package com.example.proyectoud1pablorl;

import com.example.proyectoud1pablorl.Object.Bug;
import com.example.proyectoud1pablorl.Object.Fish;
import com.example.proyectoud1pablorl.Object.Fossil;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

    public  Connection connect() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ud2projecto?user=root&password=root");
            System.out.println("Connection stablished");
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Fish> getAllFish(Connection con) {
        ArrayList<Fish> allfish = new ArrayList<>();
        try (Statement st = con.createStatement()) {

            try (ResultSet rs = st.executeQuery("SELECT * FROM fish")) {
                while (rs.next()) {
                    allfish.add(new Fish(rs.getInt("id"),rs.getString("name"),rs.getString("shadow"),rs.getInt("Price"),rs.getInt("pricecj"),rs.getString("Catch")));

                }
            }
            return allfish;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Bug> getAllBug(Connection con) {
        ArrayList<Bug> allBug = new ArrayList<>();
        try (Statement st = con.createStatement()) {

            try (ResultSet rs = st.executeQuery("SELECT * FROM bug")) {
                while (rs.next()) {
                    allBug.add(new Bug(rs.getInt("id"),rs.getString("name"),rs.getInt("Price"),rs.getInt("priceflick"),rs.getString("catch")));

                }
            }
            return allBug;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Fossil> getAllFossil(Connection con) {
        ArrayList<Fossil> allFossil = new ArrayList<>();
        try (Statement st = con.createStatement()) {

            try (ResultSet rs = st.executeQuery("SELECT * FROM fossil")) {
                while (rs.next()) {
                    allFossil.add(new Fossil(rs.getString("name"),rs.getInt("Price"),rs.getString("Museum")));

                }
            }
            return allFossil;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Fish getFishId(Connection con , int id) {
        Fish fish = null;
        try(Statement st = con.createStatement()) {

            try(ResultSet rs = st.executeQuery("SELECT * FROM  fish where id = " + id)){
                
                while (rs.next()) {
                    fish = new Fish(rs.getInt("id"),rs.getString("name"),rs.getString("shadow"),rs.getInt("Price"),rs.getInt("pricecj"),rs.getString("Catch"));
                }
            }

            return fish;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Bug getBugId(Connection con , int id) {
        Bug bug = null;
        try(Statement st = con.createStatement()) {

            try(ResultSet rs = st.executeQuery("SELECT * FROM  Bug where id = " + id)){

                while (rs.next()) {
                    bug = new Bug(rs.getInt("id"),rs.getString("name"),rs.getInt("Price"),rs.getInt("priceflick"),rs.getString("catch"));
                }
            }

            return bug;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Fossil getFossilId(Connection con , String id) {
        Fossil fossil = null;
        try(Statement st = con.createStatement()) {

            try(ResultSet rs = st.executeQuery("SELECT * FROM  Fossil where name = '" + id +"'")){

                while (rs.next()) {
                    fossil = new Fossil(rs.getString("name"),rs.getInt("price"),rs.getString("Museum"));
                }
            }

            return fossil;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}
