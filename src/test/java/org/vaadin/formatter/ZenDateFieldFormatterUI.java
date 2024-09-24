package org.vaadin.formatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.formatter.ZenDateFieldFormatterUI.*;
import org.vaadin.textfieldformatter.AbstractTest;
import org.vaadin.textfieldformatter.RouteParams;
import org.vaadin.textfieldformatter.UITestConfiguration;

import java.time.LocalDate;

@RouteParams({ Max.class, Min.class, MinMax.class, FinnishDate.class, AmericanDate.class, MonthYear.class })
public class ZenDateFieldFormatterUI extends AbstractTest {

    public static class Max extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("Date before Feb 3, 2019");
            new ZenDateFieldFormatter.Builder().dateMax(LocalDate.of(2019, 2, 3)).build().extend(field);
            return field;
        }
    }

    public static class Min extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("Date after Feb 3, 2019");
            new ZenDateFieldFormatter.Builder().dateMin(LocalDate.of(2019, 2, 4)).build().extend(field);
            field.addValueChangeListener(e -> {
                Notification.show(e.getValue());
            });
            return field;
        }
    }

    public static class MinMax extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("Accepts only Feb 4, 2019");
            new ZenDateFieldFormatter.Builder().dateMax(LocalDate.of(2019, 2, 4)).dateMin(LocalDate.of(2019, 2, 4)).build()
                    .extend(field);
            return field;
        }

    }

    public static class FinnishDate extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("dd.MM.yyyy");
            new ZenDateFieldFormatter.Builder().datePattern("ddMMyyyy").delimiter(".").build().extend(field);
            return field;
        }

    }

    public static class AmericanDate extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("MM-dd-yyyy");
            new ZenDateFieldFormatter.Builder().datePattern("MMddyyyy").build().extend(field);
            return field;
        }

    }

    public static class MonthYear extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("MM-yyyy");
            new ZenDateFieldFormatter.Builder().datePattern("MMyyyy").build().extend(field);
            return field;
        }

    }

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        return configuration.getTestComponent();
    }

}
