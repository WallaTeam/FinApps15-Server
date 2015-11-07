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
        static final String IP = "192.168.42.1";
        static final String DB = "finapps";

        public Database() {
        }


        private static final String INSERCION_CLIENTE = "insert into Clients(dni, name, surname, date, postalCode) values (?, ?, ?, ?, ?)";
        private static final String INSERCION_ARTICLE = "insert into Article(code, name, vat, price, description, stock, Category_name) values (?, ?, ?, ?, ?,?,?)";
        private static final String INSERCION_VENTA = "insert into Sale(code, date, Clients_dni, Workers_dni,cost ) values (?, ?, ?, ?, ?)";
        //private static final String INSERCION__ARTICULO_VENTA ="insert into Saled(Sale_code, Article_code) values (?, ?)";
        private static final String CONSULTA_LISTADO_CLIENTES = "select * from Clients";

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

        public Boolean insertarCliente(Cliente c) {
            try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(INSERCION_CLIENTE, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(c.getCode()));
                stmt.setString(2, c.getName());
                stmt.setString(3, c.getSurname());
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime;
                currentTime = sdf.format(c.getBirthDate());
                stmt.setString(4, String.valueOf(currentTime));
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
                return false;
            } catch (SQLException e) {
                try {
                    System.out.print(e.getMessage());
                    con.rollback();
                } catch (SQLException e2) {
                    System.out.print("insercion cliente");
                }
                return false;
            }
        }


    public boolean insertarVenta(Sale s) {
        try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement(INSERCION_VENTA, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, s.getCode());
            stmt.setString(2, s.getDate());
            stmt.setInt(3, s.getClient());
            stmt.setInt(4, s.getWorker());
            stmt.setDouble(5, s.getFinalPrice());

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

        public boolean modificarCliente(int dni, String name, String surname, String date, int postalCode) {
            Statement stmt = null;
            try {
                stmt = (Statement) con.createStatement();
            } catch (SQLException e) {
                return false;
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

        public Cliente obtenerCliente(int dni) {
            try {
                Statement stmt = (Statement) con.createStatement();
                String sql = "SELECT * " + "FROM Clients" + " WHERE dni=\"" + dni;
                ResultSet rs = stmt.executeQuery(sql);
                Cliente j = extraerCliente(rs);
                return j;
            } catch (SQLException e1) {
                return null;
            }
        }

        public Article obtenerArticulo(String code) {
            try {
                Statement stmt = (Statement) con.createStatement();
                String sql = "SELECT * " + "FROM Article" + " WHERE code=\"" + code;
                ResultSet rs = stmt.executeQuery(sql);
                Article j = extraerArticulo(rs);
                return j;
            } catch (SQLException e1) {
                return null;
            }
        }

        /*
            public Sale obtenerVenta(int code){
                try {
                    Statement stmt = (Statement) con.createStatement();
                    String sql = "SELECT * " + "FROM Sale" + " WHERE code=\"" + code;
                    ResultSet rs = stmt.executeQuery(sql);
                    Sale j = extraerVenta(rs);
                    return j;
                } catch (SQLException e1) {
                    return null;
                }
            }
    */
        public boolean insertarVenta_Articulo(int code, Article e) {
            try {
                Statement stmt = (Statement) con.createStatement();
                stmt.executeUpdate(
                        "INSERT INTO Saled (Sale_code,Article_code)"
                                + " VALUES ('" + code + "','"
                                + e.getCode() + "')");
                return true;
            } catch (SQLException e1) {
                return false;
            }
        }



        public static List<Cliente> obtenerListadoClientes() {
            try (ResultSet rs = con.prepareStatement(CONSULTA_LISTADO_CLIENTES).executeQuery()) {
                List<Cliente> res = new ArrayList<>();
                if (rs.next()) {
                    while (!rs.isAfterLast()) {
                        Cliente c = extraerCliente(rs);
                        res.add(c);
                    }
                }
                return res;
            } catch (SQLException e) {
                return null;
            }
        }

        private static Cliente extraerCliente(ResultSet rs) throws SQLException {
            int code = rs.getInt("dni");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String birthDate = rs.getString("date");
            int direccion = rs.getInt("postalCode");

            Cliente c = new Cliente(code, name, surname, birthDate, direccion);
            return c;
        }

        private static Article extraerArticulo(ResultSet rs) throws SQLException {
            String code = rs.getString("Code");
            String date = rs.getString("date");
            int vat = rs.getInt("vat");
            double prize = rs.getDouble("prize");
            String descripcion = rs.getString("description");
            int stock = rs.getInt("stock");
            String Category_name = rs.getString("Category_name");

            Article c = new Article(code, date,Category_name, prize, vat, descripcion, stock);
            return c;
        }

        public boolean insertarTrabajador(Worker a){

            try {
                Statement stmt = (Statement) con.createStatement();
                stmt.executeUpdate(
                        "INSERT INTO Workers (dni,Name)"
                                + " VALUES ('" + a.getDni() + "','"
                                + a.getName() + "')");
                return true;
            } catch (SQLException e1) {
                return false;
            }
        }

    }
