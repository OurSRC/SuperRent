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
    private int BranchID;
    private String BranchName;
    private String BranchPhone;
    private String BranchAddress;

    public Branch() {
    }

    public Branch(String BranchName, String BranchPhone, String BranchAddress) {
        this.BranchName = BranchName;
        this.BranchPhone = BranchPhone;
        this.BranchAddress = BranchAddress;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int BranchID) {
        this.BranchID = BranchID;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getBranchPhone() {
        return BranchPhone;
    }

    public void setBranchPhone(String BranchPhone) {
        this.BranchPhone = BranchPhone;
    }

    public String getBranchAddress() {
        return BranchAddress;
    }

    public void setBranchAddress(String BranchAddress) {
        this.BranchAddress = BranchAddress;
    }

  
}
