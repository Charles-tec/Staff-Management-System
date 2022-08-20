package com.tec.staffmanagementsystem.controller;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.service.AdminService;
import com.tec.staffmanagementsystem.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public class LoginController implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    private Label lblLogin;

    @Autowired
    private AdminService adminService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private void login(ActionEvent event) throws IOException {
        if(adminService.authenticate(getUserName(), getPassword())){
            stageManager.switchScene(FxmlView.ADMIN);


        }else{
            lblLogin.setText("Login Failed.");
        }
    }

    public String getUserName() {
        return userName.getText();
    }
    public String getPassword() {
        return password.getText();
    }
   @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        // TODO
    }

}
