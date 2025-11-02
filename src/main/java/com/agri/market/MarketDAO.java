package com.agri.market;

import com.agri.common.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarketDAO {

    private Connection connection;

    public MarketDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addMarket(Market market) {
        String sql = "INSERT INTO Market (market_id, name, city, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, market.getMarket_id());
            stmt.setString(2, market.getName());
            stmt.setString(3, market.getCity());
            stmt.setString(4, market.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (All)
    public List<Market> getAllMarkets() {
        List<Market> markets = new ArrayList<>();
        String sql = "SELECT * FROM Market ORDER BY market_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Market market = new Market();
                market.setMarket_id(rs.getInt("market_id"));
                market.setName(rs.getString("name"));
                market.setCity(rs.getString("city"));
                market.setType(rs.getString("type"));
                markets.add(market);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return markets;
    }

    // READ (One)
    public Market getMarketById(int marketId) {
        Market market = null;
        String sql = "SELECT * FROM Market WHERE market_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, marketId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    market = new Market();
                    market.setMarket_id(rs.getInt("market_id"));
                    market.setName(rs.getString("name"));
                    market.setCity(rs.getString("city"));
                    market.setType(rs.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return market;
    }

    // UPDATE
    public void updateMarket(Market market) {
        String sql = "UPDATE Market SET name = ?, city = ?, type = ? WHERE market_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, market.getName());
            stmt.setString(2, market.getCity());
            stmt.setString(3, market.getType());
            stmt.setInt(4, market.getMarket_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteMarket(int marketId) {
        String sql = "DELETE FROM Market WHERE market_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, marketId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}