package bd;

import com.mysql.jdbc.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by teruyi on 6/11/15.
 */
public class Database {

    private static Connection con;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "jdbc:mysql://";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "wallapet";
    static final String IP = "192.168.56.101";
    static final String DB = "finapps";

    public Database() {

    }

    public String connect() {
        try {
            String db_driver = JDBC_DRIVER + IP + ":" + 3306 + "/" + DB;
            con = (Connection) DriverManager.getConnection(db_driver, USER, PASS);
            con.setAutoCommit(false);
            System.out.println("bien");
            return "00000";
        } catch (SQLException e) {
            System.out.println("mal");
            return e.getSQLState();
        }
    }

    public String disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                return e.getSQLState();
            }
        }
        return "00000";
    }

    public int get (){
        return 1;
    }


}
