package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

public class CSBFNumericOnlyUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        TextField tf = new TextField();
        new CustomStringBlockFormatter(new int[] { 1, 2, 3 }, new String[] { "-", "*" }, CustomStringBlockFormatter.ForceCase.NONE, null, true)
                .extend(tf);
        return tf;
    }
}
