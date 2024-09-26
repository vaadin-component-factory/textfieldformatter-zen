package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

public class ZenCSBFNumericAndPrefixWithBuilderUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        TextField tf = new TextField();
        ZenCustomStringBlockFormatter.Builder builder = new ZenCustomStringBlockFormatter.Builder();
        builder.blocks(1, 2, 3).delimiters("-", "-").numeric().prefix("PREFIX:", " ");
        ZenCustomStringBlockFormatter formatter = builder.build();
        formatter.extend(tf);
        return tf;
    }
}
