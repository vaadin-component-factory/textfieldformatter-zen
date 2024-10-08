package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.Registration;

@Tag("textfield-formatter")
@NpmPackage(value = "cleave-zen", version = "0.0.17")
@JsModule("./textfield-formatter.ts")
public abstract class CleaveExtension<CONF extends AbstractCleaveConfiguration> extends Component {

	private WeakReference<Component> extended;
	private CONF configuration;
	private Registration attachRegistration = null;
	private final List<PasteOverflowListener> pasteOverflowListeners = new ArrayList<>();

	public CleaveExtension() {
	}

	protected void extend(Component component) {
		if (!component.getUI().isPresent()) {
			attachRegistration = component.addAttachListener(event -> {
				extend(component, event.getUI());
			});

		} else {
			extend(component, component.getUI().get());
		}
	}

	private void extend(Component component, UI ui) {
		extended = new WeakReference<>(component);
		component.getElement().appendChild(getElement());

		// update client-side change listener that handles formatting
		if (component instanceof HasValueChangeMode) {
			getElement().executeJs("$0.updateValueChangeEvent($1);", this,
					ValueChangeMode.eventForMode(
							((HasValueChangeMode) component).getValueChangeMode(),
							"input")
			);
		}

		// update client-side cleave js config
		getElement().executeJs("$0.updateConf($1, $2);", this, getConfiguration().toJson(), getConfiguration().getFormatType());
	}

	public void remove() {
		if (attachRegistration != null) {
			attachRegistration.remove();
			attachRegistration = null;
		}
		if (extended != null) {
			getElement().removeFromParent();
			extended.clear();
		}
	}

	protected CONF getConfiguration() {
		if (configuration == null) {
			configuration = createDefaultConfiguration();
		}
		return configuration;
	}
	protected abstract CONF createDefaultConfiguration();

	// ===========================================
	// Paste Overflow Event
	// ===========================================

	@ClientCallable
	public void onPasteOverflow(String originalValue, String formattedValue) {
		pasteOverflowListeners.forEach(l -> l.onPasteOverflow(new PasteOverflowEvent(this, originalValue, formattedValue)));
	}

	public void addPasteOverflowListener(PasteOverflowListener listener) {
		pasteOverflowListeners.add(listener);
	}

	public void removePasteOverflowListener(PasteOverflowListener listener) {
		pasteOverflowListeners.remove(listener);
	}

	public interface PasteOverflowListener {
		void onPasteOverflow(PasteOverflowEvent event);
	}

	public static class PasteOverflowEvent {
		private final String originalValue;
		private final String formattedValue;
		private final CleaveExtension<?> source;

		public PasteOverflowEvent(CleaveExtension<?> source, String originalValue, String formattedValue) {
			this.source = source;
			this.originalValue = originalValue;
			this.formattedValue = formattedValue;
		}

		public CleaveExtension<?> getSource() {
			return source;
		}

		public String getOriginalValue() {
			return originalValue;
		}

		public String getFormattedValue() {
			return formattedValue;
		}
	}
}
