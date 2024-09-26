package org.vaadin.formatter.it;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.formatter.NumeralFieldFormatterUI;

public class NumeralFieldFormatterUsageIT extends AbstractCustomTestBenchTestCase {

	@Before
	public void init() {
		startBrowser();
	}

	@Test
	public void numeralFieldWithDefaultValues() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.DefaultValues.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("12345.80");
		Assert.assertEquals("12,345.80", tf.getValue());
	}

	@Test
	public void numeralFieldWithCustomValues() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.CustomValue.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("-12345,801");
		Assert.assertEquals("-€12 345,801", tf.getValue());
	}

	@Test
	public void numeralFieldWithThousandsGroupStyle() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.ThousandsGroupThousand.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("1234567.89");
		Assert.assertEquals("1,234,567.89", tf.getValue());

		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.ThousandsGroupWan.class);
		tf = $(TextFieldElement.class).first();
		tf.sendKeys("1234567.89");
		Assert.assertEquals("123,4567.89", tf.getValue());

		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.ThousandsGroupLakh.class);
		tf = $(TextFieldElement.class).first();
		tf.sendKeys("1234567.89");
		Assert.assertEquals("12,34,567.89", tf.getValue());

		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.ThousandsGroupNone.class);
		tf = $(TextFieldElement.class).first();
		tf.sendKeys("1234567.89");
		Assert.assertEquals("1234567.89", tf.getValue());
	}

	@Test
	public void numeralFieldWithIntegerScale() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.IntegerScale.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("-123456.789");
		Assert.assertEquals("-12,345.78", tf.getValue());
	}

	@Test
	public void numeralFieldWithDecimalScale() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.DecimalScale.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("-12345,6789012");
		Assert.assertEquals("-12.345,678901", tf.getValue());
	}

	@Test
	public void numeralFieldWithDecimalMark() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.DecimalMark.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("1,2");
		Assert.assertEquals("1,2", tf.getValue());
	}

	@Test
	public void numeralFieldWithPositiveOnly() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.PositiveOnly.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("-12.34");
		Assert.assertEquals("12.34", tf.getValue());
	}

	@Test
	public void numeralFieldWithSignBeforePrefix() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.SignBeforePrefix.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("-123.45");
		Assert.assertEquals("-€123.45", tf.getValue());
	}

	@Test
	public void numeralFieldWithPostfix() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.Postfix.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("-123.45");
		Assert.assertEquals("-123.45€", tf.getValue());
	}

	@Test
	public void numeralFieldWithStripLeadingZeroes() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.DontStripLeadingZeroes.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("001.23");
		Assert.assertEquals("001.23", tf.getValue());
	}

	@Test
	public void numeralFieldWithGermanMoneyFormat() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.GermanMoneyFormat.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("123456789,123456");
		Assert.assertEquals("123.456.789,12€", tf.getValue());
	}

	@Test
	public void numeralFieldWithLeadingZeros() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.AddLeadingZeroForDecimal.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys(",12");

		// we need to blur because this example uses server-side logic and only happens after value is sent to server
		$(ButtonElement.class).first().focus();
		getCommandExecutor().waitForVaadin();

		Assert.assertEquals("0,12€", tf.getValue());
	}

	@Test
	public void numeralFieldWithAlwaysShownDecimal() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.AlwaysDisplayDecimal.class);
		TextFieldElement tf = $(TextFieldElement.class).first();

		tf.sendKeys("123");
		// we need to blur because this example uses server-side logic and only happens after value is sent to server
		$(ButtonElement.class).first().focus();
		getCommandExecutor().waitForVaadin();
		Assert.assertEquals("123,00", tf.getValue());

		tf.setValue("");
		tf.sendKeys("123,1");
		$(ButtonElement.class).first().focus();
		getCommandExecutor().waitForVaadin();
		Assert.assertEquals("123,10", tf.getValue());
	}

	@Test
	public void numeralFieldWithAlwaysShownDecimalWithSuffix() throws InterruptedException {
		openUI(NumeralFieldFormatterUI.class, NumeralFieldFormatterUI.AlwaysDisplayDecimalWithSuffix.class);
		TextFieldElement tf = $(TextFieldElement.class).first();

		tf.sendKeys("123");
		// we need to blur because this example uses server-side logic and only happens after value is sent to server
		$(ButtonElement.class).first().focus();
		getCommandExecutor().waitForVaadin();
		Assert.assertEquals("123,00€", tf.getValue());

		tf.setValue("");
		tf.sendKeys("123,1");
		$(ButtonElement.class).first().focus();
		getCommandExecutor().waitForVaadin();
		Assert.assertEquals("123,10€", tf.getValue());
	}
}