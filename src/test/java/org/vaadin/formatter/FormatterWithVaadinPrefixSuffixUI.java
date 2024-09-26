package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.formatter.FormatterWithVaadinPrefixSuffixUI.*;

@RouteParams({ PrefixIcon.class, PrefixText.class, SuffixIcon.class, SuffixText.class })
public class FormatterWithVaadinPrefixSuffixUI extends AbstractTest {

    private static final String PREFIX_HELPER_TEXT = "The prefix is completely separate from the input value";
    private static final String SUFFIX_HELPER_TEXT = "The suffix is completely separate from the input value";

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        return configuration.getTestComponent();
    }

    public static class PrefixIcon extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField("TextField With Vaadin Icon Prefix");
            tf.setPrefixComponent(VaadinIcon.BOOK.create());
            tf.setHelperText(PREFIX_HELPER_TEXT);

            IBANFormatter.fromIBANLength(18).extend(tf);

            return tf;
        }
    }

    public static class PrefixText extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField("TextField With Vaadin Text Prefix");
            tf.setPrefixComponent(new Span("Book #:"));
            tf.setHelperText(PREFIX_HELPER_TEXT);

            IBANFormatter.fromIBANLength(18).extend(tf);

            return tf;
        }
    }

    public static class SuffixIcon extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField("TextField With Vaadin Icon Suffix");
            tf.setSuffixComponent(VaadinIcon.SEARCH.create());
            tf.setHelperText(SUFFIX_HELPER_TEXT);

            IBANFormatter.fromIBANLength(18).extend(tf);

            return tf;
        }
    }

    public static class SuffixText extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField tf = new TextField("TextField With Vaadin Text Suffix");
            tf.setSuffixComponent(new Span("mg/dl"));
            tf.setHelperText(SUFFIX_HELPER_TEXT);

            IBANFormatter.fromIBANLength(18).extend(tf);

            return tf;
        }
    }
}
