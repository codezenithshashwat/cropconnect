package com.agri.crop;

import com.agri.plot.FarmPlot;       // <-- IMPORT FROM MODULE 2
import com.agri.plot.FarmPlotDAO;    // <-- IMPORT FROM MODULE 2

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/grows")
public class GrowsServlet extends HttpServlet {

    private GrowsDAO growsDAO;
    private CropDAO cropDAO;         // <-- DAO from Module 3A
    private FarmPlotDAO plotDAO;     // <-- DAO from Module 2

    @Override
    public void init() {
        growsDAO = new GrowsDAO();
        cropDAO = new CropDAO();
        plotDAO = new FarmPlotDAO();
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
                deleteGrows(request, response);
                break;
            default:
                listGrows(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertGrows(request, response);
        } else if ("endcrop".equals(action)) {
            endGrows(request, response);
        }
    }

    private void listGrows(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Grows> growsList = growsDAO.getAllGrows();
        request.setAttribute("growsList", growsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/growsList.jsp");
        dispatcher.forward(request, response);
    }

    // *** KEY INTEGRATION METHOD ***
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get all available crops
        List<Crop> cropList = cropDAO.getAllCrops();
        request.setAttribute("cropList", cropList);

        // 2. Get all available plots
        List<FarmPlot> plotList = plotDAO.getAllPlots();
        request.setAttribute("plotList", plotList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/growsForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertGrows(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int crop_id = Integer.parseInt(request.getParameter("crop_id"));
        int plot_id = Integer.parseInt(request.getParameter("plot_id"));
        Date start_date = Date.valueOf(request.getParameter("start_date"));

        Grows newGrows = new Grows();
        newGrows.setCrop_id(crop_id);
        newGrows.setPlot_id(plot_id);
        newGrows.setStart_date(start_date);

        growsDAO.addGrows(newGrows);
        response.sendRedirect("grows?action=list");
    }

    private void endGrows(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int crop_id = Integer.parseInt(request.getParameter("crop_id"));
        int plot_id = Integer.parseInt(request.getParameter("plot_id"));
        Date end_date = Date.valueOf(request.getParameter("end_date"));

        growsDAO.endGrows(crop_id, plot_id, end_date);
        response.sendRedirect("grows?action=list");
    }

    private void deleteGrows(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int crop_id = Integer.parseInt(request.getParameter("crop_id"));
        int plot_id = Integer.parseInt(request.getParameter("plot_id"));

        growsDAO.deleteGrows(crop_id, plot_id);
        response.sendRedirect("grows?action=list");
    }
}