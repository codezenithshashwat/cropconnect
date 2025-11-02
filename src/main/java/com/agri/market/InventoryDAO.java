package com.agri.market;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    private Connection connection;

    public InventoryDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addInventory(Inventory inventory) {
        String sql = "INSERT INTO Maintains_Inventory_Of (warehouse_id, product_id, qty, storage_condition) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getWarehouse_id());
            stmt.setInt(2, inventory.getProduct_id());
            stmt.setDouble(3, inventory.getQty());
            stmt.setString(4, inventory.getStorage_condition());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All)
    public List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT mi.*, w.name as warehouse_name, p.name as product_name " +
                "FROM Maintains_Inventory_Of mi " +
                "JOIN Warehouse w ON mi.warehouse_id = w.warehouse_id " +
                "JOIN Product p ON mi.product_id = p.product_id " +
                "ORDER BY mi.inventory_id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventory_id(rs.getInt("inventory_id"));
                inventory.setWarehouse_id(rs.getInt("warehouse_id"));
                inventory.setProduct_id(rs.getInt("product_id"));
                inventory.setQty(rs.getDouble("qty"));
                inventory.setStorage_condition(rs.getString("storage_condition"));
                inventory.setWarehouse_name(rs.getString("warehouse_name"));
                inventory.setProduct_name(rs.getString("product_name"));
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }

    // READ (One)
    public Inventory getInventoryById(int inventoryId) {
        Inventory inventory = null;
        String sql = "SELECT * FROM Maintains_Inventory_Of WHERE inventory_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inventory = new Inventory();
                    inventory.setInventory_id(rs.getInt("inventory_id"));
                    inventory.setWarehouse_id(rs.getInt("warehouse_id"));
                    inventory.setProduct_id(rs.getInt("product_id"));
                    inventory.setQty(rs.getDouble("qty"));
                    inventory.setStorage_condition(rs.getString("storage_condition"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    // UPDATE
    public void updateInventory(Inventory inventory) {
        String sql = "UPDATE Maintains_Inventory_Of SET warehouse_id = ?, product_id = ?, qty = ?, storage_condition = ? WHERE inventory_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getWarehouse_id());
            stmt.setInt(2, inventory.getProduct_id());
            stmt.setDouble(3, inventory.getQty());
            stmt.setString(4, inventory.getStorage_condition());
            stmt.setInt(5, inventory.getInventory_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteInventory(int inventoryId) {
        String sql = "DELETE FROM Maintains_Inventory_Of WHERE inventory_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}