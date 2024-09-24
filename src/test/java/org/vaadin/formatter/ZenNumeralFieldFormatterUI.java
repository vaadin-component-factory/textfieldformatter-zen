package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.formatter.ZenNumeralFieldFormatterUI.*;
import org.vaadin.textfieldformatter.AbstractTest;
import org.vaadin.textfieldformatter.RouteParams;
import org.vaadin.textfieldformatter.UITestConfiguration;

@RouteParams({ DefaultValues.class, CustomValue.class, ThousandsGroupThousand.class, ThousandsGroupLakh.class,
        ThousandsGroupWan.class, ThousandsGroupNone.class, IntegerScale.class, DecimalScale.class, DecimalMark.class,
        PositiveOnly.class, SignBeforePrefix.class, Postfix.class, DontStripLeadingZeroes.class })
public class ZenNumeralFieldFormatterUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        return configuration.getTestComponent();
    }

    public static class DefaultValues extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter().extend(tf);
            tf.addValueChangeListener(l -> Notification.show("Value: " + l.getValue()));
            return tf;
        }

    }

    public static class CustomValue extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().delimiter(" ").decimalMark(",").decimalScale(3).prefix("€")
                    .signBeforePrefix(true).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupThousand extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().thousandsGroupStyle(ZenNumeralFieldFormatter.ThousandsGroupStyle.THOUSAND).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupLakh extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().thousandsGroupStyle(ZenNumeralFieldFormatter.ThousandsGroupStyle.LAKH).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupWan extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().thousandsGroupStyle(ZenNumeralFieldFormatter.ThousandsGroupStyle.WAN).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupNone extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().thousandsGroupStyle(ZenNumeralFieldFormatter.ThousandsGroupStyle.NONE).build().extend(tf);
            return tf;
        }

    }

    public static class IntegerScale extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().integerScale(5).decimalScale(2).build().extend(tf);
            return tf;
        }

    }

    public static class DecimalScale extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter(".", ",", 6).extend(tf);
            return tf;
        }

    }

    public static class DecimalMark extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().decimalMark(",").build().extend(tf);
            return tf;
        }

    }

    public static class PositiveOnly extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().nonNegativeOnly(true).build().extend(tf);
            return tf;
        }

    }

    public static class SignBeforePrefix extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().prefix("€").signBeforePrefix(true).build().extend(tf);
            return new VerticalLayout(tf, new TextField("Here"));
        }

    }

    public static class Postfix extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().prefix("€", true).build().extend(tf);
            return tf;
        }

    }

    public static class DontStripLeadingZeroes extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenNumeralFieldFormatter.Builder().stripLeadingZeroes(false).build().extend(tf);
            return tf;
        }

    }
}