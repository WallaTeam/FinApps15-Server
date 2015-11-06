package bd;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import logica.*;

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
    static final String USER = "luis";
    static final String PASS = "platano";
    static final String IP = "localhost";
    static final String DB = "finapps";

    public Database() {
    }


    private static final String INSERCION_CLIENTE = "insert into Clients(dni, name, surname, date, postalCode) values (?, ?, ?, ?, ?)";


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

    public String insertarCliente(Cliente c) {
        try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(INSERCION_CLIENTE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, String.valueOf(c.getCode()));
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getSurname());
            stmt.setString(4, String.valueOf(c.getBirthDate()));
            stmt.setString(5, String.valueOf(c.getPostalCode()));

            stmt.executeUpdate();
            con.commit();
            return "00000";
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e2) {
                System.out.print("insercion cliente");
            }
            return e.getSQLState();
        }
    }


    //
    public String insertarVenta(Cliente c) {
        return "";
    }

    //
    public String insertarDevolucion(Cliente c) {
        return "";
    }

    public boolean modificarCliente(int dni, String name, String surname, String date, int postalCode) {
        Statement stmt = null;
        try {
            stmt = (Statement)con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // (LOW PRIORITY) Actualizar cuando nadie este leyendo
        String sql_1 = "UPDATE LOW PRIORITY " + "Clients"
                + " SET dni =" + dni
                + ", name =" + name + ", surname =" + surname
                + ", date =" + date + ", postalCode=" + postalCode
                + "WHERE dni=\""
                + dni
                + "\"";

        try {
            stmt.executeQuery(sql_1);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

   /* public boolean borrarCliente(Cliente c){
        Statement stmt = con.createStatement();
        sql_1 = "DELETE FROM Clients " + " WHERE dni = '" + nombre + "'"
                + " and marca = '" + marca + "'"
                + " and codigo_restaurante = '"
                + codigo + "'";
        return false;
        }

    public ArrayList <Article> articulos()

    Statement stmt = con.createStatement();

        }*/

}
