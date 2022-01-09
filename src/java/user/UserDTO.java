/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String userID;
    private String fullName;
    private String address;
    private String phone;
    private String password;
    private String roleID;

    public UserDTO() {}
    
    public UserDTO(String userID, String fullName, String address, String phone, String password, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.roleID = roleID;
    }

    public UserDTO(String userID, String fullName, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
    }
    


    public String getUserId() {
        return userID;
    }

    public void setUserId(String userId) {
        this.userID = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleID;
    }

    public void setRoleId(String roleId) {
        this.roleID = roleId;
    }
    
}
