/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codequiz;

import java.util.Random;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author mariu
 */
public class NewQuestionOutput {

    DBConnect db;
    public ResultSet result;
    public static int index;
    
    public NewQuestionOutput() throws SQLException {
        db = DBConnect.instance();
    }

    public String data() throws SQLException {
        
        Random r = new Random();
        db.executeQuery("SELECT COUNT(*) FROM " + db.tableName);
        index = r.nextInt(db.length)+1;
        result = db.select(index);
 
        return result.getString("Question");
    }
    
    public String data(int i) throws SQLException {
        
        result = db.select(i);
 
        return result.getString("Answer");
    }
    
}
