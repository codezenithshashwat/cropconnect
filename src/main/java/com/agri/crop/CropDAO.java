package com.agri.crop;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CropDAO {

    private Connection connection;

    public CropDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addCrop(Crop crop) {
        String sql = "INSERT INTO Crop (crop_id, name, type, season, duration) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, crop.getCrop_id());
            stmt.setString(2, crop.getName());
            stmt.setString(3, crop.getType());
            stmt.setString(4, crop.getSeason());
            stmt.setInt(5, crop.getDuration());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All)
    public List<Crop> getAllCrops() {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT * FROM Crop ORDER BY crop_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Crop crop = new Crop();
                crop.setCrop_id(rs.getInt("crop_id"));
                crop.setName(rs.getString("name"));
                crop.setType(rs.getString("type"));
                crop.setSeason(rs.getString("season"));
                crop.setDuration(rs.getInt("duration"));
                crops.add(crop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crops;
    }

    // READ (One)
    public Crop getCropById(int cropId) {
        Crop crop = null;
        String sql = "SELECT * FROM Crop WHERE crop_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cropId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    crop = new Crop();
                    crop.setCrop_id(rs.getInt("crop_id"));
                    crop.setName(rs.getString("name"));
                    crop.setType(rs.getString("type"));
                    crop.setSeason(rs.getString("season"));
                    crop.setDuration(rs.getInt("duration"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crop;
    }

    // UPDATE
    public void updateCrop(Crop crop) {
        String sql = "UPDATE Crop SET name = ?, type = ?, season = ?, duration = ? WHERE crop_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, crop.getName());
            stmt.setString(2, crop.getType());
            stmt.setString(3, crop.getSeason());
            stmt.setInt(4, crop.getDuration());
            stmt.setInt(5, crop.getCrop_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteCrop(int cropId) {
        String sql = "DELETE FROM Crop WHERE crop_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cropId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}