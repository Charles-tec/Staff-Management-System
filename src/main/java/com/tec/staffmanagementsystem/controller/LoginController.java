package com.tec.staffmanagementsystem.controller;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.service.AdminService;
import com.tec.staffmanagementsystem.service.EngineerService;
import com.tec.staffmanagementsystem.service.StudentService;
import com.tec.staffmanagementsystem.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public class LoginController implements Initializable {
    @FXML
    private SplitMenuButton btnLogin;

    @FXML
    private Button btnLogin1;

    @FXML
    private Button btnLogin2;


    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    private Label lblLogin;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private StudentService studentService;


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
    @FXML
    private void login1(ActionEvent event) throws IOException {
        if(engineerService.authenticate(getUserName(), getPassword())){
            stageManager.switchScene(FxmlView.ENGINEER);


        }else{
            lblLogin.setText("Login Failed.");
        }
    }
    @FXML
    private void login2(ActionEvent event) throws IOException {
        if(studentService.authenticate(getUserName(), getPassword())){
            stageManager.switchScene(FxmlView.STUDENT);


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
