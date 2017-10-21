package com.zjtyd.tms.conn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-18
 * Time: 08:56
 * Description:
 */
public class ConnectionUtils
{
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        return MySQLConnUtils.getMySQLConnection();
    }
    public static void closeQuietly(Connection conn)
    {
        try
        {
            conn.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void rollbackQuietly(Connection conn)
    {
        try
        {
            conn.rollback();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
