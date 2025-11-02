package com.agri.crop;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlledByDAO {

    private Connection connection;

    public ControlledByDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE: Assign a fertilizer to a crop
    public void addControlledBy(ControlledBy cb) {
        String sql = "INSERT INTO Controlled_By (crop_id, fertilizer_id, date_app) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cb.getCrop_id());
            stmt.setInt(2, cb.getFertilizer_id());
            stmt.setDate(3, cb.getDate_app());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All): Get all fertilizer applications
    public List<ControlledBy> getAllControlledBy() {
        List<ControlledBy> cbList = new ArrayList<>();
        // This query joins Controlled_By, Crop, and Fertilizer
        String sql = "SELECT cb.*, c.name as crop_name, f.name as fertilizer_name " +
                "FROM Controlled_By cb " +
                "JOIN Crop c ON cb.crop_id = c.crop_id " +
                "JOIN Fertilizer f ON cb.fertilizer_id = f.fertilizer_id " +
                "ORDER BY cb.date_app DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ControlledBy cb = new ControlledBy();
                cb.setCrop_id(rs.getInt("crop_id"));
                cb.setFertilizer_id(rs.getInt("fertilizer_id"));
                cb.setDate_app(rs.getDate("date_app"));
                cb.setCrop_name(rs.getString("crop_name"));
                cb.setFertilizer_name(rs.getString("fertilizer_name"));
                cbList.add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cbList;
    }

    // DELETE: Delete an application record
    // Note: The primary key is (crop_id, fertilizer_id, date_app)
    public void deleteControlledBy(int cropId, int fertilizerId, java.sql.Date dateApp) {
        String sql = "DELETE FROM Controlled_By WHERE crop_id = ? AND fertilizer_id = ? AND date_app = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cropId);
            stmt.setInt(2, fertilizerId);
            stmt.setDate(3, dateApp);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}