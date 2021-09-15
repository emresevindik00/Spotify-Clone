package spotifyproje;     
        

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
/**
 *
 * @author msi
 */
public class DbHelper {
    private String userName = "root";
    private String password = "12345";
    private String dbUrl = "jdbc:mysql://127.0.0.1:3306/sistem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //jdbc:mysql://127.0.0.1:3306/
    //jdbc:mysql://localhost:3306/ --> uygun olan
    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException{
        return (Connection) DriverManager.getConnection(dbUrl, userName, password);
    }
    
    public void showErrorMessage(SQLException exception){
        System.out.println("Error: "+exception.getMessage());
    }
}
