package com.tec.staffmanagementsystem.config;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import com.tec.staffmanagementsystem.view.FxmlView;

import org.slf4j.Logger;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StageManager {

    private static final Logger LOG=getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = stage;
    }
    public void switchScene(final FxmlView view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());

        show(viewRootNodeHierarchy, view.getTitle());
    }
    private void show(final Parent rootnode, String title) {
        Scene scene = prepareScene(rootnode);

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();

        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }
    private Scene prepareScene(Parent rootnode){
        Scene scene = primaryStage.getScene();

        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }
    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootnode = null;
        try {
            rootnode = springFXMLLoader.load(fxmlFilePath);
        } catch (IOException exception) {
            logAndExit ("Unable to load view node hierarchy from file" + fxmlFilePath,  exception);
        }
        return rootnode;
    }
    private void logAndExit(String message, Exception exception) {
        LOG.error(message, exception);
        Platform.exit();
    }
}
