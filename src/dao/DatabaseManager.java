/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Customer;

/**
 *
 * @author Chamodi
 */
public class DatabaseManager {
    private Connection connect = null;
   
    public void connectdb(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_management","root","");  
            //here customer_management is database name, root is username and password  
            System.out.println("conneted to db");
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void saveCustomer(String fname,String lname, String email){
        connectdb();
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connect.prepareStatement("insert into customer(first_name,last_name,email) values(?,?,?)");
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, email);
            // execute the preparedstatement
            preparedStatement.execute();

            connect.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public List getAllCustomers(){
        connectdb();
        Statement statement;
        ResultSet resultSet;
        List<Customer> customers = new ArrayList<>();
        try{
            statement = connect.createStatement();
            String sql = "select * from customer";
            resultSet = statement.executeQuery(sql);
  
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            Customer customer = new Customer(first_name, last_name, email);
            customer.setId(id);
            customers.add(customer);

//            System.out.println("id: " + id);
//            System.out.println("first name: " + first_name);
//            System.out.println("last name: " + last_name);
//            System.out.println("email: " + email);       
        }
        
        connect.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
                
        return customers;
    }
    
    public Customer getCustomer(int id){
        connectdb();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Customer customer = new Customer("", "", "");
        try{
            String query = "select * from customer where id=?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.first()){
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                customer.setId(id);
                customer.setFname(first_name);
                customer.setLname(last_name);
                customer.setEmail(email);

                System.out.println("id: " + id);
                System.out.println("first name: " + first_name);
                System.out.println("last name: " + last_name);
                System.out.println("email: " + email);
                return customer;
            }
  
        connect.close();

        }catch(Exception e){
            System.out.println(e);
        }
        
        return customer;
    }
    
    public void deleteCustomer(int id){
        connectdb();
        PreparedStatement preparedStatement;
        try{
            String query = "delete from customer where id=?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
//            String sql = "delete from customer where first_name="+fname+" and last_name="+lname;
////            String sql = "delete from customer where first_name='chamodi' and last_name='madushani'";
//
//            Statement stmt = connect.createStatement();
//      
//            stmt.executeUpdate(sql);
            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateCustomer(int id,String fname, String lname, String email){
        connectdb();
        PreparedStatement preparedStatement;
        try{
            String query = "update customer set first_name=?, last_name=?, email=? where id= ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
//            String sql = "delete from customer where first_name="+fname+" and last_name="+lname;
////            String sql = "delete from customer where first_name='chamodi' and last_name='madushani'";
//
//            Statement stmt = connect.createStatement();
//      
//            stmt.executeUpdate(sql);
            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
