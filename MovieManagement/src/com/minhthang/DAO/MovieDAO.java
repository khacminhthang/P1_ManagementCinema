/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.DAO;

import com.minhthang.core.Movie;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh Thắng
 */
public class MovieDAO {

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

    public boolean addNewMovie(Movie movie) throws SQLException {
        String query = "insert into demomovie(id,type_movie,name_movie,introduction_movie) values (" + movie.getId() + " , '" + movie.getType()
                + "', '" + movie.getName() + "' , '" + movie.getIntroduction() + "')";
        Statement stmt = (Statement) getConnection().createStatement();
        int n = stmt.executeUpdate(query);
        if (n != 0) {
            return true;
        }
        return false;
    }

    public boolean modifyMovie(Movie movie) throws SQLException {
        String query = "update demomovie set type_movie='" + movie.getType() + "' , name_movie='" + movie.getName()
                + "', introduction_movie='" + movie.getIntroduction() + "' where id=" + movie.getId();
        Statement stmt = (Statement) getConnection().createStatement();
        int n = stmt.executeUpdate(query);
        if (n != 0) {
            return true;
        }
        return false;
    }

    public boolean deleteMovieByName(String name_movie) throws SQLException {
        String query = "delete from demomovie where name_movie = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, name_movie);
        int n = stmt.executeUpdate();
        if (n != 0) {
            System.out.println(n + "row delete");
        }
        return false;
    }

    public ArrayList<Movie> findAllMovieByName(String name_movie) throws SQLException {
        String query = "select * from demomovie where" + "(" + " name_movie like '" + "%" + name_movie + "%" + "'" + " or " + "type_movie like '" + "%" + name_movie + "%" + "'" + ")";
        Statement stmt = (Statement) getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setId(resultSet.getLong("id"));
            movie.setType(resultSet.getString("type_movie"));
            movie.setName(resultSet.getString("name_movie"));
            movie.setIntroduction(resultSet.getString("introduction_movie"));
            movieList.add(movie);

        }
        return movieList;
    }

    public Movie findMovieByName(String name_movie) throws SQLException {
        String query = "select * from demomovie where name_movie='" + "%" + name_movie + "%" + "'";
        java.sql.Statement stmt = getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            Movie movie = new Movie();
            movie.setId(resultSet.getLong("id"));
            movie.setType(resultSet.getString("type_movie"));
            movie.setName(resultSet.getString("name_movie"));
            movie.setIntroduction(resultSet.getString("introduction_movie"));
            return movie;
        } else {
            return null;
        }
    }

    public Movie findMovieById(long id) throws SQLException {
        String query = "select * from demomovie where id='" + id + "'";
        java.sql.Statement stmt = getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        if (resultSet.next()) {
            Movie movie = new Movie();
            movie.setId(resultSet.getLong("id"));
            movie.setType(resultSet.getString("type_movie"));
            movie.setName(resultSet.getString("name_movie"));
            movie.setIntroduction(resultSet.getString("introduction_movie"));
            return movie;
        } else {
            return null;
        }
    }

    public ArrayList<Movie> findAll() throws SQLException {
        String query = "select * from demomovie";
        java.sql.Statement stmt = getConnection().createStatement();
        ResultSet resulSet = stmt.executeQuery(query);
        ArrayList movieList = new ArrayList<Movie>();
        while (resulSet.next()) {
            Movie movie = new Movie();
            movie.setId(resulSet.getLong("id"));
            movie.setType(resulSet.getString("type_movie"));
            movie.setName(resulSet.getString("name_movie"));
            movie.setIntroduction(resulSet.getString("introduction_movie"));
            movieList.add(movie);
        }
        return movieList;
    }

    public ArrayList<String> databaseName() throws SQLException {
        String query = "select name_movie from demomovie";
        java.sql.Statement stmt = getConnection().createStatement();
        ResultSet resulSet = stmt.executeQuery(query);
        ArrayList name = new ArrayList();
        while (resulSet.next()) {
           String name_movie = resulSet.getString("name_movie");
           name.add(name_movie);
        }
        return name;
        
    }

    public long generateId() throws SQLException {
        String query = "select max(id) as maxid from demomovie";
        Statement stmt = (Statement) getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        if (resultSet.next()) {
            return resultSet.getLong("maxid") + 1;
        } else {
            return 0;
        }
    }

    public void addMovie(Movie theMovie) throws Exception {
        PreparedStatement myStmt = null;

        //prepare statement
        myStmt = getConnection().prepareStatement("insert into demomovie"
                + "(id,type_movie,name_movie,introduction_movie,actors,director,country,length,image,day,time)"
                + "values(?,?,?,?,?,?,?,?,?,?,?)");
        //set params

        myStmt.setLong(1, theMovie.getId());
        myStmt.setString(2, theMovie.getType());
        myStmt.setString(3, theMovie.getName());
        myStmt.setString(4, theMovie.getIntroduction());
        myStmt.setString(5, theMovie.getActor());
        myStmt.setString(6, theMovie.getDirector());
        myStmt.setString(7, theMovie.getCountry());
        myStmt.setString(8, theMovie.getLength());
        myStmt.setString(9, theMovie.getImage());
        myStmt.setString(10, theMovie.getDay());
        myStmt.setString(11, theMovie.getTime());

        //excute SQL
        myStmt.executeUpdate();

    }

    public void updateMovie(Movie theMovie) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = getConnection().prepareStatement("update demomovie "
                + "set type_movie=? , name_movie=?, introduction_movie=?, actors=?, director=?, country=?, length=?, image=?, day=?, time=?"
                + "where id=?");
        myStmt.setString(1, theMovie.getType());
        myStmt.setString(2, theMovie.getName());
        myStmt.setString(3, theMovie.getIntroduction());
        myStmt.setString(4, theMovie.getActor());
        myStmt.setString(5, theMovie.getDirector());
        myStmt.setString(6, theMovie.getCountry());
        myStmt.setString(7, theMovie.getLength());
        myStmt.setString(8, theMovie.getImage());
        myStmt.setString(9, theMovie.getDay());
        myStmt.setString(10, theMovie.getTime());
        myStmt.setLong(11, theMovie.getId());

        myStmt.executeUpdate();
    }

    public void deleteMovie(long movieId) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = getConnection().prepareStatement("delete from demomovie where id=?");
        myStmt.setLong(1, movieId);
        myStmt.executeUpdate();
    }

    public Movie getItemMovie(int id) {
        Movie movie = new Movie();
        String query = "SELECT * FROM demomovie WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                movie.setId(id);
                movie.setName(resultSet.getString("name_movie"));
                movie.setType(resultSet.getString("type_movie"));
                movie.setActor(resultSet.getString("actors"));
                movie.setCountry(resultSet.getString("country"));
                movie.setIntroduction(resultSet.getString("introduction_movie"));
                movie.setDirector(resultSet.getString("director"));
                movie.setLength(resultSet.getString("length"));
                movie.setImage(resultSet.getString("image"));
                movie.setDay(resultSet.getString("day"));
                movie.setTime(resultSet.getString("time"));

            }
            return movie;
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
