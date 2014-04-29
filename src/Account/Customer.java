package Account;

import java.util.Date;

/**
 * Entity class for Customer table
 */
public class Customer extends User {

    private int customerId;
    private String phone;
    private String address;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String driverLicenseNumber;

    private boolean isClubMember;
    private int point;				//for club member only
    private Date membershipExpiry;              //for club member only

    public Customer() {
    }

    public Customer(String username, String password, String phone, String address,
            String fistName, String middleName, String lastName, String email,
            String driverLicenseNumber, boolean isClubMember, int point, Date membershipExpiry) {
        super.setUsername(username);
        super.setPassword(password);
        super.setType(TYPE.CUSTOMER);

        this.phone = phone;
        this.address = address;
        this.firstName = fistName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
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
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param fistName the fistName to set
     */
    public void setFirstName(String fistName) {
        this.firstName = fistName;
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
     * @param Email the eMail to set
     */
    public void setEmail(String Email) {
        this.email = Email;
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
