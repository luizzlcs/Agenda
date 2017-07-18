package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    private final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    private final String DRIVER_MSSQL = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private final String DATABASE = "db_agenda";
    private final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private final String USER = "root";
    private final String PASSWORD = "senac";
    
    private Connection connection = null;
    
    public boolean open() {
        try {
            Class.forName(DRIVER_MYSQL);
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch(ClassNotFoundException | SQLException error) {
            System.out.println("Erro: " + error);
        }
        return false;
    }
    
    public boolean close() {
        try {
            this.connection.close();
            return true;
        } catch (SQLException error) {
            System.out.println("Erro: " + error);
        }
        return false;
    }
    
    public Connection getConnetion() {
        return this.connection;
    }
    
}
