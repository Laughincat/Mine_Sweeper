package dev.laughingcat27.util.fx.fxmlloader;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class ExtendedFxmlLoader extends FXMLLoader {

    public void tryLoad() {
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomObject(Object object, Class<?> clazz) {
        // Get url
        String fileName = clazz.getSimpleName() + ".fxml";
        URL url = clazz.getResource(fileName);

        // Set stuff
        this.setRoot(object);
        this.setController(object);
        this.setLocation(url);

        // Load
        tryLoad();
    }
}
