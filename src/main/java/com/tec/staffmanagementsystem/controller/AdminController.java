package com.tec.staffmanagementsystem.controller;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.service.AdminService;
import com.tec.staffmanagementsystem.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AdminController implements Initializable {
    @FXML
    private Button btnLogout;


    @FXML
    private Label adminId;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker employmentDate;

    @FXML
    private TextField salary;

    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button reset;

    @FXML
    private Button saveAdmin;

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private TableColumn<Admin, Long> colAdminId;

    @FXML
    private TableColumn<Admin, String> colFirstName;

    @FXML
    private TableColumn<Admin, String> colLastName;

    @FXML
    private TableColumn<Admin, LocalDate> colEmploymentDate;

    @FXML
    private TableColumn<Admin, Long> colSalary;

    @FXML
    private TableColumn<Admin, Long> colPhoneNumber;

    @FXML
    private TableColumn<Admin, String> colPassword;

    @FXML
    private TableColumn<Admin, String> colUsername;

    @FXML
    private TableColumn<Admin, Boolean> colEdit;

    @FXML
    private MenuItem deleteAdmins;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private AdminService adminService;

    private ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void saveAdmin(ActionEvent event) {

        if (validate("First Name", getFirstName(), "[a-zA-Z]+") &&
                validate("Last Name", getLastName(), "[a-zA-Z]+")) {

            if (adminId.getText() == null || adminId.getText() == "") {
                if (validate("UserName", getUserName(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
                        emptyValidation("Password", getPassword().isEmpty())) {

                    Admin admin = new Admin();
                    admin.setFirstName(firstName.getText());
                    admin.setLastName(lastName.getText());
                    admin.setEmploymentDate(employmentDate.getValue());
                    admin.setSalary(Long.parseLong(salary.getText()));
                    admin.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    admin.setPassword(password.getText());
                    admin.setUserName(username.getText());

                    Admin newAdmin = adminService.save(admin);

                    saveAlert(newAdmin);

                }
            } else {
                Admin admin = adminService.find(Long.parseLong(adminId.getText()));
                admin.setFirstName(firstName.getText());
                admin.setLastName(lastName.getText());
                admin.setEmploymentDate(employmentDate.getValue());
                admin.setSalary(Long.parseLong(salary.getText()));
                admin.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                admin.setPassword(password.getText());
                admin.setUserName(username.getText());
                Admin updatedAdmin = adminService.save(admin);
                updateAlert(updatedAdmin);
            }
            clearFields();
            loadAdminDetails();
        }

    }

    @FXML
    private void deleteAdmins(ActionEvent event) {
        List<Admin> admins = adminTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected admins?");
        Optional<ButtonType> action= alert.showAndWait();

        if (action.get() == ButtonType.OK)
            adminService.deleteInBatch(admins);
        loadAdminDetails();
    }

    private void clearFields() {
        adminId.setText(null);
        firstName.clear();
        lastName.clear();
        employmentDate.setValue(null);
        salary.clear();
        phoneNumber.clear();
        password.clear();
        username.clear();

    }

    private void saveAlert(Admin newAdmin) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Admin " + newAdmin.getFirstName() + " " + newAdmin.getLastName() + " has been saved successfully");
        alert.showAndWait();
    }

    private void updateAlert(Admin admin) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Admin " + admin.getFirstName() + " " + admin.getLastName() + " has been updated successfully");
        alert.showAndWait();
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getUserName() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

   @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadAdminDetails();
    }

    private void setColumnProperties() {
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmploymentDate.setCellValueFactory(new PropertyValueFactory<>("employmentDate"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEdit.setCellFactory(cellFactory);
    }


        javafx.util.Callback<TableColumn<Admin, Boolean>, TableCell<Admin, Boolean>> cellFactory =
                new Callback<TableColumn<Admin, Boolean>, TableCell<Admin, Boolean>>() {
            @Override
            public TableCell<Admin, Boolean> call(final TableColumn<Admin, Boolean> param) {
                final TableCell<Admin, Boolean> cell = new TableCell<Admin, Boolean>() {

                    Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                    final Button btnEdit = new Button();
                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                                btnEdit.setOnAction(event -> {
                                Admin admin = getTableView().getItems().get(getIndex());
                                updateAdmin(admin);
                            });
                            btnEdit.setStyle("-fx-background-color: transparent;");
                            javafx.scene.image.ImageView iv = new ImageView();
                            iv.setImage(imgEdit);
                            iv.setPreserveRatio(true);
                            iv.setSmooth(true);
                            iv.setCache(true);
                            btnEdit.setGraphic(iv);

                            setGraphic(btnEdit);
                            setAlignment(Pos.CENTER);
                            setText(null);
                        }
                    }
                    private void updateAdmin(Admin admin) {
                        adminId.setText(Long.toString(admin.getId()));
                        firstName.setText(admin.getFirstName());
                        lastName.setText(admin.getLastName());
                        employmentDate.setValue(admin.getEmploymentDate());
                        salary.setText(Long.toString(admin.getSalary()));
                        phoneNumber.setText(Long.toString(admin.getPhoneNumber()));
                        password.setText(admin.getPassword());
                        username.setText(admin.getUserName());
                    }
                };
                return cell;
            }
        };
        private void loadAdminDetails() {
            adminList.clear();
            adminList.addAll(adminService.findAll());
            adminTable.setItems(adminList);

        }
    private boolean validate(String field, String value, String pattern){
        if(!value.isEmpty()){
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if(m.find() && m.group().equals(value)){
                return true;
            }else{
                validationAlert(field, false);
                return false;
            }
        }else{
            validationAlert(field, true);
            return false;
        }
    }
    private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
            validationAlert(field, true);
            return false;
        }
    }
    private void validationAlert(String field, boolean empty){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
            if(empty) alert.setContentText("Please Enter "+ field);
            else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
    }
}






