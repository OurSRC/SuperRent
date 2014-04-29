package Account;

/**
 * Entity class for staff table.
 */
public class Staff extends User {

    /**
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public enum STATUS {

        ACTIVE(1),
        DEACTIVATED(2);

        private int value;

        private STATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    public enum TYPE {

        CLERK(1),
        MANAGER(2),
        ADMIN(3);

        private int value;

        private TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    private int staffId;
    private int branchId;
    private String fistName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    //public Date onJobDate;
    private STATUS status;
    private TYPE staffType;

    public Staff(int branchId, String firstName, String middleName, String lastName, String email,
            String phone, STATUS status, TYPE staffType, String username, String password) {
        setUsername(username);
        setPassword(password);
        setType(User.TYPE.STAFF);
        this.branchId = branchId;
        this.fistName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.staffType = staffType;
    }

    public Staff() {
    }

    /**
     * @return the staffId
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    /**
     * @return the fistName
     */
    public String getFistName() {
        return fistName;
    }

    /**
     * @param fistName the fistName to set
     */
    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the eMail
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the eMail to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the status
     */
    public STATUS getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void setStatus(String status) {
        if (status == null) {
            this.status = null;
        } else {
            this.status = STATUS.valueOf(status);
        }
    }

    /**
     * @return the staffType
     */
    public TYPE getStaffType() {
        return staffType;
    }

    /**
     * @param staffType the staffType to set
     */
    public void setStaffType(TYPE staffType) {
        this.staffType = staffType;
    }

    public void setStaffType(String staffType) {
        if (staffType == null) {
            this.staffType = null;
        } else {
            this.staffType = TYPE.valueOf(staffType);
        }
    }

    public String getStaffType(String staffType) {
        return this.staffType.toString();
    }

}
