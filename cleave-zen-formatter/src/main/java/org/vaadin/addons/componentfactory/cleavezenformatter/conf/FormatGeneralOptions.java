package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import tools.jackson.databind.JsonNode;

public class FormatGeneralOptions extends AbstractCleaveConfiguration {

    public int[] blocks;
    public String delimiter;
    public Boolean delimiterLazyShow;
    public String[] delimiters;
    public Boolean uppercase;
    public Boolean lowercase;
    public Boolean numericOnly;
    public String prefix;

    protected JsonNode toJson() {
        var json = factory.objectNode();
        ifNotNull(delimiter, value -> json.put("delimiter", value));
        ifNotNullArray(blocks, value -> json.set("blocks", value));
        ifNotNullArray(delimiters, value -> json.set("delimiters", value));
        ifNotNull(delimiterLazyShow, value -> json.put("delimiterLazyShow", value));
        ifNotNull(uppercase, value -> json.put("uppercase", value));
        ifNotNull(lowercase, value -> json.put("lowercase", value));
        ifNotNull(numericOnly, value -> json.put("numericOnly", value));
        ifNotNull(prefix, value -> json.put("prefix", value));

        return json;
    }

    @Override
    protected String getFormatType() {
        return "general";
    }
}
