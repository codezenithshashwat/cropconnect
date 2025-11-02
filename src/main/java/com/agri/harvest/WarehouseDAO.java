package com.agri.harvest;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {

    private Connection connection;

    public WarehouseDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO Warehouse (warehouse_id, name, city, total_capacity, left_capacity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, warehouse.getWarehouse_id());
            stmt.setString(2, warehouse.getName());
            stmt.setString(3, warehouse.getCity());
            stmt.setDouble(4, warehouse.getTotal_capacity());
            // When creating, left_capacity is total_capacity
            stmt.setDouble(5, warehouse.getTotal_capacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All)
    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM Warehouse ORDER BY warehouse_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouse_id(rs.getInt("warehouse_id"));
                warehouse.setName(rs.getString("name"));
                warehouse.setCity(rs.getString("city"));
                warehouse.setTotal_capacity(rs.getDouble("total_capacity"));
                warehouse.setLeft_capacity(rs.getDouble("left_capacity"));
                warehouses.add(warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }

    // READ (One)
    public Warehouse getWarehouseById(int warehouseId) {
        Warehouse warehouse = null;
        String sql = "SELECT * FROM Warehouse WHERE warehouse_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, warehouseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    warehouse = new Warehouse();
                    warehouse.setWarehouse_id(rs.getInt("warehouse_id"));
                    warehouse.setName(rs.getString("name"));
                    warehouse.setCity(rs.getString("city"));
                    warehouse.setTotal_capacity(rs.getDouble("total_capacity"));
                    warehouse.setLeft_capacity(rs.getDouble("left_capacity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouse;
    }

    // UPDATE
    public void updateWarehouse(Warehouse warehouse) {
        String sql = "UPDATE Warehouse SET name = ?, city = ?, total_capacity = ?, left_capacity = ? WHERE warehouse_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, warehouse.getName());
            stmt.setString(2, warehouse.getCity());
            stmt.setDouble(3, warehouse.getTotal_capacity());
            stmt.setDouble(4, warehouse.getLeft_capacity());
            stmt.setInt(5, warehouse.getWarehouse_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteWarehouse(int warehouseId) {
        String sql = "DELETE FROM Warehouse WHERE warehouse_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, warehouseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}