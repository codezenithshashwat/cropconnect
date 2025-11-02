package com.agri.plot;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmPlotDAO {

    private Connection connection;

    public FarmPlotDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE: Add a new plot
    public void addPlot(FarmPlot plot) {
        // Your trigger will handle updating Farmer.size_owned
        String sql = "INSERT INTO Farm_Plot (plot_id, size, location, status, owned_by) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, plot.getPlot_id());
            stmt.setDouble(2, plot.getSize());
            stmt.setString(3, plot.getLocation());
            stmt.setString(4, plot.getStatus());
            stmt.setInt(5, plot.getOwned_by());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get all plots (with farmer name)
    public List<FarmPlot> getAllPlots() {
        List<FarmPlot> plots = new ArrayList<>();
        // Use a JOIN to get the farmer's name for display
        String sql = "SELECT p.*, f.name as farmer_name " +
                "FROM Farm_Plot p " +
                "JOIN Farmer f ON p.owned_by = f.farmer_id " +
                "ORDER BY p.plot_id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FarmPlot plot = new FarmPlot();
                plot.setPlot_id(rs.getInt("plot_id"));
                plot.setSize(rs.getDouble("size"));
                plot.setLocation(rs.getString("location"));
                plot.setStatus(rs.getString("status"));
                plot.setOwned_by(rs.getInt("owned_by"));
                plot.setFarmer_name(rs.getString("farmer_name")); // Set the joined name
                plots.add(plot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plots;
    }

    // READ: Get one plot by ID
    public FarmPlot getPlotById(int plotId) {
        FarmPlot plot = null;
        String sql = "SELECT * FROM Farm_Plot WHERE plot_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, plotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    plot = new FarmPlot();
                    plot.setPlot_id(rs.getInt("plot_id"));
                    plot.setSize(rs.getDouble("size"));
                    plot.setLocation(rs.getString("location"));
                    plot.setStatus(rs.getString("status"));
                    plot.setOwned_by(rs.getInt("owned_by"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plot;
    }

    // UPDATE: Update a plot
    public void updatePlot(FarmPlot plot) {
        // Your trigger will handle size/owner changes
        String sql = "UPDATE Farm_Plot SET size = ?, location = ?, status = ?, owned_by = ? WHERE plot_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, plot.getSize());
            stmt.setString(2, plot.getLocation());
            stmt.setString(3, plot.getStatus());
            stmt.setInt(4, plot.getOwned_by());
            stmt.setInt(5, plot.getPlot_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Delete a plot
    public void deletePlot(int plotId) {
        // Your trigger will handle reducing Farmer.size_owned
        String sql = "DELETE FROM Farm_Plot WHERE plot_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, plotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}