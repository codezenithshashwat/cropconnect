package com.agri.market;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDAO {

    private Connection connection;

    public ShipmentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addShipment(Shipment shipment) {
        String sql = "INSERT INTO Sent_To (product_id, market_id, vehicle_id, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipment.getProduct_id());
            stmt.setInt(2, shipment.getMarket_id());
            stmt.setString(3, shipment.getVehicle_id());
            stmt.setDate(4, shipment.getDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All)
    public List<Shipment> getAllShipments() {
        List<Shipment> shipments = new ArrayList<>();
        String sql = "SELECT st.*, p.name as product_name, m.name as market_name " +
                "FROM Sent_To st " +
                "JOIN Product p ON st.product_id = p.product_id " +
                "JOIN Market m ON st.market_id = m.market_id " +
                "ORDER BY st.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shipment shipment = new Shipment();
                shipment.setProduct_id(rs.getInt("product_id"));
                shipment.setMarket_id(rs.getInt("market_id"));
                shipment.setVehicle_id(rs.getString("vehicle_id"));
                shipment.setDate(rs.getDate("date"));
                shipment.setProduct_name(rs.getString("product_name"));
                shipment.setMarket_name(rs.getString("market_name"));
                shipments.add(shipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    // DELETE
    public void deleteShipment(int productId, int marketId, String vehicleId, java.sql.Date date) {
        String sql = "DELETE FROM Sent_To WHERE product_id = ? AND market_id = ? AND vehicle_id = ? AND date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, marketId);
            stmt.setString(3, vehicleId);
            stmt.setDate(4, date);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}