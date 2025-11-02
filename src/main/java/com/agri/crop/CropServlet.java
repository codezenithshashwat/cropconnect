package com.agri.crop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/crops")
public class CropServlet extends HttpServlet {
    private CropDAO cropDAO;

    @Override
    public void init() {
        cropDAO = new CropDAO();
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
                deleteCrop(request, response);
                break;
            default:
                listCrops(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertCrop(request, response);
        } else if ("update".equals(action)) {
            updateCrop(request, response);
        }
    }

    private void listCrops(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Crop> cropList = cropDAO.getAllCrops();
        request.setAttribute("cropList", cropList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/cropList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/cropForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Crop existingCrop = cropDAO.getCropById(id);
        request.setAttribute("crop", existingCrop);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/cropForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCrop(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("crop_id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String season = request.getParameter("season");
        int duration = Integer.parseInt(request.getParameter("duration"));

        Crop newCrop = new Crop();
        newCrop.setCrop_id(id);
        newCrop.setName(name);
        newCrop.setType(type);
        newCrop.setSeason(season);
        newCrop.setDuration(duration);

        cropDAO.addCrop(newCrop);
        response.sendRedirect("crops?action=list");
    }

    private void updateCrop(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("crop_id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String season = request.getParameter("season");
        int duration = Integer.parseInt(request.getParameter("duration"));

        Crop crop = new Crop();
        crop.setCrop_id(id);
        crop.setName(name);
        crop.setType(type);
        crop.setSeason(season);
        crop.setDuration(duration);

        cropDAO.updateCrop(crop);
        response.sendRedirect("crops?action=list");
    }

    private void deleteCrop(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cropDAO.deleteCrop(id);
        response.sendRedirect("crops?action=list");
    }
}