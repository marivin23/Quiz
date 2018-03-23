/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codequiz;

import java.sql.SQLException;
/**
 *
 * @author mariu
 */
public class CodeQuiz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        DBConnect connection = new DBConnect();
        UI window = new UI(connection);
        
    }
    
}
