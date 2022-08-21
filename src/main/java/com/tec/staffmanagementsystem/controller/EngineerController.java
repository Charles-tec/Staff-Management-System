package com.tec.staffmanagementsystem.controller;

import com.tec.staffmanagementsystem.config.StageManager;
import com.tec.staffmanagementsystem.entities.Admin;
import com.tec.staffmanagementsystem.entities.Engineer;
import com.tec.staffmanagementsystem.service.EngineerService;
import com.tec.staffmanagementsystem.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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


/**
 *
 * @author tec
 */
@Controller

/**
 * The class Engineer controller implements initializable
 */
public class EngineerController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private TextField searchEngineers;
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

/**
 *
 * Exit
 *
 * @param event  the event
 */
    private void exit(ActionEvent event) {

        Platform.exit();
    }

    @FXML

/**
 *
 * Logout
 *
 * @param event  the event
 * @throws   IOException
 */
    private void logout(ActionEvent event) throws IOException {

        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML

/**
 *
 * Save engineer
 *
 * @param event  the event
 */
    private void saveEngineer(ActionEvent event) {


        if (true){

            if (engineerId.getText() == null || engineerId.getText() == "") {
                if (true) {

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
    private void filterData(){
        FilteredList<Engineer> searchedData = new FilteredList<>(engineerList, p -> true);
        searchEngineers.textProperty().addListener((observable, oldValue, newValue) -> {
            searchedData.setPredicate(engineer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (engineer.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (engineer.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (engineer.getUserName().toLowerCase().contains(lowerCaseFilter)) {
                    engineerTable.getSelectionModel().select(engineer);
                    showAdminDetails(engineer);
                    return true;
                }
                return false;
            });
        });
        SortedList<Engineer> sortedData = new SortedList<>(searchedData);
        sortedData.comparatorProperty().bind(engineerTable.comparatorProperty());
        engineerTable.setItems(sortedData);


    }
    private void showAdminDetails(Engineer engineer) {
        engineerId.setText(String.valueOf(engineer.getId()));
        firstName.setText(engineer.getFirstName());
        lastName.setText(engineer.getLastName());
        employmentDate.setValue(engineer.getEmploymentDate());
        salary.setText(String.valueOf(engineer.getSalary()));
        email.setText(engineer.getEmail());
        password.setText(engineer.getPassword());
        username.setText(engineer.getUserName());

    }
    public void searchRequestFocus(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                searchEngineers.requestFocus();
            }
        });
    }


    @FXML

/**
 *
 * Delete engineers
 *
 * @param event  the event
 */
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


    /**
     *
     * Clear fields
     *
     */
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


    /**
     *
     * Save alert
     *
     * @param newEngineer  the new engineer
     */
    private void saveAlert(Engineer newEngineer) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Engineer " + newEngineer.getFirstName() + " " + newEngineer.getLastName() + " has been saved successfully");
        alert.showAndWait();
    }


    /**
     *
     * Update alert
     *
     * @param engineer  the engineer
     */
    private void updateAlert(Engineer engineer) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Engineer " + engineer.getFirstName() + " " + engineer.getLastName() + " has been updated successfully");
        alert.showAndWait();
    }


    /**
     *
     * Gets the first name
     *
     * @return the first name
     */
    public String getFirstName() {

        return firstName.getText();
    }


    /**
     *
     * Gets the last name
     *
     * @return the last name
     */
    public String getLastName() {

        return lastName.getText();
    }


    /**
     *
     * Gets the user name
     *
     * @return the user name
     */
    public String getUserName() {

        return username.getText();
    }


    /**
     *
     * Gets the password
     *
     * @return the password
     */
    public String getPassword() {

        return password.getText();
    }

    @Override

/**
 *
 * Initialize
 *
 * @param location  the location
 * @param resources  the resources
 */
    public void initialize(URL location, ResourceBundle resources) {

        engineerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadEngineerDetails();
        filterData();
        searchRequestFocus();
    }


    /**
     *
     * Sets the column properties
     *
     */
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

/**
 *
 * Call
 *
 * @param TableColumn<Engineer  the table column< engineer
 * @param param  the param
 * @return Boolean>
 */
                public TableCell<Engineer, Boolean> call(final TableColumn<Engineer, Boolean> param) {

                    final TableCell<Engineer, Boolean> cell = new TableCell<Engineer, Boolean>() {

                        Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                        final Button btnEdit = new Button();
                        @Override

/**
 *
 * Update item
 *
 * @param item  the item
 * @param empty  the empty
 */
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

                        /**
                         *
                         * Update engineer
                         *
                         * @param engineer  the engineer
                         */
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

    /**
     *
     * Load engineer details
     *
     */
    private void loadEngineerDetails() {

        engineerList.clear();
        engineerList.addAll(engineerService.findAll());
        engineerTable.setItems(engineerList);

    }

    /**
     *
     * Validate
     *
     * @param field  the field
     * @param value  the value
     * @param pattern  the pattern
     * @return boolean
     */
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

    /**
     *
     * Empty validation
     *
     * @param field  the field
     * @param empty  the empty
     * @return boolean
     */
    private boolean emptyValidation(String field, boolean empty){

        if(!empty){
            return true;
        }else{
            validationAlert(field, true);
            return false;
        }
    }

    /**
     *
     * Validation alert
     *
     * @param field  the field
     * @param empty  the empty
     */
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
