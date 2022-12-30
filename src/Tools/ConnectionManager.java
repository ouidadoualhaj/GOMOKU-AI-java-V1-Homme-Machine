package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
    //la connexion à la base de données
    private static Connection connection;
    private String url = "jdbc:mysql://localhost:3306/gomoku";
    private String user = "root";
    private String password = "";

    private ConnectionManager() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
            if(connection == null)
                    new ConnectionManager();
            return connection;
    }
    
}
