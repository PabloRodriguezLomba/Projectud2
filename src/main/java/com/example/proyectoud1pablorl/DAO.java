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

    public void addFossil(Connection con , Fossil fosi) {

        String nam = fosi.getNam();
        int pric = fosi.getPric();
        String muse = fosi.getMuseum();

        try(Statement st = con.createStatement()) {

            int numFiles = st.executeUpdate("INSERT INTO fossil (name,price,Museum) VALUES ('" +nam + "','"+pric+"','"+muse+"'  )");





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void addFish(Connection con , Fish fish) {

        int id = fish.getI();
        String nae = fish.getNam();
        String shad = fish.getShado();
        int pric = fish.getPric();
        int priccj = fish.getPriccj();
        String catc = fish.getCatch();

        try(Statement st = con.createStatement()) {

            int numFiles = st.executeUpdate("INSERT INTO fish (id,name,shadow,Price,pricecj,Catch) VALUES ('" +id + "','"+nae+"','"+shad+"','"+pric+"','"+priccj+"','"+catc+"')");





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }    public void addBug(Connection con , Bug bug) {

        int id = bug.getI();
        String nam = bug.getNam();
        int pric = bug.getPric();
        int priflick = bug.getFlick();
        String catc = bug.getCatch();

        try(Statement st = con.createStatement()) {

            int numFiles = st.executeUpdate("INSERT INTO bug (id,name,price,priceflick,catch) VALUES ('"+id+"','" +nam + "','"+pric+"','"+priflick+"','"+catc+"'  )");





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void DeleteBugbyId(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM Bug where id = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteBugbyname(Connection con , String id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM Bug where name = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void DeleteBugbyPrice(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM Bug where Price = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteBugbyPriceflick(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del = st.executeUpdate("DELETE FROM Bug where priceflick = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteBugbyCatch(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM Bug where Catch = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void DeleteFishbyId(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fish where id = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Deletefishbyname(Connection con , String id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fish where name = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Deletefishbyshadow(Connection con , String id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fish where shadow = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeletefishbyPrice(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fish where Price = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeletefishbyPricecj(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del = st.executeUpdate("DELETE FROM fish where pricecj = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeletefishbyCatch(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fish where Catch = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Deletefossilbyname(Connection con , String id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fossil where name = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void Deletefossilbyprice(Connection con , int id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fossil where price = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DeletefossilbyMuseum(Connection con , String id) {

        try(Statement st = con.createStatement()) {

            int del= st.executeUpdate("DELETE FROM fossil where name = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }








}
