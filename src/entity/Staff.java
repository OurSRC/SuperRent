package entity;

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
        DISACTIVE(2);
        
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
    private String eMail;
    private String phone;
    //public Date onJobDate;
    private STATUS status;
    private TYPE staffType;

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
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail the eMail to set
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
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

}
