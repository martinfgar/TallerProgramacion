package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDB {
	
	 
    public String driver = "oracle.jdbc.driver.OracleDriver";

    // Nombre de la base de datos
    public String database = "xe";

    // Host
    public String hostname = "91.200.117.27";

    // Puerto
    public String port = "49161";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + database ;

    // Nombre de usuario


    public Connection conectarOracle(String username,String password) {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}