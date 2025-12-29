package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import tools.jackson.databind.JsonNode;

public class FormatDateOptions extends AbstractCleaveConfiguration {

    public String[] datePattern;
    public String dateMax;
    public String dateMin;
    public String delimiter;
    public Boolean delimiterLazyShow;

    protected JsonNode toJson() {
        var json = factory.objectNode();
        ifNotNull(delimiter, value -> json.put("delimiter", value));
        ifNotNull(delimiterLazyShow, value -> json.put("delimiterLazyShow", value));
        ifNotNullArray(datePattern, value -> json.set("datePattern", value));
        ifNotNull(dateMax, value -> json.put("dateMax", value));
        ifNotNull(dateMin, value -> json.put("dateMin", value));

        return json;
    }

    @Override
    protected String getFormatType() {
        return "date";
    }
}
