package entity;

import java.util.Date;

public class Customer extends User {

    private int customerId;
    private String phone;
    private String address;
    private String fistName;
    private String middleName;
    private String lastName;
    private String eMail;
    private String driverLicenseNumber;
    //public ArrayList<CreditCard> creditCards;
    //public ArrayList<Payment> paymentHistory;

    private boolean isClubMember;
    private int point;				//for club member only
    private Date membershipExpiry;              //for club member only

    public Customer(){
    }
    
    public Customer(String username, String password, String phone, String address, String fistName, String middleName, String lastName, String eMail, String driverLicenseNumber, boolean isClubMember, int point, Date membershipExpiry) {
        super.setUsername(username);
        super.setPassword(password);
        super.setType(TYPE.CUSTOMER);
        
        this.phone = phone;
        this.address = address;
        this.fistName = fistName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.driverLicenseNumber = driverLicenseNumber;
        this.isClubMember = isClubMember;
        this.point = point;
        this.membershipExpiry = membershipExpiry;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return the driverLicenseNumber
     */
    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    /**
     * @param driverLicenseNumber the driverLicenseNumber to set
     */
    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    /**
     * @return the isClubMember
     */
    public boolean getIsClubMember() {
        return isClubMember;
    }

    /**
     * @param isClubMember the isClubMember to set
     */
    public void setIsClubMember(boolean isClubMember) {
        this.isClubMember = isClubMember;
    }

    /**
     * @return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * @return the membershipExpiry
     */
    public Date getMembershipExpiry() {
        return membershipExpiry;
    }

    /**
     * @param membershipExpiry the membershipExpiry to set
     */
    public void setMembershipExpiry(Date membershipExpiry) {
        this.membershipExpiry = membershipExpiry;
    }
}
