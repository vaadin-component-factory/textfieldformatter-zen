package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

public class FormatCreditCardOptions extends AbstractCleaveConfiguration {
    public Boolean creditCardStrictMode;
    public String delimiter;
    public Boolean delimiterLazyShow;

    protected JsonNode toJson() {
        ObjectNode json = factory.objectNode();
        ifNotNull(creditCardStrictMode, value -> json.put("strictMode", value));
        ifNotNull(delimiter, value -> json.put("delimiter", value));
        ifNotNull(delimiterLazyShow, value -> json.put("delimiterLazyShow", value));
        return json;
    }

    @Override
    protected String getFormatType() {
        return "creditCard";
    }
}
