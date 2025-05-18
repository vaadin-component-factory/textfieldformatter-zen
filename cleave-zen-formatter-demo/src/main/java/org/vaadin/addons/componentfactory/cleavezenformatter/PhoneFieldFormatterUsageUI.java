package org.vaadin.addons.componentfactory.cleavezenformatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

@RouteParams({ PhoneFieldFormatterUsageUI.DefaultValues.class,
				PhoneFieldFormatterUsageUI.GermanNationalValues.class,
				PhoneFieldFormatterUsageUI.GermanInternationalValues.class
})
public class PhoneFieldFormatterUsageUI extends AbstractTest {

	@Override
	public Component getTestComponent(UITestConfiguration configuration) {
		return configuration.getTestComponent();
	}

	public static class DefaultValues extends UITestConfiguration {

		@Override
		public Component getTestComponent() {
			TextField tf = new TextField();
			new PhoneFieldFormatter().extend(tf);
			tf.addValueChangeListener(l -> Notification.show("Value: " + l.getValue()));
			return tf;
		}

	}

	public static class GermanNationalValues extends UITestConfiguration {

		@Override
		public Component getTestComponent() {
			TextField tf = new TextField();
			PhoneFieldFormatter phoneFieldFormatter = new PhoneFieldFormatter("DE", true);
			phoneFieldFormatter.extend(tf);
			tf.addValueChangeListener(l -> Notification.show("Value: " + l.getValue()));
			return tf;
		}
	}

	public static class GermanInternationalValues extends UITestConfiguration {

		@Override
		public Component getTestComponent() {
			TextField tf = new TextField();
			new PhoneFieldFormatter("DE").extend(tf);
			tf.addValueChangeListener(l -> Notification.show("Value: " + l.getValue()));
			return tf;
		}

	}

}
