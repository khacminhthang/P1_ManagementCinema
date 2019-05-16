/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.ui;

import com.minhthang.core.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh Thắng
 */
public class UserTableModel extends AbstractTableModel {
    public static final int OBJECT_COL = -1;
    public static final int USERNAME_COL = 0;
    public static final int PASSWORD_COL = 1;
    public static final int EMAIL_COL = 2;
    public static final int MOBIE_COL = 3;
    
 
    private final String[] columnNames = {"User_name", "Password", "Email", "Mobie"};

    private List<User> users;

    public UserTableModel(List<User> userList) {
        this.users = userList;

    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getComnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case USERNAME_COL:
                return user.getUsername();
            case PASSWORD_COL:
                return user.getPassword();
            case EMAIL_COL:
                return user.getEmail();
            case OBJECT_COL:
                return user;
            case MOBIE_COL:
                return user.getMobi();
            default:
                return user.getUsername();
        }
    }

    
    
}
