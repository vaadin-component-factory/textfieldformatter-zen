package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.textfieldformatter.AbstractTest;
import org.vaadin.textfieldformatter.UITestConfiguration;

public class ZenCSBFReplacingMaskUI extends AbstractTest {

    private final ZenCustomStringBlockFormatter.Options formatterA = new ZenCustomStringBlockFormatter.Options(new int[] { 1, 2, 3 }, new String[] { "-", "-" }, ZenCustomStringBlockFormatter.ForceCase.UPPER,
            null, false);
    private final ZenCustomStringBlockFormatter.Options formatterB = new ZenCustomStringBlockFormatter.Options(new int[] { 2, 2 }, new String[] { "*" }, ZenCustomStringBlockFormatter.ForceCase.UPPER, null,
            false);
    private ZenCustomStringBlockFormatter current;
    private TextField textField;
    private boolean isFirstFormat = true;

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        VerticalLayout layout = new VerticalLayout();
        textField = new TextField();
        current = new ZenCustomStringBlockFormatter(formatterA);
        current.extend(textField);

        Button toggleButton = new Button("SWITCH", event -> replaceFormatter());
        toggleButton.setId("switch");

        layout.add(textField, toggleButton);
        return layout;
    }

    private void replaceFormatter() {
        isFirstFormat = !isFirstFormat;
        if (current != null) {
            current.remove();
        }
        current = new ZenCustomStringBlockFormatter(isFirstFormat ? formatterA : formatterB);
        current.extend(textField);
    }
}
