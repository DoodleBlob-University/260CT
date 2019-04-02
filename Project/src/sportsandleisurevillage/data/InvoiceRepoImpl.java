package sportsandleisurevillage.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class InvoiceRepoImpl implements Repo {
    private Connection conn;
    private PreparedStatement stmt;

    private void connect(){
        try{
            conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            throw new IllegalStateException("Failed to Connect", e);
        }
    }

    @Override
    public ResultSet read(){
        ResultSet result = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM Invoice");
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

    public InvoiceRepoImpl(){
        connect();
    }
}