/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Minh Thắng
 */
public class RightBlockDAO {

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

    public int getStatus(int i, int j) throws SQLException {
        String query = "select status from rightblock where i= " + i + " and j= " + j;
        Statement stmt = (Statement) getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        resultSet.next();
        int status = resultSet.getInt("status");
        return status;

    }

    public void setStatus(int id) throws SQLException {
        String query = "update rightblock set status = 1 where id="+id ;
        Statement stmt = (Statement) getConnection().createStatement();
        int n = stmt.executeUpdate(query);
    }
    public void resetStatus() throws SQLException {
        String query = "update rightblock set status = 0 " ;
        Statement stmt = (Statement) getConnection().createStatement();
        int n = stmt.executeUpdate(query);
        
    }

}
