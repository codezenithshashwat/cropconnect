package com.agri.harvest;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HarvestDAO {

    private Connection connection;

    public HarvestDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE: Add a new harvest
    // This method inserts into two tables: Harvest and Belongs_To
    public void addHarvest(Harvest harvest) {
        String sqlHarvest = "INSERT INTO Harvest (harvest_id, date, total_yield, warehouse_id, store_date) VALUES (?, ?, ?, ?, ?)";
        String sqlBelongsTo = "INSERT INTO Belongs_To (harvest_id, crop_id, plot_id) VALUES (?, ?, ?)";

        try {
            // Start transaction
            connection.setAutoCommit(false);

            // 1. Insert into Harvest
            try (PreparedStatement stmtHarvest = connection.prepareStatement(sqlHarvest)) {
                stmtHarvest.setInt(1, harvest.getHarvest_id());
                stmtHarvest.setDate(2, harvest.getDate());
                stmtHarvest.setDouble(3, harvest.getTotal_yield());
                stmtHarvest.setInt(4, harvest.getWarehouse_id());
                stmtHarvest.setDate(5, harvest.getStore_date());
                stmtHarvest.executeUpdate();
            }

            // 2. Insert into Belongs_To
            try (PreparedStatement stmtBelongs = connection.prepareStatement(sqlBelongsTo)) {
                stmtBelongs.setInt(1, harvest.getHarvest_id());
                stmtBelongs.setInt(2, harvest.getCrop_id());
                stmtBelongs.setInt(3, harvest.getPlot_id());
                stmtBelongs.executeUpdate();
            }

            // Commit transaction
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // Rollback on error
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // Return to default
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // READ (All): Get all harvest records with names
    public List<Harvest> getAllHarvests() {
        List<Harvest> harvests = new ArrayList<>();
        // This query joins 5 tables!
        String sql = "SELECT h.*, b.crop_id, b.plot_id, c.name as crop_name, p.location, w.name as warehouse_name " +
                "FROM Harvest h " +
                "JOIN Belongs_To b ON h.harvest_id = b.harvest_id " +
                "JOIN Crop c ON b.crop_id = c.crop_id " +
                "JOIN Farm_Plot p ON b.plot_id = p.plot_id " +
                "JOIN Warehouse w ON h.warehouse_id = w.warehouse_id " +
                "ORDER BY h.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Harvest harvest = new Harvest();
                harvest.setHarvest_id(rs.getInt("harvest_id"));
                harvest.setDate(rs.getDate("date"));
                harvest.setTotal_yield(rs.getDouble("total_yield"));
                harvest.setWarehouse_id(rs.getInt("warehouse_id"));
                harvest.setStore_date(rs.getDate("store_date"));
                harvest.setCrop_id(rs.getInt("crop_id"));
                harvest.setPlot_id(rs.getInt("plot_id"));
                harvest.setCrop_name(rs.getString("crop_name"));
                harvest.setPlot_location(rs.getString("location"));
                harvest.setWarehouse_name(rs.getString("warehouse_name"));
                harvests.add(harvest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harvests;
    }

    // DELETE: Delete a harvest (will cascade to Belongs_To)
    public void deleteHarvest(int harvestId) {
        String sql = "DELETE FROM Harvest WHERE harvest_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, harvestId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}