package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import tools.jackson.databind.JsonNode;

public class FormatPhoneOptions extends AbstractCleaveConfiguration {

    public String country;
    public boolean formatNational;

    protected JsonNode toJson() {
        var json = factory.objectNode();
        ifNotNull(country, value -> json.put("country", value));
        ifNotNull(formatNational, value -> json.put("formatNational", formatNational));
        return json;
    }

    @Override
    protected String getFormatType() {
        return "phone";
    }
}
