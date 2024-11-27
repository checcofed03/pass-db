package connessione_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {
    private String url;
    private String driver;
    private String username;
    private String password;
    private String DBName;
    
    public DBConnectionManager(String username, String password) {
        this.username = username;
        this.password = password;
        this.url="jdbc:mysql://localhost:3306/";
        this.driver="com.mysql.cj.jdbc.Driver";
        this.DBName="PASS_DB";
    }

    public Connection getConnection() throws ClassNotFoundException,SQLException{
        /*returns a connection with database */
        Connection conn=null;

        Class.forName(driver);
        conn=DriverManager.getConnection(url+DBName,username,password); 
        
        return conn;
    }

    public void closeConnection(Connection c) throws SQLException{
        if (c!=null)c.close();
    }

    public ResultSet selectQuery(String query,Connection conn) throws SQLException{
        Statement stmt=null;
        ResultSet res=null;

        stmt = conn.createStatement();        
        res=stmt.executeQuery(query);
              
        return res;
    }
    
    public int updateQuery(String query, Connection conn) throws SQLException{
        Statement stmt=null;
        int res=0;

        stmt = conn.createStatement();
        res=stmt.executeUpdate(query);
        
        return res;
    }

    public boolean test(Connection c)throws SQLException{
        boolean ris=false;
        
        ris=c.isValid(120);
        
        return ris;
    }
}
