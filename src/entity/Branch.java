/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Jingchuan Chen
 */
public class Branch {
    private int branchID;
    private String branchName;
    private String branchPhone;
    private String branchAddress;

    public Branch() {
    }

    public Branch(String BranchName, String BranchPhone, String BranchAddress) {
        this.branchName = BranchName;
        this.branchPhone = BranchPhone;
        this.branchAddress = BranchAddress;
    }

    /**
     * @return the branchID
     */
    public int getBranchID() {
        return branchID;
    }

    /**
     * @param branchID the branchID to set
     */
    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the branchPhone
     */
    public String getBranchPhone() {
        return branchPhone;
    }

    /**
     * @param branchPhone the branchPhone to set
     */
    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    /**
     * @return the branchAddress
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * @param branchAddress the branchAddress to set
     */
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
