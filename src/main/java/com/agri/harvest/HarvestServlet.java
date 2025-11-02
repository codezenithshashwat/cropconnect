package com.agri.harvest;

import com.agri.crop.Grows;          // <-- IMPORT FROM MODULE 3
import com.agri.crop.GrowsDAO;       // <-- IMPORT FROM MODULE 3

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/harvests")
public class HarvestServlet extends HttpServlet {

    private HarvestDAO harvestDAO;
    private GrowsDAO growsDAO;           // <-- DAO from Module 3
    private WarehouseDAO warehouseDAO;   // <-- DAO from Module 4A

    @Override
    public void init() {
        harvestDAO = new HarvestDAO();
        growsDAO = new GrowsDAO();
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
            case "delete":
                deleteHarvest(request, response);
                break;
            default:
                listHarvests(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertHarvest(request, response);
        }
    }

    private void listHarvests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Harvest> harvestList = harvestDAO.getAllHarvests();
        request.setAttribute("harvestList", harvestList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("harvest/harvestList.jsp");
        dispatcher.forward(request, response);
    }

    // *** KEY INTEGRATION METHOD ***
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get all active crops (using our new DAO method)
        List<Grows> activeGrowsList = growsDAO.getActiveGrows();
        request.setAttribute("activeGrowsList", activeGrowsList);

        // 2. Get all warehouses
        List<Warehouse> warehouseList = warehouseDAO.getAllWarehouses();
        request.setAttribute("warehouseList", warehouseList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("harvest/harvestForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertHarvest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // 1. Get data from form
        int harvest_id = Integer.parseInt(request.getParameter("harvest_id"));
        Date harvest_date = Date.valueOf(request.getParameter("harvest_date"));
        double total_yield = Double.parseDouble(request.getParameter("total_yield"));
        int warehouse_id = Integer.parseInt(request.getParameter("warehouse_id"));

        // 2. The "grows" dropdown value is a composite: "crop_id-plot_id"
        String[] growsIds = request.getParameter("grows_id").split("-");
        int crop_id = Integer.parseInt(growsIds[0]);
        int plot_id = Integer.parseInt(growsIds[1]);

        // 3. Create Harvest object
        Harvest newHarvest = new Harvest();
        newHarvest.setHarvest_id(harvest_id);
        newHarvest.setDate(harvest_date);
        newHarvest.setTotal_yield(total_yield);
        newHarvest.setWarehouse_id(warehouse_id);
        newHarvest.setStore_date(harvest_date); // Store date is same as harvest date
        newHarvest.setCrop_id(crop_id);
        newHarvest.setPlot_id(plot_id);

        // 4. Add to DB (this DAO method adds to two tables)
        harvestDAO.addHarvest(newHarvest);

        // 5. Redirect
        response.sendRedirect("harvests?action=list");
    }

    private void deleteHarvest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        harvestDAO.deleteHarvest(id);
        response.sendRedirect("harvests?action=list");
    }
}