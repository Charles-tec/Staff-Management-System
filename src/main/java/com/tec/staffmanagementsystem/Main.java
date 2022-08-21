package com.tec.staffmanagementsystem;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.view.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author tec
 */
@SpringBootApplication

/**
 * The class Main extends application
 */
public class Main extends Application {
    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;


    /**
     *
     * Main
     *
     *
     */
    public static void main(final String[] args) {

        Application.launch(args);
    }

    @Override

/**
 *
 * Init
 *
 * @param Exception  the exception
 * @throws   Exception
 */
    public void init() throws Exception {

        springContext = springBootApplicationContext();
    }

    @Override

/**
 *
 * Start
 *
 * @param stage  the stage
 * @throws   Exception
 */
    public void start(Stage stage) throws Exception {

        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override

/**
 *
 * Stop
 *
 * @param Exception  the exception
 * @throws   Exception
 */
    public void stop() throws Exception {

        springContext.close();
    }


    protected void displayInitialScene() {

        stageManager.switchScene(FxmlView.LOGIN);

    }



    /**
     *
     * Spring boot application context
     *
     * @return ConfigurableApplicationContext
     */
    private ConfigurableApplicationContext springBootApplicationContext() {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }

}
