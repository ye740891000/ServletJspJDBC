package com.zjtyd.tms.utils;

import com.zjtyd.tms.beans.Product;
import com.zjtyd.tms.beans.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-18
 * Time: 09:23
 * Description:
 */
public class DBUtils
{
    public static UserAccount findUser(Connection conn,String userName,String password) throws SQLException
    {
        String sql = "select a.USER_NAME,a.PASSWORD.a.GENDER from user_account a where a.USER_NAME = ? and a.PASSWORD" +
                " = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,userName);
        pstm.setString(2,password);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next())
        {
            String gender = resultSet.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static UserAccount findUser(Connection conn,String userName) throws SQLException
    {
        String sql = "select a.USER_NAME,a.PASSWORD.a.GENDER from user_account a where a.USER_NAME = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next())
        {
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");

            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;

    }

    public static List<Product> queryProduct(Connection conn) throws SQLException
    {
        String sql = "select a.CODE,a.NAME,a.PRICE from product a";

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<Product> list = new ArrayList<>();
        while (resultSet.next())
        {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");

            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }

    public static Product findProduct(Connection conn,String code) throws SQLException
    {
        String sql = "select a.CODE,a.NAME,a.PRICE from product a where a.CODE = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next())
        {
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");
            Product product = new Product(code,name,price);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection conn,Product product) throws SQLException
    {
        String sql = "update product set name = ?,price = ? where code = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,product.getName());
        pstm.setFloat(2,product.getPrice());
        pstm.setString(3,product.getCode());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn,Product product) throws SQLException
    {
        String sql = "insert product (code,name,price) value(?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1,product.getCode());
        pstm.setString(2,product.getName());
        pstm.setFloat(3,product.getPrice());
        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn,String code) throws SQLException
    {
        String sql = "delete from product where code = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,code);
        pstm.executeUpdate();

    }




}
