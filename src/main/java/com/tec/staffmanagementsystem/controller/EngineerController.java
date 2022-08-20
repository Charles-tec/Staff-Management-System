package com.tec.staffmanagementsystem.controller;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.service.EngineerService;
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
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EngineerController implements Initializable {

    @FXML
    private Button btnLogout;


    @FXML
    private Label engineerId;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker employmentDate;

    @FXML
    private TextField salary;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button reset;

    @FXML
    private Button saveAdmin;

    @FXML
    private TableView<Engineer> engineerTable;

    @FXML
    private TableColumn<Engineer, Long> colEngineerId;

    @FXML
    private TableColumn<Engineer, String> colFirstName;

    @FXML
    private TableColumn<Engineer, String> colLastName;

    @FXML
    private TableColumn<Engineer, LocalDate> colEmploymentDate;

    @FXML
    private TableColumn<Engineer, Long> colSalary;

    @FXML
    private TableColumn<Engineer, String> colEmail;

    @FXML
    private TableColumn<Engineer, String> colPassword;

    @FXML
    private TableColumn<Engineer, String> colUsername;

    @FXML
    private TableColumn<Engineer, Boolean> colEdit;

    @FXML
    private MenuItem deleteEngineers;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private EngineerService engineerService;

    private ObservableList<Engineer> engineerList = FXCollections.observableArrayList();

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
    private void saveEngineer(ActionEvent event) {

        if (validate("First Name", getFirstName(), "[a-zA-Z]+") &&
                validate("Last Name", getLastName(), "[a-zA-Z]+")) {

            if (engineerId.getText() == null || engineerId.getText() == "") {
                if (validate("UserName", getUserName(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
                        emptyValidation("Password", getPassword().isEmpty())) {

                    Engineer engineer = new Engineer();
                    engineer.setFirstName(firstName.getText());
                    engineer.setLastName(lastName.getText());
                    engineer.setEmploymentDate(employmentDate.getValue());
                    engineer.setSalary(Long.parseLong(salary.getText()));
                    engineer.setEmail(email.getText());
                    engineer.setPassword(password.getText());
                    engineer.setUserName(username.getText());

                    Engineer newEngineer = engineerService.save(engineer);

                    saveAlert(newEngineer);

                }
            } else {
                Engineer engineer = engineerService.find(Long.parseLong(engineerId.getText()));
                engineer.setFirstName(firstName.getText());
                engineer.setLastName(lastName.getText());
                engineer.setEmploymentDate(employmentDate.getValue());
                engineer.setSalary(Long.parseLong(salary.getText()));
                engineer.setEmail(email.getText());
                engineer.setPassword(password.getText());
                engineer.setUserName(username.getText());
                Engineer updatedEngineer = engineerService.save(engineer);
                updateAlert(updatedEngineer);
            }
            clearFields();
            loadEngineerDetails();
        }

    }

    @FXML
    private void deleteEngineers(ActionEvent event) {
        List<Engineer> engineers = engineerTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected engineers?");
        Optional<ButtonType> action= alert.showAndWait();

        if (action.get() == ButtonType.OK)
            engineerService.deleteInBatch(engineers);
        loadEngineerDetails();
    }

    private void clearFields() {
        engineerId.setText(null);
        firstName.clear();
        lastName.clear();
        employmentDate.setValue(null);
        salary.clear();
        email.clear();
        password.clear();
        username.clear();

    }

    private void saveAlert(Engineer newEngineer) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Engineer " + newEngineer.getFirstName() + " " + newEngineer.getLastName() + " has been saved successfully");
        alert.showAndWait();
    }

    private void updateAlert(Engineer engineer) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Engineer " + engineer.getFirstName() + " " + engineer.getLastName() + " has been updated successfully");
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
        engineerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadEngineerDetails();
    }

    private void setColumnProperties() {
        colEngineerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmploymentDate.setCellValueFactory(new PropertyValueFactory<>("employmentDate"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEdit.setCellFactory(cellFactory);
    }


    javafx.util.Callback<TableColumn<Engineer, Boolean>, TableCell<Engineer, Boolean>> cellFactory =
            new Callback<TableColumn<Engineer, Boolean>, TableCell<Engineer, Boolean>>() {
                @Override
                public TableCell<Engineer, Boolean> call(final TableColumn<Engineer, Boolean> param) {
                    final TableCell<Engineer, Boolean> cell = new TableCell<Engineer, Boolean>() {

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
                                    Engineer engineer = getTableView().getItems().get(getIndex());
                                    updateEngineer(engineer);
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
                        private void updateEngineer(Engineer engineer) {
                            engineerId.setText(Long.toString(engineer.getId()));
                            firstName.setText(engineer.getFirstName());
                            lastName.setText(engineer.getLastName());
                            employmentDate.setValue(engineer.getEmploymentDate());
                            salary.setText(Long.toString(engineer.getSalary()));
                            email.setText(engineer.getEmail());
                            password.setText(engineer.getPassword());
                            username.setText(engineer.getUserName());
                        }
                    };
                    return cell;
                }
            };
    private void loadEngineerDetails() {
       engineerList.clear();
        engineerList.addAll(engineerService.findAll());
        engineerTable.setItems(engineerList);

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
