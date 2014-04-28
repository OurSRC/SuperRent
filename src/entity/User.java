package entity;

import ControlObjects.SecurityCtrl;

/**
 * Entity class for user table.
 */
public class User {

    public enum TYPE {

        ERROR(0),
        CUSTOMER(1),
        STAFF(2);

        private int value;

        private TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    private String username;
    private String password;
    private TYPE userType;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPasswordText(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
        //this.password = SecurityCtrl.digestPassword(password);
    }

    /**
     * @return the type
     */
    public TYPE getType() {
        return userType;
    }

    /**
     * @param type the type to set
     */
    public void setType(TYPE type) {
        this.userType = type;
    }

    public void setType(String type) {
        if (type == null) {
            this.userType = null;
        } else {
            this.userType = User.TYPE.valueOf(type);
        }
    }

}
