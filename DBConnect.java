/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codequiz;

import java.io.File;
import java.sql.*;
/**
 *
 * @author mariu
 */
public class DBConnect {
    
    private static String url = "jdbc:sqlite:E:\\SQLite\\Questions.db";
    private File db = new File(url);
    private Connection c = null;
    private Statement st;
    public int length;
    public String tableName = "Questions";
    
    public DBConnect() throws SQLException {
        
        if( c == null ){
            
            c = DriverManager.getConnection(url);
            st = c.createStatement();
            openTable(tableName);
            
        } else {
            
            c.close();
            return;
            
        }
        
    }
    
    public void openTable(String tableName) throws SQLException {
        
        String query = " CREATE TABLE IF NOT EXISTS " + tableName + " (\n " +
                       " id integer PRIMARY KEY, \n " +
                       " Question text NOT NULL, \n " + 
                       " Answer text NOT NULL)";
        
        st.execute(query);
        
    }
    
    public void executeQuery(String command, String q, String a) throws SQLException {
        
        PreparedStatement prp = c.prepareStatement(command);
        prp.setString(1, q);
        prp.setString(2, a);
        prp.executeUpdate();
        
    }
    
    public void executeQuery(String command) throws SQLException {
        
        ResultSet rs = st.executeQuery(command);
        rs.next();
        length = rs.getInt(1);
        System.out.println("Lenght = " + length);
        
    }
    
    public ResultSet select(int id) throws SQLException {
        
        String query = "SELECT Question, Answer FROM " + tableName + " WHERE id = " + id;
        ResultSet rs = st.executeQuery(query);
        
        return rs;
    }
    
    public void endConnection() throws SQLException {
        c.close();
    }
}
