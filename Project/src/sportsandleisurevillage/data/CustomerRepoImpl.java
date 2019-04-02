package sportsandleisurevillage.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepoImpl implements Repo {
    private Connection conn;
    private PreparedStatement stmt;

    private int id;

    private void connect(){
        try{
            conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            throw new IllegalStateException("Failed to Connect", e);
        }
    }


    @Override
    public ResultSet read() {
        ResultSet result = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM Customer WHERE Customer.ID = ?");
            stmt.setInt(1, id);
            result = stmt.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void write() {

    }

    @Override
    public void update() {

    }

    @Override
    public void closeconn(){
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public CustomerRepoImpl(int id){
        this.id = id;
        connect();
    }
}