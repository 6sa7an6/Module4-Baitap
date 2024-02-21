package ra.demospringmvc.ulti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/post_crud";
    static final String USERNAME = "root";
    static final String PASSWORD = "Vjtc0nd3pz4j";
    public static java.sql.Connection openConnection(){
        java.sql.Connection conn;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection conn){
        try {
            if(!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
