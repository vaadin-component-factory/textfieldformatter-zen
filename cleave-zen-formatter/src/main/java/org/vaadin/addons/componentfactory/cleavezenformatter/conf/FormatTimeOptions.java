package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import tools.jackson.databind.JsonNode;

public class FormatTimeOptions extends AbstractCleaveConfiguration {

    public String delimiter;
    public Boolean delimiterLazyShow;
    public String timeFormat;
    public String timePattern;

    protected JsonNode toJson() {
        var json = factory.objectNode();
        ifNotNull(delimiter, value -> json.put("delimiter", value));
        ifNotNull(delimiterLazyShow, value -> json.put("delimiterLazyShow", value));
        ifNotNull(timeFormat, value -> json.put("timeFormat", value));
        ifNotNull(timePattern, value -> json.put("timePattern", value));
        return json;
    }

    @Override
    protected String getFormatType() {
        return "time";
    }
}
