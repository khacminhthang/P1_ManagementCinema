/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.app;

import com.minhthang.DAO.MovieDAO;
import com.minhthang.core.Movie;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Minh Thắng
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MovieDAO movieDAO = new MovieDAO();

    public static void main(String[] args) {
        System.out.println("The movie maganement program ");
        System.out.println("1. Add new movie");
        System.out.println("2. Find movie by name");
        System.out.println("3. Find movie by id");
        System.out.println("4. Display movie list");
        System.out.println("5. Delete movie by name");
        System.out.println("6. Update movie");
        System.out.println("7.Exit");
        boolean flag = true;
        while (flag) {
            System.out.println("enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewMovie();
                    break;
                case 2:
                    findMovieByName();
                    break;
                case 3:
                    findMovieById();
                    break;
                case 4:
                    displayMovieList();
                    break;
                case 5:
                    deleteMovieByName();
                    break;
                case 6:
                    updateMovie();
                    break;
                case 7:
                    System.out.println("System end ");
                    movieDAO.closeConnection();
                    flag = false;

            }

        }
    }

    public static void addNewMovie() {
        System.out.println("Add new movie function");
        try {
            long id = movieDAO.generateId();
            System.out.println("Input the type ");
            String type_movie = scanner.nextLine();
            System.out.println("Input the name ");
            String name_movie = scanner.nextLine();
            System.out.println("Input the introduction ");
            String introduction_movie = scanner.nextLine();
            Movie movie = new Movie(id, type_movie, name_movie, introduction_movie);
            movieDAO.addNewMovie(movie);
        } catch (Exception e) {
            System.out.println("an erroe when adding");
            e.printStackTrace();
        }
    }

    public static void findMovieByName() {
        System.out.println("Find movie by name function ");
        System.out.println("Input the name movie ");
        String name_movie = scanner.nextLine();
        Movie movie = null;
        try {
            movie = movieDAO.findMovieByName(name_movie);
        } catch (Exception e) {
            System.out.println("an error when find");
            e.printStackTrace();
        }
        if (movie != null) {
            System.out.println(movie);
        } else {
            System.out.println("Can't find with " + name_movie);
        }
    }

    public static void findMovieById() {
        System.out.println("Find movie by Id");
        System.out.println("Input id ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Movie movie = null;
        try {
            movie = movieDAO.findMovieById(id);
        } catch (Exception e) {
            System.out.println("an error when find with id" + id);
            e.printStackTrace();
        }
        if (movie != null) {
            System.out.println(movie);

        } else {
            System.out.println("can't find");
        }

    }

    public static void displayMovieList() {
        try {
            ArrayList<Movie> movieList = movieDAO.findAll();
            for (Movie movie : movieList) {
                System.out.println(movie);
            }
        } catch (Exception e) {
            System.out.println("an error when display");
            e.printStackTrace();
        }
    }

    public static void deleteMovieByName() {
        System.out.println("Delete movie by name function");
        System.out.println("Input name of movie");
        String name_movie = scanner.nextLine();
        try {
            movieDAO.deleteMovieByName(name_movie);
        } catch (Exception e) {
            System.out.println("an error when delete by name " + name_movie);
            e.printStackTrace();
        }

    }

    public static void updateMovie() {
        try {
            System.out.println("Update movie");
            System.out.println(" Input id of movie");
            long id = scanner.nextLong();
            Movie movie = movieDAO.findMovieById(id);
            if (movie == null) {
                System.out.println("can't find the movie id :" + id);
            } else {
                System.out.println("Found movie id:" + id);
                System.out.println("Info" + movie);
                System.out.println("input new type_movie");
                String type = scanner.nextLine();
                System.out.println("Input new name_movie");
                String name = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Input new introduction_movie ");
                String introduction = scanner.nextLine();
                Movie newMovie = new Movie(id, type, name, introduction);
                movieDAO.modifyMovie(newMovie);
            }
        } catch (Exception e) {
            System.out.println("an error when update ");
            e.printStackTrace();
        }
    }
}
