/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.DAO;

import com.minhthang.core.User;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Minh Thắng
 */
public class LoginDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demomovie?autoReconnect=true&useSSL=false",
                    "root", "123456");
            return conn;
        }
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkLoginAdmin(String username, String password) throws SQLException {
        String query = "select * from admin where user_name = ? and password = ? ";
        try {
            PreparedStatement pstmt = null;
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLoginUser(String username, String password) throws SQLException {
        String query = "select * from user where user_name = ? and password = ? ";
        try {
            PreparedStatement pstmt = null;
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void addUser(long id,  String username, String password, String email, String mobi) throws Exception {
        PreparedStatement myStmt = null;
        
         myStmt = getConnection().prepareStatement("insert into user(id,user_name,password,email,mobi) values(?,?,?,?,?)");
         
         myStmt.setLong(1, id);
         myStmt.setString(2, username);
         myStmt.setString(3, password);
         myStmt.setString(4, email);
         myStmt.setString(5, mobi);
         
         myStmt.executeUpdate();
        
        
    }
    
    public void deleteUser(long id) throws SQLException{
        PreparedStatement myStmt = null;
        myStmt = getConnection().prepareStatement("delete from user where id=?");
        myStmt.setLong(1, id);
        myStmt.executeUpdate();
    }
    
    public void addAdmin(long id,  String username, String password, String email, String mobi) throws Exception {
        PreparedStatement myStmt = null;
        
         myStmt = getConnection().prepareStatement("insert into admin(id,user_name,password,email,mobi) values(?,?,?,?,?)");
         
         myStmt.setLong(1, id);
         myStmt.setString(2, username);
         myStmt.setString(3, password);
         myStmt.setString(4, email);
         myStmt.setString(5, mobi);
         
         myStmt.executeUpdate();
        
        
    }
    
    
    public long generateUserId() throws SQLException {
        String query = "select max(id) as maxid from user";
        Statement stmt = (Statement) getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getLong("maxid") + 1;
        } else {
            return 0;
        }
    }
    public long generateAdminId() throws SQLException {
        String query = "select max(id) as maxid from admin";
        Statement stmt = (Statement) getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getLong("maxid") + 1;
        } else {
            return 0;
        }
    }
    public boolean checkUserName(String username) throws Exception{
        String query = "select * from user where user_name = ?";
        try {
            PreparedStatement pstmt = null;
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, username);

            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        
    }
    public boolean checkAdminName(String username) throws Exception{
        String query = "select * from admin where user_name = ?";
        try {
            PreparedStatement pstmt = null;
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, username);

            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        
    }
    public ArrayList<User> findAll() throws SQLException{
		String query = "select * from user";
		java.sql.Statement stmt = getConnection().createStatement();
		ResultSet resulSet = stmt.executeQuery(query);
		ArrayList userList = new ArrayList<User>();
		while(resulSet.next()) {
			User user = new User();
			user.setId(resulSet.getLong("id"));
                        user.setUsername(resulSet.getString("user_name"));
			user.setPassword(resulSet.getString("password"));
			user.setEmail(resulSet.getString("email"));
                        user.setMobi(resulSet.getString("mobi"));
			userList.add(user);
		}
		return userList;
	}
    public ArrayList<User> findAllMovieByName(String user_name) throws SQLException {
        String query = "select * from user where"+ "(" +" user_name like '" +"%"+ user_name +"%"+ "'"+")";
        Statement stmt = (Statement) getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        ArrayList<User> userList = new ArrayList<User>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setMobi(resultSet.getString("mobi"));
            userList.add(user);

        }
        return userList;
    }

}
