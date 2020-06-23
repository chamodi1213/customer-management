/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customermanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Chamodi
 */
class Customer{
    private String fname;
    private String lname;
    private String email;
    private int id;
    
    Customer(String fname, String lname, String email){
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


public class DatabaseManager {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    
    public void connectdb(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_management","root","");  
            //here customer_management is database name, root is username and password  
                System.out.println("yes");
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void saveCustomer(String fname,String lname, String email){
        connectdb();
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(email);
        
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
        List<Customer> customers = new ArrayList<Customer>();
        try{
        statement = connect.createStatement();
        resultSet = statement.executeQuery("select * from customer");
  
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

            System.out.println("id: " + id);
            System.out.println("first name: " + first_name);
            System.out.println("last name: " + last_name);
            System.out.println("email: " + email);       
        }
        
        connect.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
                
        return customers;
    }
    
    public Customer getCustomer(int id){
        connectdb();
        Customer customer = new Customer("no", "", "");
        try{
        String sql = "select * from customer where id="+id;
        statement = connect.createStatement();
        resultSet = statement.executeQuery(sql);
//        preparedStatement = connect.prepareStatement(sql);
//        preparedStatement.setInt(1, id);
//        resultSet = preparedStatement.executeQuery();
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
        try{
            String query = "delete from customer where id=?";
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setInt(1, id);

            preparedStmt.executeUpdate();
//            String sql = "delete from customer where first_name="+fname+" and last_name="+lname;
////            String sql = "delete from customer where first_name='chamodi' and last_name='madushani'";
//
//            Statement stmt = connect.createStatement();
//      
//            stmt.executeUpdate(sql);
            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    
    public void updateCustomer(int id,String fname, String lname, String email){
        connectdb();
        try{
            String query = "update customer set first_name=?, last_name=?, email=? where id= ?";
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setString(1, fname);
            preparedStmt.setString(2, lname);
            preparedStmt.setString(3, email);
            preparedStmt.setInt(4, id);

            preparedStmt.executeUpdate();
//            String sql = "delete from customer where first_name="+fname+" and last_name="+lname;
////            String sql = "delete from customer where first_name='chamodi' and last_name='madushani'";
//
//            Statement stmt = connect.createStatement();
//      
//            stmt.executeUpdate(sql);
            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
        
    public void readDataBase(){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/feedback?"
                            + "user=sqluser&password=sqluserpw");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from feedback.comments");
            System.out.println(resultSet);
            writeResultSet(resultSet);
        }
        catch (Exception e) {
//            throw e;
                System.out.println("error");
        } 
//        finally {
//            close();
//        }
    }
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }
}
