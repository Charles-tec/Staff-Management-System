package com.tec.staffmanagementsystem.controller;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.entities.Student;
import com.tec.staffmanagementsystem.service.StudentService;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class StudentController implements Initializable {
    @FXML
    private Button btnLogout;


    @FXML
    private Label studentId;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker employmentDate;

    @FXML
    private TextField salary;

    @FXML
    private TextField engineerId;

    @FXML
    private TextField university;
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button reset;

    @FXML
    private Button saveStudent;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Long> colStudentId;

    @FXML
    private TableColumn<Student, String> colFirstName;

    @FXML
    private TableColumn<Student, String> colLastName;

    @FXML
    private TableColumn<Student, LocalDate> colEmploymentDate;

    @FXML
    private TableColumn<Student, Long> colSalary;

    @FXML
    private TableColumn<Student, Long> colEngineerId;

    @FXML
    private TableColumn<Student, Long> colUniversity;
    @FXML
    private TableColumn<Student, String> colPassword;

    @FXML
    private TableColumn<Student, String> colUsername;

    @FXML
    private TableColumn<Admin, Boolean> colEdit;

    @FXML
    private MenuItem deleteAdmins;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private StudentService studentService;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

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
    private void saveStudent(ActionEvent event) {
        Engineer engineer=new Engineer();

        if (validate("First Name", getFirstName(), "[a-zA-Z]+") &&
                validate("Last Name", getLastName(), "[a-zA-Z]+")) {

            if (studentId.getText() == null || studentId.getText() == "") {
                if (validate("UserName", getUserName(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
                        emptyValidation("Password", getPassword().isEmpty())) {

                    Student student = new Student();

                    student.setFirstName(firstName.getText());
                    student.setLastName(lastName.getText());
                    student.setEmploymentDate(employmentDate.getValue());
                    student.setSalary(Long.parseLong(salary.getText()));
                    student.setEngineerId(engineer.getId());
                    student.setUniversity(university.getText());
                   student.setPassword(password.getText());
                   student.setUserName(username.getText());

                    Student newStudent = studentService.save(student);

                    saveAlert(newStudent);

                }
            } else {
                Student student = studentService.find(Long.parseLong(studentId.getText()));
                student.setFirstName(firstName.getText());
                student.setLastName(lastName.getText());
                student.setEmploymentDate(employmentDate.getValue());
                student.setSalary(Long.parseLong(salary.getText()));
                student.setEngineerId(engineer.getId());
                student.setUniversity(university.getText());
                student.setPassword(password.getText());
                student.setUserName(username.getText());
                Student updatedStudent = studentService.save(student);
                updateAlert(updatedStudent);
            }
            clearFields();
            loadAdminDetails();
        }

    }

    @FXML
    private void deleteStudents(ActionEvent event) {
        List<Student> students = adminable.getSelectionModel().getSelectedItems();

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

    private void saveAlert(Student newAdmin) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Admin " + newAdmin.getFirstName() + " " + newAdmin.getLastName() + " has been saved successfully");
        alert.showAndWait();
    }

    private void updateAlert(Student admin) {
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

    // @Override
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
