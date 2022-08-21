

package com.tec.staffmanagementsystem.config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ResourceBundle;
import com.tec.staffmanagementsystem.logging.ExceptionWriter;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
/**
 *
 * @author tec
 */


@Configuration

/**
 * The class Application config
 */
public class AppJavaConfig {

    @Autowired
    SpringFXMLLoader springFXMLLoader;

    /**
     * Useful when dumping stack trace to a string for logging.
     * @return ExceptionWriter contains logging utility methods
     */
    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {

        return new ExceptionWriter(new StringWriter());
    }

    @Bean

/**
 *
 * Resource bundle
 *
 * @return ResourceBundle
 */
    public ResourceBundle resourceBundle() {

        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap

/**
 *
 * Stage manager
 *
 * @param stage  the stage
 * @return StageManager
 * @throws   IOException
 */
    public StageManager stageManager(Stage stage) throws IOException {

        return new StageManager(springFXMLLoader, stage);
    }

}

