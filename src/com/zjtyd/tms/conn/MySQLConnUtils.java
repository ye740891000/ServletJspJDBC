package com.zjtyd.tms.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-18
 * Time: 08:58
 * Description:
 */
public class MySQLConnUtils
{
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException
    {
        String honeName= "localhost";
        String dbName ="hibernate";
        String userName = "root";
        String password = "root";
        return getMySQLConnection(honeName,dbName,userName,password);
    }

    public static Connection getMySQLConnection(String hostName,String dbName,String userName,String password) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://"+hostName+":3306"+dbName;
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);

        return connection;
    }
}
