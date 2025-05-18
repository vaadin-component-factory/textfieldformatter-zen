package org.vaadin.addons.componentfactory.cleavezenformatter.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.addons.componentfactory.cleavezenformatter.NumeralFieldFormatterUI;
import org.vaadin.addons.componentfactory.cleavezenformatter.PhoneFieldFormatterUsageUI;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;

public class PhoneFieldFormatterUsageIT extends AbstractCustomTestBenchTestCase {

	@Before
	public void init() {
		startBrowser();
	}

	@Test
	public void phoneFieldWithGermanInternationalValues() throws InterruptedException {
		openUI(PhoneFieldFormatterUsageUI.class, PhoneFieldFormatterUsageUI.GermanInternationalValues.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("017012345678");
		Assert.assertEquals("+49 170 12345678", tf.getValue());
	}

	@Test
	public void phoneFieldWithGermanInternationalValues_00() throws InterruptedException {
		openUI(PhoneFieldFormatterUsageUI.class, PhoneFieldFormatterUsageUI.GermanInternationalValues.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.setValue("0049 30 12345678");
		Assert.assertEquals("+49 30 12345678", tf.getValue());
	}

	@Test
	public void phoneFieldWithGermanInternationalValues_addIndicator() throws InterruptedException {
		openUI(PhoneFieldFormatterUsageUI.class, PhoneFieldFormatterUsageUI.GermanInternationalValues.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("00");
		Assert.assertEquals("+49 00", tf.getValue());
	}
	@Test
	public void phoneFieldWithGermanNationalValues() throws InterruptedException {
		openUI(PhoneFieldFormatterUsageUI.class, PhoneFieldFormatterUsageUI.GermanNationalValues.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("017012345678");
		Assert.assertEquals("0170 12345678", tf.getValue());
	}

	@Test
	public void phoneFieldWithUSInternationalValues() throws InterruptedException {
		openUI(PhoneFieldFormatterUsageUI.class, PhoneFieldFormatterUsageUI.DefaultValues.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("017012345678");
		Assert.assertEquals("+1 017012345678", tf.getValue());
	}

}