package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

@RouteParams({ ZenCSBFDelimitersUI.LazyDelimiter.class })
public class ZenCSBFDelimitersUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        if (configuration != null) {
            return configuration.getTestComponent();
        }
        TextField tf = new TextField();
        new ZenCustomStringBlockFormatter(new int[] { 1, 2, 3 }, new String[] { "-", "-" }, ZenCustomStringBlockFormatter.ForceCase.NONE, null, false)
                .extend(tf);
        return tf;
    }

    public static class LazyDelimiter extends UITestConfiguration {
        @Override
        public Component getTestComponent() {
            TextField tf = new TextField();
            new ZenCustomStringBlockFormatter.Builder().blocks(1, 2, 3).delimiters("-", "*").delimiterLazyShow().build()
                    .extend(tf);
            return tf;
        }
    }
}
