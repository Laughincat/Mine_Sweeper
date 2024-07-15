package dev.laughingcat27.util.fx.controls;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class IntegerField extends TextField {

    public IntegerField() {
        this.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
    }

    public int getInt() throws NumberFormatException {
        return Integer.parseInt(super.getText());
    }

    public Integer getInteger() {
        try {
            return Integer.parseInt(super.getText());
        } catch (NumberFormatException e) {
            // When nothing is inputted, the parseInt will shit its pants,
            // so this can return null to let the caller know the field is empty
            return null;
        }
    }
}
