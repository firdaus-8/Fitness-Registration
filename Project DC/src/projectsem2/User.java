/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsem2;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private int age;
    private String userID;
    private String phone;

    public User(String name, String email, int age, String userID, String phone) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.userID = userID;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", userID='" + userID + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
