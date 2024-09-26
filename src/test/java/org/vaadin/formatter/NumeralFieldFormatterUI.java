package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.formatter.NumeralFieldFormatterUI.AddLeadingZeroForDecimal;
import org.vaadin.formatter.NumeralFieldFormatterUI.AlwaysDisplayDecimal;
import org.vaadin.formatter.NumeralFieldFormatterUI.CustomValue;
import org.vaadin.formatter.NumeralFieldFormatterUI.DecimalMark;
import org.vaadin.formatter.NumeralFieldFormatterUI.DecimalScale;
import org.vaadin.formatter.NumeralFieldFormatterUI.DefaultValues;
import org.vaadin.formatter.NumeralFieldFormatterUI.DontStripLeadingZeroes;
import org.vaadin.formatter.NumeralFieldFormatterUI.GermanMoneyFormat;
import org.vaadin.formatter.NumeralFieldFormatterUI.IntegerScale;
import org.vaadin.formatter.NumeralFieldFormatterUI.PositiveOnly;
import org.vaadin.formatter.NumeralFieldFormatterUI.Postfix;
import org.vaadin.formatter.NumeralFieldFormatterUI.SignBeforePrefix;
import org.vaadin.formatter.NumeralFieldFormatterUI.ThousandsGroupLakh;
import org.vaadin.formatter.NumeralFieldFormatterUI.ThousandsGroupNone;
import org.vaadin.formatter.NumeralFieldFormatterUI.ThousandsGroupThousand;
import org.vaadin.formatter.NumeralFieldFormatterUI.ThousandsGroupWan;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RouteParams({ DefaultValues.class, CustomValue.class, ThousandsGroupThousand.class, ThousandsGroupLakh.class,
        ThousandsGroupWan.class, ThousandsGroupNone.class, IntegerScale.class, DecimalScale.class, DecimalMark.class,
        PositiveOnly.class, SignBeforePrefix.class, Postfix.class, DontStripLeadingZeroes.class, GermanMoneyFormat.class,
        AddLeadingZeroForDecimal.class, AlwaysDisplayDecimal.class })
