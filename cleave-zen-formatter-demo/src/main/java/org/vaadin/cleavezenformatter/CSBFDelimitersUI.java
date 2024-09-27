package org.vaadin.cleavezenformatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

@RouteParams({ CSBFDelimitersUI.LazyDelimiter.class })
public class CSBFDelimitersUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        if (configuration != null) {
            return configuration.getTestComponent();
        }
        TextField tf = new TextField();
        new CustomStringBlockFormatter(new int[] { 1, 2, 3 }, new String[] { "-", "-" }, CustomStringBlockFormatter.ForceCase.NONE, null, false)
                .extend(tf);
        return tf;
    }

    public static class LazyDelimiter extends UITestConfiguration {
        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new CustomStringBlockFormatter.Builder().blocks(1, 2, 3).delimiters("-", "*").delimiterLazyShow().build()
                    .extend(tf);
            return tf;
        }
    }
}
