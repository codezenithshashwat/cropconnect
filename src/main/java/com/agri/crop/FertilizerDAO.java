package com.agri.crop;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerDAO {

    private Connection connection;

    public FertilizerDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addFertilizer(Fertilizer fertilizer) {
        String sql = "INSERT INTO Fertilizer (fertilizer_id, name, composition) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fertilizer.getFertilizer_id());
            stmt.setString(2, fertilizer.getName());
            stmt.setString(3, fertilizer.getComposition());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All)
    public List<Fertilizer> getAllFertilizers() {
        List<Fertilizer> fertilizers = new ArrayList<>();
        String sql = "SELECT * FROM Fertilizer ORDER BY fertilizer_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fertilizer fertilizer = new Fertilizer();
                fertilizer.setFertilizer_id(rs.getInt("fertilizer_id"));
                fertilizer.setName(rs.getString("name"));
                fertilizer.setComposition(rs.getString("composition"));
                fertilizers.add(fertilizer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fertilizers;
    }

    // READ (One)
    public Fertilizer getFertilizerById(int fertilizerId) {
        Fertilizer fertilizer = null;
        String sql = "SELECT * FROM Fertilizer WHERE fertilizer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fertilizerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fertilizer = new Fertilizer();
                    fertilizer.setFertilizer_id(rs.getInt("fertilizer_id"));
                    fertilizer.setName(rs.getString("name"));
                    fertilizer.setComposition(rs.getString("composition"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fertilizer;
    }

    // UPDATE
    public void updateFertilizer(Fertilizer fertilizer) {
        String sql = "UPDATE Fertilizer SET name = ?, composition = ? WHERE fertilizer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fertilizer.getName());
            stmt.setString(2, fertilizer.getComposition());
            stmt.setInt(3, fertilizer.getFertilizer_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteFertilizer(int fertilizerId) {
        String sql = "DELETE FROM Fertilizer WHERE fertilizer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fertilizerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}