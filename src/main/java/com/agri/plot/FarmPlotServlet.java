package com.agri.plot;

// *** IMPORTING FROM MODULE 1 ***
import com.agri.farmer.Farmer;
import com.agri.farmer.FarmerDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/plots")
public class FarmPlotServlet extends HttpServlet {

    private FarmPlotDAO plotDAO;
    private FarmerDAO farmerDAO; // *** DAO FROM MODULE 1 ***

    @Override
    public void init() {
        plotDAO = new FarmPlotDAO();
        farmerDAO = new FarmerDAO(); // Initialize FarmerDAO
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
                deletePlot(request, response);
                break;
            default:
                listPlots(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertPlot(request, response);
        } else if ("update".equals(action)) {
            updatePlot(request, response);
        }
    }

    private void listPlots(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<FarmPlot> plotList = plotDAO.getAllPlots();
        request.setAttribute("plotList", plotList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("plot/plotList.jsp");
        dispatcher.forward(request, response);
    }

    // *** KEY INTEGRATION METHOD ***
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Get the list of all farmers
        List<Farmer> farmerList = farmerDAO.getAllFarmers();
        // 2. Pass this list to the JSP
        request.setAttribute("farmerList", farmerList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("plot/plotForm.jsp");
        dispatcher.forward(request, response);
    }

    // *** KEY INTEGRATION METHOD ***
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Get the plot to be edited
        int id = Integer.parseInt(request.getParameter("id"));
        FarmPlot existingPlot = plotDAO.getPlotById(id);
        request.setAttribute("plot", existingPlot);

        // 2. Get the list of all farmers for the dropdown
        List<Farmer> farmerList = farmerDAO.getAllFarmers();
        request.setAttribute("farmerList", farmerList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("plot/plotForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPlot(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("plot_id"));
        double size = Double.parseDouble(request.getParameter("size"));
        String location = request.getParameter("location");
        String status = request.getParameter("status");
        int owned_by = Integer.parseInt(request.getParameter("owned_by"));

        FarmPlot newPlot = new FarmPlot();
        newPlot.setPlot_id(id);
        newPlot.setSize(size);
        newPlot.setLocation(location);
        newPlot.setStatus(status);
        newPlot.setOwned_by(owned_by);

        plotDAO.addPlot(newPlot);
        response.sendRedirect("plots?action=list");
    }

    private void updatePlot(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("plot_id"));
        double size = Double.parseDouble(request.getParameter("size"));
        String location = request.getParameter("location");
        String status = request.getParameter("status");
        int owned_by = Integer.parseInt(request.getParameter("owned_by"));

        FarmPlot plot = new FarmPlot();
        plot.setPlot_id(id);
        plot.setSize(size);
        plot.setLocation(location);
        plot.setStatus(status);
        plot.setOwned_by(owned_by);

        plotDAO.updatePlot(plot);
        response.sendRedirect("plots?action=list");
    }

    private void deletePlot(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        plotDAO.deletePlot(id);
        response.sendRedirect("plots?action=list");
    }
}