package org.vaadin.cleavezenformatter.it;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.cleavezenformatter.BasicIBANFormatterUsageUI;
import org.vaadin.cleavezenformatter.CSBFDelimitersUI;
import org.vaadin.cleavezenformatter.CSBFNumericAndPrefixUI;
import org.vaadin.cleavezenformatter.CSBFNumericAndPrefixWithBlocksUI;
import org.vaadin.cleavezenformatter.CSBFNumericAndPrefixWithBuilderUI;
import org.vaadin.cleavezenformatter.CSBFNumericOnlyUI;
import org.vaadin.cleavezenformatter.CSBFReplacingMaskUI;

public class CustomStringBlockFormatterIT extends AbstractCustomTestBenchTestCase {

	@Before
	public void init() {
		startBrowser();
	}

	@Test
	public void basicIban() throws InterruptedException {
		openUI(BasicIBANFormatterUsageUI.class, BasicIBANFormatterUsageUI.BasicIBAN.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("FI42500015100000231");
		Assert.assertEquals("FI42 5000 1510 0000 23", tf.getValue());
	}

	@Test
	public void replaceIbanFormatter() throws InterruptedException {
		openUI(BasicIBANFormatterUsageUI.class, BasicIBANFormatterUsageUI.ReplaceIBAN.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("FI425000151000002319999");
		Assert.assertEquals("FI42 5000 1510 0000 2319 99", tf.getValue());
	}

	@Test
	public void customBlockWithDelimiters() throws InterruptedException {

		openUI(CSBFDelimitersUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("12233k");
		Assert.assertEquals("1-22-33k", tf.getValue());
	}

	@Test
	public void customBlockWithDelimitersNumericOnly() throws InterruptedException {

		openUI(CSBFNumericOnlyUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("12233k");
		Assert.assertEquals("1-22*33", tf.getValue());
	}

	@Test
	public void customBlockWithReplacingMask() throws InterruptedException {
		openUI(CSBFReplacingMaskUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("12233abcd");
		Assert.assertEquals("1-22-33A", tf.getValue());
		$(ButtonElement.class).withId("switch").first().click();
		Assert.assertEquals("1-*22", tf.getValue());
		tf.setValue("");
		tf.sendKeys("12233abcd");
		Assert.assertEquals("12*23", tf.getValue());
	}

	@Test
	public void customBlocksWithNumericAndPrefixBlocks() throws InterruptedException {
		openUI(CSBFNumericAndPrefixWithBlocksUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		Assert.assertEquals("PREFIX: ", tf.getValue());
		tf.sendKeys("1234bbbbb");
		Assert.assertEquals("PREFIX: 1-23-4", tf.getValue());
	}

	@Test
	public void customBlocksWithNumericAndPrefixWithBuilder() throws InterruptedException {
		openUI(CSBFNumericAndPrefixWithBuilderUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		Assert.assertEquals("PREFIX: ", tf.getValue());
		tf.sendKeys("1234bbbbb");
		Assert.assertEquals("PREFIX: 1-23-4", tf.getValue());
	}

	@Test
	public void customBlocksWithNumericAndPrefix() throws InterruptedException {
		openUI(CSBFNumericAndPrefixUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		Assert.assertEquals("PREFIX:", tf.getValue());
		tf.sendKeys("1234bbbbb");
		Assert.assertEquals("PREFIX:1234", tf.getValue());
	}

	@Test
	public void customBlocksWithLazyDelimiter() throws InterruptedException {
		openUI(CSBFDelimitersUI.class, CSBFDelimitersUI.LazyDelimiter.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("1");
		Assert.assertEquals("1", tf.getValue());
		tf.sendKeys("2");
		Assert.assertEquals("1-2", tf.getValue());
	}
}
