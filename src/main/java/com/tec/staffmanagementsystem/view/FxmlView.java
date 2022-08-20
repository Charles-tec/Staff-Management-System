package com.tec.staffmanagementsystem.view;


import java.util.ResourceBundle;

public enum FxmlView {
    STUDENT{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("student.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Student.fxml";
        }
    },

    ENGINEER{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("engineer.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Engineer.fxml";
        }

    },
    ADMIN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("admin.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Admin.fxml";
        }
    },
    LOGIN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    };
    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
