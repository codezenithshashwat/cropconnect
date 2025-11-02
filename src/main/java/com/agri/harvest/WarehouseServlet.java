package com.agri.harvest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/warehouses")
public class WarehouseServlet extends HttpServlet {
    private WarehouseDAO warehouseDAO;

    @Override
    public void init() {
        warehouseDAO = new WarehouseDAO();
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
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteWarehouse(request, response);
                break;
            default:
                listWarehouses(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertWarehouse(request, response);
        } else if ("update".equals(action)) {
            updateWarehouse(request, response);
        }
    }

    private void listWarehouses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Warehouse> warehouseList = warehouseDAO.getAllWarehouses();
        request.setAttribute("warehouseList", warehouseList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("harvest/warehouseList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("harvest/warehouseForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Warehouse existingWarehouse = warehouseDAO.getWarehouseById(id);
        request.setAttribute("warehouse", existingWarehouse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("harvest/warehouseForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertWarehouse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("warehouse_id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        double total_capacity = Double.parseDouble(request.getParameter("total_capacity"));

        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setWarehouse_id(id);
        newWarehouse.setName(name);
        newWarehouse.setCity(city);
        newWarehouse.setTotal_capacity(total_capacity);

        warehouseDAO.addWarehouse(newWarehouse);
        response.sendRedirect("warehouses?action=list");
    }

    private void updateWarehouse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("warehouse_id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        double total_capacity = Double.parseDouble(request.getParameter("total_capacity"));
        double left_capacity = Double.parseDouble(request.getParameter("left_capacity"));

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouse_id(id);
        warehouse.setName(name);
        warehouse.setCity(city);
        warehouse.setTotal_capacity(total_capacity);
        warehouse.setLeft_capacity(left_capacity);

        warehouseDAO.updateWarehouse(warehouse);
        response.sendRedirect("warehouses?action=list");
    }

    private void deleteWarehouse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        warehouseDAO.deleteWarehouse(id);
        response.sendRedirect("warehouses?action=list");
    }
}