public class NumeralFieldFormatterUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        return configuration.getTestComponent();
    }

    public static class DefaultValues extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter().extend(tf);
            tf.addValueChangeListener(l -> Notification.show("Value: " + l.getValue()));
            return tf;
        }

    }

    public static class CustomValue extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().delimiter(" ").decimalMark(",").decimalScale(3).prefix("€")
                    .signBeforePrefix(true).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupThousand extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.THOUSAND).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupLakh extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.LAKH).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupWan extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.WAN).build().extend(tf);
            return tf;
        }

    }

    public static class ThousandsGroupNone extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.NONE).build().extend(tf);
            return tf;
        }

    }

    public static class IntegerScale extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().integerScale(5).decimalScale(2).build().extend(tf);
            return tf;
        }

    }

    public static class DecimalScale extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter(".", ",", 6).extend(tf);
            return tf;
        }

    }

    public static class DecimalMark extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().decimalMark(",").build().extend(tf);
            return tf;
        }

    }

    public static class PositiveOnly extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().nonNegativeOnly(true).build().extend(tf);
            return tf;
        }

    }

    public static class SignBeforePrefix extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().prefix("€").signBeforePrefix(true).build().extend(tf);
            return new VerticalLayout(tf, new TextField("Here"));
        }

    }

    public static class Postfix extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().prefix("€", true).build().extend(tf);
            return tf;
        }

    }

    public static class DontStripLeadingZeroes extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder().stripLeadingZeroes(false).build().extend(tf);
            return tf;
        }

    }

    public static class GermanMoneyFormat extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder()
                    .decimalMark(",")
                    .delimiter(".")
                    .thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.THOUSAND)
                    .stripLeadingZeroes(false)
                    .prefix("€", true)
                    .build().extend(tf);
            return tf;
        }

    }

    public static class AddLeadingZeroForDecimal extends UITestConfiguration {

        @Override
        public Component getTestComponent() {

            // define properties needed for config and server side event
            String decimalMark = ",";

            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder()
                    .decimalMark(decimalMark)
                    .delimiter(".")
                    .thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.THOUSAND)
                    .stripLeadingZeroes(false)
                    .prefix("€", true)
                    .build().extend(tf);

            // todo: this could work better if textfield was change mode EAGER, but that isn't working with cleave at the moment
            // cleave does not support adding the zero in front, so we'll do it from the server side
            // note: this can get very messy when you have prefixes, suffixes or any other formatting
            tf.addValueChangeListener(e -> {
               if (StringUtils.isEmpty(e.getValue()))
                   return;
               if (StringUtils.indexOf(e.getValue(), decimalMark) == 0) {
                   e.getSource().setValue("0" + e.getValue());
               }
            });

            return tf;
        }

    }

    public static class AlwaysDisplayDecimal extends UITestConfiguration {

        @Override
        public Component getTestComponent() {

            // define properties needed for config and server side event
            String decimalMark = ",";
            int decimalScale = 2;

            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder()
                    .decimalMark(decimalMark)
                    .decimalScale(decimalScale)
                    .delimiter(".")
                    .thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.THOUSAND)
                    .stripLeadingZeroes(false)
                    .build().extend(tf);

            // cleave doesn't support always displaying x number of decimal places (filling them with zeros)
            // so this is an example of doing it on the server side
            // note: this can get very messy when you have prefixes, suffixes or any other formatting
            tf.addValueChangeListener(e -> {
                if (StringUtils.isEmpty(e.getValue()))
                    return;

                String formattedValue = e.getValue();
                int decimalIndex = StringUtils.indexOf(formattedValue, decimalMark);

                // add decimal mark if needed
                if (decimalIndex < 0) {
                    formattedValue += decimalMark;
                    decimalIndex = StringUtils.indexOf(formattedValue, decimalMark);
                }

                // figure out how many zeros we need to append
                int numExistingDigitsAfterDecimal = StringUtils.substring(formattedValue, decimalIndex+1).replaceAll("[^0-9]", "").length();
                int numMissingDigits = decimalScale - numExistingDigitsAfterDecimal;

                // add as many trailing 0s as needed
                String zeros = IntStream.range(0, numMissingDigits)
                                .mapToObj(i -> "0")
                                .collect(Collectors.joining(""));
                formattedValue += zeros;

                // if our value has changed, update the component
                if (!StringUtils.equals(e.getValue(), formattedValue)) {
                    e.getSource().setValue(formattedValue);
                }
            });

            return tf;
        }

    }

    public static class AlwaysDisplayDecimalWithSuffix extends UITestConfiguration {

        @Override
        public Component getTestComponent() {

            // define properties needed for config and server side event
            String decimalMark = ",";
            int decimalScale = 2;
            String suffix = "€";

            TextField tf = new TextField();
            new NumeralFieldFormatter.Builder()
                    .decimalMark(decimalMark)
                    .decimalScale(decimalScale)
                    .delimiter(".")
                    .thousandsGroupStyle(NumeralFieldFormatter.ThousandsGroupStyle.THOUSAND)
                    .stripLeadingZeroes(false)
                    .prefix(suffix, true)
                    .build().extend(tf);

            // same server-side example as in the prev test (AlwaysDisplayDecimal), except this takes into
            // consideration the suffix
            tf.addValueChangeListener(e -> {
                if (StringUtils.isEmpty(e.getValue()))
                    return;

                // do nothing if value contains no digits
                if (!e.getValue().matches(".*[0-9]+.*")) {
                    return;
                }

                String formattedValue = e.getValue();
                int decimalIndex = StringUtils.indexOf(formattedValue, decimalMark);

                // add decimal symbol if needed
                if (decimalIndex < 0) {

                    // if we contain a suffix, add decimal mark before it
                    if (StringUtils.contains(formattedValue, suffix)) {
                        formattedValue = StringUtils.substring(formattedValue, 0, formattedValue.length() - suffix.length()) + decimalMark + suffix;
                    }

                    decimalIndex = StringUtils.indexOf(formattedValue, decimalMark);
                }

                // figure out how many zeros are needed
                int numExistingDigitsAfterDecimal = StringUtils.substring(formattedValue, decimalIndex+1).replaceAll("[^0-9]", "").length();
                int numMissingDigits = decimalScale - numExistingDigitsAfterDecimal;

                // add as many trailing 0s as needed to make 2 decimal digits
                String zeros =
                        IntStream.range(0, numMissingDigits)
                        .mapToObj(i -> "0")
                        .collect(Collectors.joining(""));

                // insert zeros before suffix
                formattedValue =
                        StringUtils.substring(formattedValue, 0, decimalIndex + numExistingDigitsAfterDecimal + 1)
                        + zeros
                        + StringUtils.substring(formattedValue, -suffix.length());

                // if our value has changed, update the component
                if (!StringUtils.equals(e.getValue(), formattedValue)) {
                    e.getSource().setValue(formattedValue);
                }
            });

            return tf;
        }

    }
}