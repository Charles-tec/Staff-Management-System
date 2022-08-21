

package com.tec.staffmanagementsystem.config;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author tec
 */
/**
 * Will load the FXML hierarchy as specified in the load method and register
 * Spring as the FXML Controller Factory. Allows Spring and Java FX to coexist
 * once the Spring Application context has been bootstrapped.
 */


@Component
public class SpringFXMLLoader {
    private final ResourceBundle resourceBundle;
    private final ApplicationContext context;

    @Autowired

/**
 *
 * It is a constructor. 
 *
 * @param context  the context
 * @param resourceBundle  the resource bundle
 */
    public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {

        this.resourceBundle = resourceBundle;
        this.context = context;
    }


    /**
     *
     * Load
     *
     * @param fxmlPath  the fxml path
     * @return Parent
     * @throws   IOException
     */
    public Parent load(String fxmlPath) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean); //Spring now FXML Controller Factory
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader.load();
    }
}
