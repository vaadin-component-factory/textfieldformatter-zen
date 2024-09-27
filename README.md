# Cleave Zen TextField Formatter

[//]: # ([![Published on Vaadin  Directory]&#40;https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg&#41;]&#40;https://vaadin.com/directory/component/textfield-formatter&#41;)

[//]: # ([![Stars on Vaadin Directory]&#40;https://img.shields.io/vaadin-directory/star/textfield-formatter.svg&#41;]&#40;https://vaadin.com/directory/component/textfield-formatter&#41;)

This add-on provides a library for formatting Vaadin TextField input.
This project is built on top the [cleave-zen](https://nosir.github.io/cleave-zen/).

## Description

This project provides a Java API for the [cleave-zen](https://nosir.github.io/cleave-zen/) 
Javascript formatting library and allows the formatting utilities to be applied to 
Vaadin TextField and TextArea components.

Different from an input mask, the actual value of the input field is formatted.

Along with the ability to configure custom formats, the following format 
are supported out of the box:

- Credit card formatting
- Numeral formatting
- Date / Time formatting
- Custom delimiter, prefix and blocks pattern

## Development instructions

Build the project and install the add-on locally:
```
mvn clean install
```

### Running the demo/test application
1. Import this maven project to your favourite IDE
2. Run `mvn -Pdevelopment jetty:run`, 
3. Navigate to http://localhost:8080

## How to use it

The best place to find examples of usage is by viewing the demo application in the test directory.
Below are a few basic examples to give and high-level view of how the API works.

### Numeric formatting

```java
// 1234567.12-> $1,234,567.12
TextField tf = new TextField();
new NumeralFieldFormatter.Builder()
        .thousandsGroupStyle(ThousandsGroupStyle.THOUSAND)
        .stripLeadingZeroes(false)
        .prefix("$")
        .build()
        .extend(tf);
add(tf);
```

```java
// 1234567,12-> 1.234.567,12€
TextField tf = new TextField();
new NumeralFieldFormatter.Builder()
        .decimalMark(",")
        .delimiter(".")
        .thousandsGroupStyle(ThousandsGroupStyle.THOUSAND)
        .stripLeadingZeroes(false)
        .prefix("€", true)
        .build()
        .extend(tf);
add(tf);
```

### IBAN formatting

123412341234 -> 1234 1234 1234

```java
TextField tf = new TextField();
IBANFormatter.fromIBANLength(12).extend(tf);
add(tf);
```

### Credit card formatting

```java
TextField tf = new TextField();
tf.setPlaceholder("Insert credit card number");

CreditCardFieldFormatter formatter = new CreditCardFieldFormatter();
formatter.extend(tf);

// library will notify when type of card changes (eg: VISA)
formatter.addCreditCardChangedListener(l -> Notification.show("" + l.getCreditCardType()));

add(tf);
```

## Missing features or bugs

You can report any issue or missing feature on [GitHub](https://github.com/vaadin-component-factory/textfieldformatter-zen/issues).

## License

Apache License 2.0.