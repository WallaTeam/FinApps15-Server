package bd;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import logica.Cliente;
import logica.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    static final String PASS = "wallamsql";
    static final String IP = "localhost";
    static final String DB = "finapps";

    public Database() {
        this.connect();
    }


    private static final String INSERCION_CLIENTE = "insert into Clients(dni, name, surname, date, postalCode) values (?, ?, ?, ?, ?)";
    private static final String INSERCION_ARTICLE ="insert into Article(code, name, vat, price, description, stock, Category_name) values (?, ?, ?, ?, ?,?,?)";
    private static final String INSERCION_VENTA ="insert into Sale(date, Clients_dni, Workers_dni,cost ) values ( ?, ?, ?, ?)";
    //private static final String INSERCION__ARTICULO_VENTA ="insert into Saled(Sale_code, Article_code) values (?, ?)";
    private static final String CONSULTA_LISTADO_CLIENTES = "select * from Clients";
    private static final String ACTUALIZACION_ARTICULO = "update Article set name = ?, vat = ?, price = ?, description = ? , stock = ?, Category_name = ? where code = ?";
    private static final String CONSULTA_LISTADO_ARTICULOS ="select * from Article";
    public String connect() {
        try {
            String db_driver = JDBC_DRIVER + IP + ":" + 3306 + "/" + DB;
            con = (Connection) DriverManager.getConnection(db_driver, USER, PASS);
            con.setAutoCommit(false);
            return "00000";
        } catch (SQLException e) {
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

    public Boolean insertarCliente(Cliente c) {
        try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(INSERCION_CLIENTE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, String.valueOf(c.getCode()));
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getSurname());

            stmt.setString(4, String.valueOf(c.getBirthDate()));
            stmt.setString(5, String.valueOf(c.getPostalCode()));

            stmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback();
                return false;
            } catch (SQLException e2) {

            }
            return false;
        }
    }

    public Boolean actualizarArticulo (Article a) {
        try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(ACTUALIZACION_ARTICULO, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getName());
            stmt.setInt(2, a.getVat());
            stmt.setDouble(3, a.getPrize());
            stmt.setString(4, String.valueOf(a.getDescription()));
            stmt.setInt(5, a.getStock());
            stmt.setString(6, String.valueOf(a.getCategory()));
            stmt.setString(7, String.valueOf(a.getCode()));

            stmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback();
                return false;
            } catch (SQLException e2) {

            }
            return false;
        }
    }

    //
    public Boolean insertarArticulo(Article a) {
        try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(INSERCION_ARTICLE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, String.valueOf(a.getCode()));
            stmt.setString(2, a.getName());
            stmt.setString(3, String.valueOf(a.getVat()));
            stmt.setString(4, String.valueOf(a.getPrize()));
            stmt.setString(5, String.valueOf(a.getDescription()));
            stmt.setString(6, String.valueOf(a.getStock()));
            stmt.setString(7, String.valueOf(a.getCategory()));


            stmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e2) {
            }
            return false;
        }
    }


    public boolean insertarVenta(Sale s) {
        try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(INSERCION_VENTA, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, s.getDate());
            stmt.setInt(2, s.getClient());
            stmt.setInt(3, s.getWorker());
            stmt.setDouble(4, s.getFinalPrice());

            stmt.executeUpdate();

            for (Article at : s.getArticlelist()) {

                insertarVenta_Articulo(s.getCode(), at);
            }
            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e2) {
            }
            return false;
        }
        return true;
    }

    //
    public String insertarDevolucion(Cliente c) {
        return "";
    }

    public boolean actualizarCliente(int dni, String name, String surname, String date, int postalCode) {

        try {
            Statement stmt = (Statement) con.createStatement();
            // (LOW PRIORITY) Actualizar cuando nadie este leyendo
            String sql1 = "UPDATE " + "Clients"
                    + " SET dni="+"\"" + dni + "\""
                    + ", name="+"\""  + name +"\""+ ", surname=" +"\"" + surname+"\""
                    + ", date=" +"\"" + date +"\""+ ", postalCode=" +"\"" + postalCode+"\""
                    + " WHERE dni=\""
                    + dni
                    + "\"";
            stmt.executeUpdate(sql1);
            con.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean borrarCliente(int c){
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "DELETE FROM Clients " + " WHERE dni = '" + c + "'";
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public ArrayList<Sale> obtenerListadoVentas() {
        ArrayList<Sale> ventas = new ArrayList<Sale>();
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "SELECT * " + "FROM Sale";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Sale venta = obtenerVenta(rs.getInt("code"));
                ventas.add(venta);
            }
            return ventas;
        } catch (SQLException e) {
            return null;
        }
    }
    public Worker obtenerTrabajador(int dni){
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "SELECT * " + "FROM Workers" + " WHERE dni=\"" + dni+"\"";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            String name = rs.getString("Name");
            Worker w = new Worker(dni,name);
            return w;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Cliente obtenerCliente(int dni) {
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "SELECT * " + "FROM Clients" + " WHERE dni=\"" + dni+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            Cliente j = extraerCliente(rs);
            return j;
        } catch (SQLException e1) {
            return null;
        }
    }

    public Article obtenerArticulo(String code) {
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "SELECT * " + "FROM Article" + " WHERE code=\"" + code + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            Article j = extraerArticulo(rs);
            return j;
        } catch (SQLException e1) {
            return null;
        }
    }


    public Sale obtenerVenta(int code){
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "SELECT * " + "FROM Saled" + " WHERE Sale_code=\"" + code+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            Sale j = extraerVenta(rs);
            return j;
        } catch (SQLException e1) {
            return null;
        }
    }

    public boolean insertarVenta_Articulo(int code, Article e) {
        try {
            Statement stmt = (Statement) con.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO Saled (Sale_code,Article_code)"
                            + " VALUES ('" + code + "','"
                            + e.getCode() + "')");
            con.commit();
            return true;
        } catch (SQLException e1) {
            return false;
        }
    }



    public  List<Cliente> obtenerListadoClientes() {
        try (ResultSet rs = con.prepareStatement(CONSULTA_LISTADO_CLIENTES).executeQuery()) {
            List<Cliente> res = new ArrayList<>();
            while (rs.next()) {
                Cliente c = extraerCliente(rs);
                res.add(c);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  List<Article> obtenerListadoArticulos() {
        try (ResultSet rs = con.prepareStatement(CONSULTA_LISTADO_ARTICULOS).executeQuery()) {
            List<Article> res = new ArrayList<>();
            while (rs.next()) {
                Article c = extraerArticulo(rs);
                res.add(c);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // El rs tiene todas las tuplas de la tabla Ventas_Articulos {
    private Sale extraerVenta(ResultSet rs) throws SQLException {
        ArrayList<Article> articulos = new ArrayList<Article>();

        String articulo = rs.getString("Article_code");
        int venta = rs.getInt("Sale_code");
        articulos.add(obtenerArticulo(articulo));
        while(rs.next()){
            articulo = rs.getString("Article_code");
            articulos.add(obtenerArticulo(articulo));
        }
        try {
            Statement stmt = (Statement) con.createStatement();
            String sql = "SELECT * " + "FROM Sale" + " WHERE code=\"" + venta + "\"";
            ResultSet rss = stmt.executeQuery(sql);
            rss.next();
            String date = rss.getString("date");
            double cost = rss.getDouble("cost");
            int Wdni = rss.getInt("Workers_dni");
            int Cdni = rss.getInt("Clients_dni");
            Sale s = new Sale(venta,Cdni,date,articulos,Wdni);
            return s;
        } catch (SQLException e1) {
            return null;
        }

    }

    private  Cliente extraerCliente(ResultSet rs) throws SQLException {

        int code = rs.getInt("dni");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String birthDate = rs.getString("date");
        int direccion = rs.getInt("postalCode");

        Cliente c = new Cliente(code, name, surname, birthDate, direccion);
        return c;
    }

    private  Article extraerArticulo(ResultSet rs) throws SQLException {

        String code = rs.getString("code");
        String name = rs.getString("name");
        int vat = rs.getInt("vat");
        double price = rs.getDouble("price");
        String descripcion = rs.getString("description");
        int stock = rs.getInt("stock");
        String Category_name = rs.getString("Category_name");

        Article c = new Article(code, name,Category_name, price, vat, descripcion, stock);
        return c;
    }

    public boolean insertarTrabajador(Worker a){

        try {
            Statement stmt = (Statement) con.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO Workers (dni,Name)"
                            + " VALUES ('" + a.getDni() + "','"
                            + a.getName() + "')");
            con.commit();
            return true;
        } catch (SQLException e1) {
            return false;
        }
    }

}