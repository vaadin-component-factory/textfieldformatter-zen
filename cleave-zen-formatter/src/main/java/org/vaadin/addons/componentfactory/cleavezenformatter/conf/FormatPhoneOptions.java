package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import elemental.json.Json;
import elemental.json.JsonObject;

public class FormatPhoneOptions extends AbstractCleaveConfiguration {

    public String country;
    public boolean formatNational;

    protected JsonObject toJson() {
        JsonObject json = Json.createObject();
        ifNotNull(country, value -> json.put("country", value));
        ifNotNull(formatNational, value -> json.put("formatNational", formatNational));
        return json;
    }

    @Override
    protected String getFormatType() {
        return "phone";
    }
}
