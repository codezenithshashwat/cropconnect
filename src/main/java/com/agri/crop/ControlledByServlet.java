package com.agri.crop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/applies")
public class ControlledByServlet extends HttpServlet {

    private ControlledByDAO controlledByDAO;
    private CropDAO cropDAO;
    private FertilizerDAO fertilizerDAO;

    @Override
    public void init() {
        controlledByDAO = new ControlledByDAO();
        cropDAO = new CropDAO();
        fertilizerDAO = new FertilizerDAO();
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
                deleteControlledBy(request, response);
                break;
            default:
                listApplications(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertControlledBy(request, response);
        }
    }

    private void listApplications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ControlledBy> cbList = controlledByDAO.getAllControlledBy();
        request.setAttribute("cbList", cbList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/controlledByList.jsp");
        dispatcher.forward(request, response);
    }

    // *** KEY INTEGRATION METHOD ***
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get all crops
        List<Crop> cropList = cropDAO.getAllCrops();
        request.setAttribute("cropList", cropList);

        // 2. Get all fertilizers
        List<Fertilizer> fertilizerList = fertilizerDAO.getAllFertilizers();
        request.setAttribute("fertilizerList", fertilizerList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/controlledByForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertControlledBy(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int crop_id = Integer.parseInt(request.getParameter("crop_id"));
        int fertilizer_id = Integer.parseInt(request.getParameter("fertilizer_id"));
        Date date_app = Date.valueOf(request.getParameter("date_app"));

        ControlledBy cb = new ControlledBy();
        cb.setCrop_id(crop_id);
        cb.setFertilizer_id(fertilizer_id);
        cb.setDate_app(date_app);

        controlledByDAO.addControlledBy(cb);
        response.sendRedirect("applies?action=list");
    }

    private void deleteControlledBy(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int crop_id = Integer.parseInt(request.getParameter("crop_id"));
        int fertilizer_id = Integer.parseInt(request.getParameter("fertilizer_id"));
        Date date_app = Date.valueOf(request.getParameter("date_app"));

        controlledByDAO.deleteControlledBy(crop_id, fertilizer_id, date_app);
        response.sendRedirect("applies?action=list");
    }
}