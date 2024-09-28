package org.vaadin.addons.componentfactory.cleavezenformatter.it;

import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.addons.componentfactory.cleavezenformatter.BasicCreditCardFieldFormatterUsageUI;

public class CreditCardFieldFormatterIT extends AbstractCustomTestBenchTestCase {

	@Before
	public void init() {
		startBrowser();
	}

	@Test
	public void validVisaNumber() throws InterruptedException {
		openUI(BasicCreditCardFieldFormatterUsageUI.class);
		TextFieldElement tf = $(TextFieldElement.class).first();

		NotificationElement notification = $(NotificationElement.class).onPage().first();
		Assert.assertEquals("GENERAL", notification.getText());

		tf.sendKeys("4");
		notification = $(NotificationElement.class).onPage().get(1);
		Assert.assertEquals("VISA", notification.getText());

		tf.sendKeys("000000000000000");
		Assert.assertEquals("4000 0000 0000 0000", tf.getValue());
		tf.sendKeys("23445");
		Assert.assertEquals("4000 0000 0000 0000", tf.getValue());
	}
}
