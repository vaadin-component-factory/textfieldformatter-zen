package org.vaadin.addons.componentfactory.cleavezenformatter.conf;

import java.util.function.Consumer;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.JsonNodeFactory;

public abstract class AbstractCleaveConfiguration {

    protected abstract String getFormatType();
    static protected JsonNodeFactory factory = new JsonNodeFactory();

    protected abstract JsonNode toJson();
    
    protected  <T> void ifNotNull(T value, Consumer<T> put) {
        if (value != null) {
            put.accept(value);
        }
    }

    protected void ifNotNullArray(int[] value, Consumer<ArrayNode> put) {
        if (value != null) {
            var array = factory.arrayNode(value.length);
            for (int i = 0; i < value.length; i++) {
                array.add(value[i]);
            }
            put.accept(array);
        }
    }

    protected void ifNotNullArray(String[] value, Consumer<ArrayNode> put) {
        if (value != null) {
            var array = factory.arrayNode(value.length);
            for (int i = 0; i < value.length; i++) {
                array.add(value[i]);
            }
            put.accept(array);
        }
    }
}
