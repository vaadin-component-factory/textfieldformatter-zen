package org.vaadin.cleavezenformatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

// issue: nothings happening
public class CSBFNumericAndPrefixUI extends AbstractTest {

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        TextField tf = new TextField();
        CustomStringBlockFormatter.Options fmtOptions = new CustomStringBlockFormatter.Options();
        fmtOptions.setBlocks(7, 10); // todo prefix won't work without calling setBlocks but it should
        fmtOptions.setNumericOnly(true);
        fmtOptions.setPrefix("PREFIX:");
        CustomStringBlockFormatter formatter = new CustomStringBlockFormatter(fmtOptions);
        formatter.extend(tf);
        return tf;
    }

//    @Override
//    public Component getTestComponent(UITestConfiguration configuration) {
//        TextField tf = new TextField();
//        ZenCustomStringBlockFormatter.Options fmtOptions = new ZenCustomStringBlockFormatter.Options();
//        fmtOptions.setBlocks(7, 1, 2, 3);
////        fmtOptions.setDelimiters(" ", "-", "-");
////        fmtOptions.setForceCase(ZenCustomStringBlockFormatter.ForceCase.NONE);
//        fmtOptions.setNumericOnly(true);
//        fmtOptions.setPrefix("PREFIX:");
//        ZenCustomStringBlockFormatter formatter = new ZenCustomStringBlockFormatter(fmtOptions);
//        formatter.extend(tf);
//        return tf;
//    }
}
