/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DatabaseManager;
import java.util.List;
import model.Customer;

/**
 *
 * @author Chamodi
 */
public class CustomerService {
    private final DatabaseManager db;

    public CustomerService() {
        this.db = new DatabaseManager();
    }
    
    public List getAllCustomers(){
//        List<Customer> customers = new ArrayList<>();
//        customers = db.getAllCustomers();
//        return customers;
        return db.getAllCustomers();
    }
    
    public Customer getCustomer(int id){
        return db.getCustomer(id);
    }
    
    public void saveCustomer(String fname, String lname, String email){
        db.saveCustomer(fname, lname, email);
    }
    
    public void updateCustomer(int id, String fname, String lname, String email ){
        db.updateCustomer(id, fname, lname, email);
    }
    
    public void deleteCustomer(int id){
        db.deleteCustomer(id);
    }
}
