package com.tec.staffmanagementsystem.view;


import java.util.ResourceBundle;
/**
 *
 * @author tec
 */
public enum FxmlView {
    STUDENT{
        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getTitle() {

            return getStringFromResourceBundle("student.title");
        }

        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getFxmlFile() {

            return "/fxml/Student.fxml";
        }
    },

    ENGINEER{
        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getTitle() {

            return getStringFromResourceBundle("engineer.title");
        }

        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getFxmlFile() {

            return "/fxml/Engineer.fxml";
        }

    },
    ADMIN{
        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getTitle() {

            return getStringFromResourceBundle("admin.title");
        }

        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getFxmlFile() {

            return "/fxml/Admin.fxml";
        }
    },
    LOGIN{
        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getTitle() {

            return getStringFromResourceBundle("login.title");
        }

        @Override

/**
 *
 * It is a constructor.
 *
 */
        public String getFxmlFile() {

            return "/fxml/Login.fxml";
        }
    };


    /**
     *
     * It is a constructor.
     *
     *
     * @throws ;

    public abstract String getFxmlFile(
     */
    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){

        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
