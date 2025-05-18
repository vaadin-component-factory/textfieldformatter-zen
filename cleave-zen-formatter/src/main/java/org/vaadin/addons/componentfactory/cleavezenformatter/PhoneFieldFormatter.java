package org.vaadin.addons.componentfactory.cleavezenformatter;

import org.vaadin.addons.componentfactory.cleavezenformatter.conf.CleaveExtension;
import org.vaadin.addons.componentfactory.cleavezenformatter.conf.FormatPhoneOptions;

import com.vaadin.flow.component.textfield.TextField;

public class PhoneFieldFormatter extends CleaveExtension<FormatPhoneOptions> {

	public static String DEFAULT_COUNTRY = "US";


	/**
	 * Creates a TextFieldFormatter for numeral fields. By default has delimiter ','
	 * and decimal mark '.'. Integer scale is not limited and shows 2 decimals.
	 */
	public PhoneFieldFormatter() {
		this(DEFAULT_COUNTRY);
	}

	@Override
	protected FormatPhoneOptions createDefaultConfiguration() {
		return new FormatPhoneOptions();
	}

	public PhoneFieldFormatter(String country) {
		getConfiguration().country = country;
	}

	public PhoneFieldFormatter(String country, boolean formatNational) {
		getConfiguration().country = country;
		getConfiguration().formatNational = formatNational;
	}

	/**
	 * Attaches this extension to a TextField. Extension cannot be moved to another
	 * TextField again.
	 * 
	 * @param textField TextField to attach this extension to
	 */
	public void extend(TextField textField) {
		super.extend(textField);
	}

}
