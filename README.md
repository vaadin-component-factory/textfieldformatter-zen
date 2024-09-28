# Cleave Zen TextField Formatter

[//]: # ([![Published on Vaadin  Directory]&#40;https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg&#41;]&#40;https://vaadin.com/directory/component/textfield-formatter&#41;)

[//]: # ([![Stars on Vaadin Directory]&#40;https://img.shields.io/vaadin-directory/star/textfield-formatter.svg&#41;]&#40;https://vaadin.com/directory/component/textfield-formatter&#41;)

This add-on provides a library for formatting Vaadin TextField input.
This project is built on top the [cleave-zen](https://nosir.github.io/cleave-zen/).

## Description

This project provides a Java API for the [cleave-zen](https://nosir.github.io/cleave-zen/) 
Javascript formatting library and allows the formatting utilities to be applied to 
Vaadin TextField and TextArea components.

Different from an input mask, the actual value of the input field is formatted.

Along with the ability to configure custom formats, the following format 
are supported out of the box:

- Credit card formatting
- Numeral formatting
- Date / Time formatting
- Custom delimiter, prefix and blocks pattern

## How to use it

The best place to find examples of usage is by viewing the demo application in the test directory.
Below are a few basic examples to give and high-level view of how the API works.

### Formatting

#### Numeric formatting

```java
// 1234567.12-> $1,234,567.12
TextField tf = new TextField();
new NumeralFieldFormatter.Builder()
        .thousandsGroupStyle(ThousandsGroupStyle.THOUSAND)
        .stripLeadingZeroes(false)
        .prefix("$")
        .build()
        .extend(tf);
add(tf);
```

```java
// 1234567,12-> 1.234.567,12€
TextField tf = new TextField();
new NumeralFieldFormatter.Builder()
        .decimalMark(",")
        .delimiter(".")
        .thousandsGroupStyle(ThousandsGroupStyle.THOUSAND)
        .stripLeadingZeroes(false)
        .prefix("€", true)
        .build()
        .extend(tf);
add(tf);
```

#### IBAN formatting

123412341234 -> 1234 1234 1234

```java
TextField tf = new TextField();
IBANFormatter.fromIBANLength(12).extend(tf);
add(tf);
```

#### Credit card formatting

```java
TextField tf = new TextField();
tf.setPlaceholder("Insert credit card number");

CreditCardFieldFormatter formatter = new CreditCardFieldFormatter();
formatter.extend(tf);

// library will notify when type of card changes (eg: VISA, MASTERCARD, etc)
formatter.addCreditCardChangedListener(l -> Notification.show("" + l.getCreditCardType()));

add(tf);
```

### Paste overflow event

If the user pastes a value into a TextField and the original value is truncated when the formatting is applied, 
a `PaseteOverflowEvent` is fired on the server-side. This can be useful if you want to notify the user that their
original input is not fully included in the final formatted value. This event can be listened for by adding a 
listener to any formatter.

```java
TextField tf = new TextField();
tf.setHelperText("Max of 4 characters. Pasting a string longer than 4 characters will result in a notification.");

// limit field to 4 characters
CustomStringBlockFormatter.Options fmtOptions = new CustomStringBlockFormatter.Options();
fmtOptions.setBlocks(4);

// listen for overflow event
CustomStringBlockFormatter formatter = new CustomStringBlockFormatter(fmtOptions);
formatter.addPasteOverflowListener(e -> {
    Notification.show("Paste Overflow Event: " +
            "OriginalValue[" + e.getOriginalValue() + "] " +
            "FormattedValue[" + e.getFormattedValue() + "]");
});
formatter.extend(tf);
add(tf);
```

### Credit card type change event

When using a credit card formatter, the type of credit card is automatically detected. When a change occurs, a 
`CreditCardChangedEvent` is fired on the server-side. This event can be listened for by adding a listener to 
the credit card formatter.

```java
TextField tf = new TextField();
CreditCardFieldFormatter formatter = new CreditCardFieldFormatter();
formatter.extend(tf);
formatter.addCreditCardChangedListener(e -> Notification.show("Credit card type: " + e.getCreditCardType()));
add(tf);
```
## Development instructions

The following contains information for both developing the add-on source, as well as running the demo app.

### Directory structure
- `cleave-zen-formatter`: add-on source code project
- `cleavev-zen-formatter-demo`: demo + integrated tests project

### Build and install

Install artifacts locally by running a clean install in the root directory:
```
mvn clean install
```

### Running the demo application
1. From the `cleave-zen-formatter-demo` directory, run `mvn spring-boot:run`
2. Alternatively, you can run the `Application.java` class from your IDE
3. Navigate to http://localhost:8080

### Running the integration tests application
1. Make sure the demo app is running (see previous section)
2. From the `cleave-zen-formatter-demo` directory, use your IDE to run the tests under `src/test/` directory

## Missing features or bugs

You can report any issue or missing feature on [GitHub](https://github.com/vaadin-component-factory/textfieldformatter-zen/issues).

## License

Apache License 2.0.