/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.ui;

import com.minhthang.core.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh Thắng
 */
public class MovieTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    public static final int TYPE_COL = 0;
    public static final int NAME_COL = 1;
    public static final int INTRODUCTION_COL = 2;
    public static final int ACTOR_COL = 3;
    public static final int DIRECTOR_COL = 4;
    public static final int COUNTRY_COL = 5;
    public static final int LENGTH_COL = 6;
    private final String[] columnNames = {"Type", "Name", "Introduction"};

    private List<Movie> movies;

    public MovieTableModel(List<Movie> movieList) {
        this.movies = movieList;

    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getComnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case TYPE_COL:
                return movie.getType();
            case NAME_COL:
                return movie.getName();
            case INTRODUCTION_COL:
                return movie.getIntroduction();
            case OBJECT_COL:
                return movie;
            case ACTOR_COL:
                return movie.getActor();
            case DIRECTOR_COL:
                return movie.getDirector();
            case COUNTRY_COL:
                return movie.getCountry();
            case LENGTH_COL:
                return movie.getLength();
            default:
                return movie.getName();
        }
    }

}
