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

    public int getInteger() {
        return Integer.parseInt(super.getText());
    }
}
