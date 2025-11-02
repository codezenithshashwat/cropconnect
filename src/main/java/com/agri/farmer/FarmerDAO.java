package com.agri.farmer;

import com.agri.common.DatabaseConnection; // Import the connection utility

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmerDAO {

    private Connection connection;

    public FarmerDAO() {
        // Get the database connection from our common class
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE: Add a new farmer
    // Note: Based on your sample data, we must provide the farmer_id.
    // reg_no is the one that is SERIAL (auto-incrementing).
    public void addFarmer(Farmer farmer) {
        String sql = "INSERT INTO Farmer (farmer_id, name, contact) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, farmer.getFarmer_id());
            stmt.setString(2, farmer.getName());
            stmt.setString(3, farmer.getContact());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get all farmers
    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        String sql = "SELECT * FROM Farmer ORDER BY farmer_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Farmer farmer = new Farmer();
                farmer.setFarmer_id(rs.getInt("farmer_id"));
                farmer.setName(rs.getString("name"));
                farmer.setContact(rs.getString("contact"));
                farmer.setReg_no(rs.getInt("reg_no"));
                farmer.setSize_owned(rs.getDouble("size_owned"));
                farmers.add(farmer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmers;
    }

    // READ: Get one farmer by ID
    public Farmer getFarmerById(int farmerId) {
        Farmer farmer = null;
        String sql = "SELECT * FROM Farmer WHERE farmer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, farmerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    farmer = new Farmer();
                    farmer.setFarmer_id(rs.getInt("farmer_id"));
                    farmer.setName(rs.getString("name"));
                    farmer.setContact(rs.getString("contact"));
                    farmer.setReg_no(rs.getInt("reg_no"));
                    farmer.setSize_owned(rs.getDouble("size_owned"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmer;
    }

    // UPDATE: Update a farmer's name and contact
    public void updateFarmer(Farmer farmer) {
        String sql = "UPDATE Farmer SET name = ?, contact = ? WHERE farmer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, farmer.getName());
            stmt.setString(2, farmer.getContact());
            stmt.setInt(3, farmer.getFarmer_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Delete a farmer
    public void deleteFarmer(int farmerId) {
        String sql = "DELETE FROM Farmer WHERE farmer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, farmerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}