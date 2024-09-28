package org.vaadin.cleavezenformatter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.cleavezenformatter.DateFieldFormatterUI.*;

import java.time.LocalDate;

@RouteParams({ Max.class, Min.class, MinMax.class, FinnishDate.class, AmericanDate.class, MonthYear.class })
public class DateFieldFormatterUI extends AbstractTest {

    public static class Max extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("Date before Feb 3, 2019");
            new DateFieldFormatter.Builder().dateMax(LocalDate.of(2019, 2, 3)).build().extend(field);
            return field;
        }
    }

    public static class Min extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("Date after Feb 3, 2019");
            new DateFieldFormatter.Builder().dateMin(LocalDate.of(2019, 2, 4)).build().extend(field);
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
            new DateFieldFormatter.Builder().dateMax(LocalDate.of(2019, 2, 4)).dateMin(LocalDate.of(2019, 2, 4)).build()
                    .extend(field);
            return field;
        }

    }

    public static class FinnishDate extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("dd.MM.yyyy");
            new DateFieldFormatter.Builder().datePattern("ddMMyyyy").delimiter(".").build().extend(field);
            return field;
        }

    }

    public static class AmericanDate extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("MM-dd-yyyy");
            new DateFieldFormatter.Builder().datePattern("MMddyyyy").build().extend(field);
            return field;
        }

    }

    public static class MonthYear extends UITestConfiguration {

        @Override
        public Component getTestComponent() {
            TextField field = new TextField("MM-yyyy");
            new DateFieldFormatter.Builder().datePattern("MMyyyy").build().extend(field);
            return field;
        }

    }

    @Override
    public Component getTestComponent(UITestConfiguration configuration) {
        return configuration.getTestComponent();
    }

}
