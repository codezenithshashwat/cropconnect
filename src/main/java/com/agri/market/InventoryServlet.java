package com.agri.market;

import com.agri.harvest.Warehouse;     // <-- IMPORT FROM MODULE 4
import com.agri.harvest.WarehouseDAO; // <-- IMPORT FROM MODULE 4

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    private InventoryDAO inventoryDAO;
    private WarehouseDAO warehouseDAO;
    private ProductDAO productDAO;

    @Override
    public void init() {
        inventoryDAO = new InventoryDAO();
        warehouseDAO = new WarehouseDAO();
        productDAO = new ProductDAO();
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
                deleteInventory(request, response);
                break;
            default:
                listInventory(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertInventory(request, response);
        } else if ("update".equals(action)) {
            updateInventory(request, response);
        }
    }

    private void listInventory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Inventory> inventoryList = inventoryDAO.getAllInventory();
        request.setAttribute("inventoryList", inventoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("market/inventoryList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get warehouses and products for dropdowns
        List<Warehouse> warehouseList = warehouseDAO.getAllWarehouses();
        List<Product> productList = productDAO.getAllProducts();

        request.setAttribute("warehouseList", warehouseList);
        request.setAttribute("productList", productList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("market/inventoryForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get existing item
        int id = Integer.parseInt(request.getParameter("id"));
        Inventory existingInventory = inventoryDAO.getInventoryById(id);
        request.setAttribute("inventory", existingInventory);

        // Get warehouses and products for dropdowns
        List<Warehouse> warehouseList = warehouseDAO.getAllWarehouses();
        List<Product> productList = productDAO.getAllProducts();

        request.setAttribute("warehouseList", warehouseList);
        request.setAttribute("productList", productList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("market/inventoryForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int warehouse_id = Integer.parseInt(request.getParameter("warehouse_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        double qty = Double.parseDouble(request.getParameter("qty"));
        String storage_condition = request.getParameter("storage_condition");

        Inventory newInventory = new Inventory();
        newInventory.setWarehouse_id(warehouse_id);
        newInventory.setProduct_id(product_id);
        newInventory.setQty(qty);
        newInventory.setStorage_condition(storage_condition);

        inventoryDAO.addInventory(newInventory);
        response.sendRedirect("inventory?action=list");
    }

    private void updateInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("inventory_id"));
        int warehouse_id = Integer.parseInt(request.getParameter("warehouse_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        double qty = Double.parseDouble(request.getParameter("qty"));
        String storage_condition = request.getParameter("storage_condition");

        Inventory inventory = new Inventory();
        inventory.setInventory_id(id);
        inventory.setWarehouse_id(warehouse_id);
        inventory.setProduct_id(product_id);
        inventory.setQty(qty);
        inventory.setStorage_condition(storage_condition);

        inventoryDAO.updateInventory(inventory);
        response.sendRedirect("inventory?action=list");
    }

    private void deleteInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        inventoryDAO.deleteInventory(id);
        response.sendRedirect("inventory?action=list");
    }
}