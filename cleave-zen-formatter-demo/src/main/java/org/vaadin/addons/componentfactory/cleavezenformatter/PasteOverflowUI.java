package org.vaadin.addons.componentfactory.cleavezenformatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

public class PasteOverflowUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        TextField tf = new TextField();
        tf.setHelperText("Max of 4 characters. Pasting a string longer than 4 characters will result in a notification.");

        // limit field to 4 characters
        CustomStringBlockFormatter.Options fmtOptions = new CustomStringBlockFormatter.Options();
        fmtOptions.setBlocks(4);

        // listen for overflow event
        CustomStringBlockFormatter formatter = new CustomStringBlockFormatter(fmtOptions);
        formatter.addPasteOverflowListener(e -> {
            Notification.show("Paste Overflow Event: " +
                    "OriginalValue[" + e.getOriginalValue() + "] " +
                    "FormattedValue[" + e.getFormattedValue() + "]");
        });
        formatter.extend(tf);
        return tf;
    }

}
