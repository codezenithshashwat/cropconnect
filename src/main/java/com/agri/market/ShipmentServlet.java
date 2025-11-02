package com.agri.market;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/shipments")
public class ShipmentServlet extends HttpServlet {

    private ShipmentDAO shipmentDAO;
    private ProductDAO productDAO;
    private MarketDAO marketDAO;

    @Override
    public void init() {
        shipmentDAO = new ShipmentDAO();
        productDAO = new ProductDAO();
        marketDAO = new MarketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "delete":
                deleteShipment(request, response);
                break;
            default:
                listShipments(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertShipment(request, response);
        }
    }

    private void listShipments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Shipment> shipmentList = shipmentDAO.getAllShipments();
        request.setAttribute("shipmentList", shipmentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("market/shipmentList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get products and markets for dropdowns
        List<Product> productList = productDAO.getAllProducts();
        List<Market> marketList = marketDAO.getAllMarkets();

        request.setAttribute("productList", productList);
        request.setAttribute("marketList", marketList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("market/shipmentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertShipment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int market_id = Integer.parseInt(request.getParameter("market_id"));
        String vehicle_id = request.getParameter("vehicle_id");
        Date date = Date.valueOf(request.getParameter("date"));

        Shipment newShipment = new Shipment();
        newShipment.setProduct_id(product_id);
        newShipment.setMarket_id(market_id);
        newShipment.setVehicle_id(vehicle_id);
        newShipment.setDate(date);

        shipmentDAO.addShipment(newShipment);
        response.sendRedirect("shipments?action=list");
    }

    private void deleteShipment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int product_id = Integer.parseInt(request.getParameter("pid"));
        int market_id = Integer.parseInt(request.getParameter("mid"));
        String vehicle_id = request.getParameter("vid");
        Date date = Date.valueOf(request.getParameter("date"));

        shipmentDAO.deleteShipment(product_id, market_id, vehicle_id, date);
        response.sendRedirect("shipments?action=list");
    }
}