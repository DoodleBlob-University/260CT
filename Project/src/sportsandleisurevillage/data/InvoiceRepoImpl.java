package sportsandleisurevillage.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class InvoiceRepoImpl implements Repo {
    private Connection conn;
    private PreparedStatement stmt;

    private int id;
    private String col;
    private Boolean bool;

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
    public void update() {
        try {
            stmt = conn.prepareStatement("UPDATE Invoice SET`" + col +"` = ? WHERE ID = ?");
            stmt.setBoolean(1, bool);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            stmt = conn.prepareStatement("DELETE Invoice, InvoiceBooking FROM Invoice INNER JOIN InvoiceBooking ON Invoice.ID = InvoiceBooking.InvoiceID WHERE Invoice.ID = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeconn(){
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public InvoiceRepoImpl(){//read
        connect();
    }

    public InvoiceRepoImpl(int id, String col, Boolean bool){//update
        this.id = id;
        this.col = col;
        this.bool = bool;
        connect();
    }

    public InvoiceRepoImpl(int id){//delete
        this.id = id;
        connect();
    }
}