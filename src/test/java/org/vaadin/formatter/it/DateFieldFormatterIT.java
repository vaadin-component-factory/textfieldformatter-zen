package org.vaadin.formatter.it;

import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.formatter.ZenDateFieldFormatterUI;


public class DateFieldFormatterIT extends AbstractCustomTestBenchTestCase {
	@Before
	public void init() {
		startBrowser();
	}

	@Test
	public void monthYear() {
		openUI(ZenDateFieldFormatterUI.class, ZenDateFieldFormatterUI.MonthYear.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("112011");
		Assert.assertEquals("11/2011", tf.getValue());
	}

	@Test
	public void americanDate() {
		openUI(ZenDateFieldFormatterUI.class, ZenDateFieldFormatterUI.AmericanDate.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("a2.4.2020");
		Assert.assertEquals("02/04/2020", tf.getValue());
	}

	@Test
	public void finnishDate() {
		openUI(ZenDateFieldFormatterUI.class, ZenDateFieldFormatterUI.FinnishDate.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("a4/2/2020");
		Assert.assertEquals("04.02.2020", tf.getValue());
	}

	@Test
	public void withMinMax() {
		openUI(ZenDateFieldFormatterUI.class, ZenDateFieldFormatterUI.MinMax.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("03022019");
		Assert.assertEquals("04/02/2019", tf.getValue());
		tf.sendKeys("05022019");
		Assert.assertEquals("04/02/2019", tf.getValue());
		tf.sendKeys("04022019");
		Assert.assertEquals("04/02/2019", tf.getValue());
	}

	@Test
	public void withMin() {
		openUI(ZenDateFieldFormatterUI.class, ZenDateFieldFormatterUI.Min.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("03022019");
		Assert.assertEquals("04/02/2019", tf.getValue());
		tf.sendKeys("04022019");
		Assert.assertEquals("04/02/2019", tf.getValue());
	}

	@Test
	public void withMax() {
		openUI(ZenDateFieldFormatterUI.class, ZenDateFieldFormatterUI.Max.class);
		TextFieldElement tf = $(TextFieldElement.class).first();
		tf.sendKeys("04022019");
		Assert.assertEquals("03/02/2019", tf.getValue());
		tf.sendKeys("03022019");
		Assert.assertEquals("03/02/2019", tf.getValue());
	}
}
