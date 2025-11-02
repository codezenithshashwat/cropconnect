package com.agri.crop;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrowsDAO {

    private Connection connection;

    public GrowsDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE: Assign a crop to a plot
    public void addGrows(Grows grows) {
        String sql = "INSERT INTO Grows (crop_id, plot_id, start_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, grows.getCrop_id());
            stmt.setInt(2, grows.getPlot_id());
            stmt.setDate(3, grows.getStart_date());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All): Get all current and past growing records
    public List<Grows> getAllGrows() {
        List<Grows> growsList = new ArrayList<>();
        // This query joins Grows, Crop, Farm_Plot, and Farmer
        String sql = "SELECT g.*, c.name as crop_name, p.location, f.name as farmer_name " +
                "FROM Grows g " +
                "JOIN Crop c ON g.crop_id = c.crop_id " +
                "JOIN Farm_Plot p ON g.plot_id = p.plot_id " +
                "JOIN Farmer f ON p.owned_by = f.farmer_id " +
                "ORDER BY g.start_date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grows grows = new Grows();
                grows.setCrop_id(rs.getInt("crop_id"));
                grows.setPlot_id(rs.getInt("plot_id"));
                grows.setStart_date(rs.getDate("start_date"));
                grows.setEnd_date(rs.getDate("end_date"));
                grows.setCrop_name(rs.getString("crop_name"));
                grows.setPlot_location(rs.getString("location"));
                grows.setFarmer_name(rs.getString("farmer_name"));
                growsList.add(grows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return growsList;
    }

    // --- NEW METHOD START ---
    // READ (Active): Get all growing records that have not ended
    public List<Grows> getActiveGrows() {
        List<Grows> growsList = new ArrayList<>();
        // This query joins Grows, Crop, Farm_Plot, and Farmer
        String sql = "SELECT g.*, c.name as crop_name, p.location, f.name as farmer_name " +
                "FROM Grows g " +
                "JOIN Crop c ON g.crop_id = c.crop_id " +
                "JOIN Farm_Plot p ON g.plot_id = p.plot_id " +
                "JOIN Farmer f ON p.owned_by = f.farmer_id " +
                "WHERE g.end_date IS NULL " + // <-- The important part
                "ORDER BY g.start_date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grows grows = new Grows();
                grows.setCrop_id(rs.getInt("crop_id"));
                grows.setPlot_id(rs.getInt("plot_id"));
                grows.setStart_date(rs.getDate("start_date"));
                grows.setEnd_date(rs.getDate("end_date"));
                grows.setCrop_name(rs.getString("crop_name"));
                grows.setPlot_location(rs.getString("location"));
                grows.setFarmer_name(rs.getString("farmer_name"));
                growsList.add(grows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return growsList;
    }
    // --- NEW METHOD END ---

    // UPDATE: To "end" a crop, we set its end_date
    public void endGrows(int cropId, int plotId, java.sql.Date endDate) {
        String sql = "UPDATE Grows SET end_date = ? WHERE crop_id = ? AND plot_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, endDate);
            stmt.setInt(2, cropId);
            stmt.setInt(3, plotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: This will remove the record entirely
    public void deleteGrows(int cropId, int plotId) {
        String sql = "DELETE FROM Grows WHERE crop_id = ? AND plot_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cropId);
            stmt.setInt(2, plotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}