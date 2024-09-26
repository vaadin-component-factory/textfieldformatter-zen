package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

@RouteParams({ ZenBasicIBANFormatterUsageUI.BasicIBAN.class, ZenBasicIBANFormatterUsageUI.ReplaceIBAN.class })
public class ZenBasicIBANFormatterUsageUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        return configuration.getTestComponent();
    }

    public static class BasicIBAN extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            ZenIBANFormatter.fromIBANLength(18).extend(tf);
            tf.addValueChangeListener(l -> Notification.show("Value: " + l.getValue()));
            return tf;
        }

    }

    public static class ReplaceIBAN extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            ZenIBANFormatter formatter = ZenIBANFormatter.fromIBANLength(18);
            formatter.extend(tf);
            formatter.remove();
            ZenIBANFormatter.fromIBANLength(22).extend(tf);
            return tf;
        }

    }
}
