package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.vaadin.textfieldformatter.AbstractTest;
import org.vaadin.textfieldformatter.UITestConfiguration;

public class ZenCSBFReplacingMaskUI extends AbstractTest {

    private final ZenCustomStringBlockFormatter.Options formatterA = new ZenCustomStringBlockFormatter.Options(new int[] { 1, 2, 3 }, new String[] { "-", "-" }, ZenCustomStringBlockFormatter.ForceCase.UPPER,
            null, false);
    private final ZenCustomStringBlockFormatter.Options formatterB = new ZenCustomStringBlockFormatter.Options(new int[] { 2, 2 }, new String[] { "*" }, ZenCustomStringBlockFormatter.ForceCase.UPPER, null,
            false);
    private ZenCustomStringBlockFormatter current;
    private TextField textField;

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        VerticalLayout layout = new VerticalLayout();
        textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        current = new ZenCustomStringBlockFormatter(formatterA);
        current.extend(textField);

        Button toggleButton = new Button("SWITCH", event -> {
            replaceFormatter(textField, formatterB);
        });
        toggleButton.setId("switch");
        toggleButton.setDisableOnClick(true);

        layout.add(textField, toggleButton);
        return layout;
    }

    private void replaceFormatter(TextField textField, ZenCustomStringBlockFormatter.Options options) {
        if (current != null) {
            current.remove();
        }
        current = new ZenCustomStringBlockFormatter(options);
        current.extend(textField);
    }
}
