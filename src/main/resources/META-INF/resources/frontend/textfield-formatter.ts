import {
  CursorTrackerDestructor,
  DelimiterType,
  formatCreditCard,
  FormatCreditCardOptions,
  formatDate,
  formatGeneral, formatNumeral,
  formatTime,
  getCreditCardType,
  unformatCreditCard
} from 'cleave-zen';
import { registerCursorTracker, DefaultCreditCardDelimiter } from 'cleave-zen';

const getDelimiterRegexByDelimiter = (delimiter: string): RegExp =>
    new RegExp(delimiter.replace(/([.?*+^$[\]\\(){}|-])/g, '\\$1'), 'g')

const stripDelimiters = (
                                  value : string,
                                  current : DelimiterType): string => {
    current.split('').forEach(letter => {
      value = value.replace(getDelimiterRegexByDelimiter(letter), '')
    })

  return value
}
/**
 * `textfield-formatter` PWeb Component wrapper for Cleavejs Zen
 *
 * @customElement
 */
class TextfieldFormatter extends HTMLElement {
  static get is() { return 'textfield-formatter'; }

  private logPrefix: string = 'CleaveZen: ';

  private configuration: any;
  private valueChangeEvent: string = 'input';
  private cursorTracker?: CursorTrackerDestructor;
  private el?: HTMLInputElement | null = null; // input element
  private formatType: 'creditCard' | 'general' | 'numeral' | 'date'  | 'time' = 'creditCard';

  connectedCallback() {
    console.log(this.logPrefix + "connectedCallback");
    this.findInputElement();
    this.addListeners();
  }

  findInputElement() {
    this.el = this.parentElement!.shadowRoot!.querySelector('input'); // retrocompatibility purposes
    if (!this.el) {
      this.el = this.parentElement!.querySelector('input');
    }
  }

  addListeners() {
    console.log(this.logPrefix + "Adding change listeners");

    if (!this.el) {
      console.error(this.logPrefix + "Cannot add listeners because this.el is null");
      return;
    }

    this.el!.addEventListener('input', this.creditTypeChanged);
    if (['VAADIN-TEXT-FIELD', 'VAADIN-TEXT-AREA'].includes(this.parentElement!.tagName.toUpperCase())) {
      this.el!.addEventListener('input', this.inputChanged);
      this.el!.addEventListener(this.valueChangeEvent, this.notifyVaadinComponentOfChange);
    } else {
      this.el!.addEventListener('change', this.inputValueChanged);
    }
  }

  removeListeners() {
    console.log(this.logPrefix + "Removing previous change listeners");

    if (!this.el) {
      console.error(this.logPrefix + "Cannot remove listeners because this.el is null");
      return;
    }

    this.el!.removeEventListener('input', this.creditTypeChanged);
    this.el!.removeEventListener('input', this.inputChanged);
    this.el!.removeEventListener(this.valueChangeEvent, this.notifyVaadinComponentOfChange);
    this.el!.removeEventListener('change', this.inputValueChanged);
  }

  private creditTypeChanged = (e: Event) => {
    // ignore event if format type is not credit card
    if (this.formatType != 'creditCard') {
      return;
    }

    console.log(this.logPrefix + 'creditTypeChanged'); console.log(e);
    const value = (e.target as HTMLInputElement).value;
    const creditCardType = getCreditCardType(value);
    (this as any).$server.onCreditCardChanged(creditCardType);
  }

  private inputChanged = (e: Event) => {
    console.log(this.logPrefix + 'inputChanged'); console.log(e);
    const value = (e.target as HTMLInputElement).value;
    const formattedValue = this.formatValue(value);

    console.log(this.logPrefix + "InputValue[" + value + "] FormattedValue[" + formattedValue + "]");

    (this.parentElement as any).value = formattedValue;
    if (formattedValue) {
      this.el!.value = formattedValue;
    }
  }

  notifyVaadinComponentOfChange = (e: Event) => {
    console.log(this.logPrefix + "Notifying vaadin component of change");
    (this.parentElement as any)._onChange(e);
  }

  formatValue(value: string) {
    let formattedValue;
    switch (this.formatType) {
      case "creditCard":
        formattedValue = formatCreditCard(value, this.configuration);
        break;
      case "general":
        formattedValue = formatGeneral(value, this.configuration);
        break;
      case "numeral":
        formattedValue = formatNumeral(value, this.configuration);
        break;
      case "date":
        formattedValue = formatDate(value, this.configuration);
        break;
      case "time":
        formattedValue = formatTime(value, this.configuration);
        break;
    }
    return formattedValue;
  }

  private inputValueChanged = (e: Event) => {
    console.log(this.logPrefix + 'inputValueChanged'); console.log(e);
    const value = (e.target as HTMLInputElement).value;
    const formattedValue = this.formatValue(value);
    (this.parentElement as any).value = formattedValue;
    // should work specifically for the credit card
    if (stripDelimiters(value, DefaultCreditCardDelimiter) !== unformatCreditCard(formattedValue)) {
      console.error('Value has been striped');
    }
  };

  updateConf(configuration: any, formatType: 'creditCard' | 'general' | 'numeral' | 'date'  | 'time') {
    console.log(this.logPrefix + 'updateConf'); console.log(formatType); console.log(configuration);
    this.formatType = formatType;
    this.configuration = configuration;
    if (this.cursorTracker) {
      this.cursorTracker()
    }
    this.cursorTracker = registerCursorTracker({ input: this.el!, delimiter: this.configuration.delimiter });

    // if we're already initialized, fire change event to force immediate update with new cleave config
    if (this.el) {
      this.fireInputEvent(true);
    }
  }

  updateValueChangeEvent(valueChangeEvent: string) {
    console.log(this.logPrefix + 'updateValueChangeEvent'); console.log(valueChangeEvent);

    // redo listeners with new event type
    if (this.el) {
      this.removeListeners();
      this.valueChangeEvent = valueChangeEvent;
      this.addListeners();
    }
  }

  fireInputEvent(notifyVaadinComponent: boolean) {
    console.log(this.logPrefix + 'Manually firing an input event');

    // todo do we need to differ the event type based on whether its a vaadin text component?
    // let eventType = '';
    // if (['VAADIN-TEXT-FIELD', 'VAADIN-TEXT-AREA'].includes(this.parentElement!.tagName.toUpperCase())) {
    //   eventType = 'input';
    // } else {
    //   eventType = 'change';
    // }

    const event = new Event('input', {
      bubbles: true,
      cancelable: false,
      composed: true
    });

    console.log(this.logPrefix + "New Event:");
    console.log(event);

    const isEventDispatched = this.el!.dispatchEvent(event);
    console.log(this.logPrefix + 'isEventDispatched = ' + isEventDispatched);

    if (notifyVaadinComponent) {
      this.notifyVaadinComponentOfChange(event);
    }
  }

  disconnectedCallback() {
    console.log(this.logPrefix + "disconnectedCallback");

    // remove event listeners
    this.removeListeners();

    if (this.cursorTracker) {
      this.cursorTracker()
    }
  }
}

window.customElements.define(TextfieldFormatter.is, TextfieldFormatter);
