package org.vaadin.addons.componentfactory.cleavezenformatter;

import org.vaadin.addons.componentfactory.cleavezenformatter.CustomStringBlockFormatter.Options;
import org.vaadin.addons.componentfactory.cleavezenformatter.CustomStringBlockFormatter.ForceCase;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

// issues: prefix is not visible initially (only after typing)
public class CSBFNumericAndPrefixWithBlocksUI extends AbstractTest {

	@Override
	public Component getTestComponent(UITestConfiguration configuration) {
		TextField tf = new TextField();
		Options fmtOptions = new Options();
		fmtOptions.setBlocks(7, 1, 2, 3);
		fmtOptions.setDelimiters(" ", "-", "-");
		fmtOptions.setForceCase(ForceCase.NONE);
		fmtOptions.setNumericOnly(true);
		fmtOptions.setPrefix("PREFIX:");
		CustomStringBlockFormatter formatter = new CustomStringBlockFormatter(fmtOptions);
		formatter.extend(tf);
		return tf;
	}
}
