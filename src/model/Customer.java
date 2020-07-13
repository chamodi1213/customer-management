/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Chamodi
 */
public class Customer {
 private String fname;
    private String lname;
    private String email;
    private int id;
    
    public Customer(String fname, String lname, String email){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getId(){
        return id;
    }
    
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }
}

