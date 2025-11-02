package com.agri.crop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fertilizers")
public class FertilizerServlet extends HttpServlet {

    private FertilizerDAO fertilizerDAO;

    @Override
    public void init() {
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
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteFertilizer(request, response);
                break;
            default:
                listFertilizers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertFertilizer(request, response);
        } else if ("update".equals(action)) {
            updateFertilizer(request, response);
        }
    }

    private void listFertilizers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Fertilizer> fertilizerList = fertilizerDAO.getAllFertilizers();
        request.setAttribute("fertilizerList", fertilizerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/fertilizerList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/fertilizerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fertilizer existingFertilizer = fertilizerDAO.getFertilizerById(id);
        request.setAttribute("fertilizer", existingFertilizer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crop/fertilizerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertFertilizer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("fertilizer_id"));
        String name = request.getParameter("name");
        String composition = request.getParameter("composition");

        Fertilizer newFertilizer = new Fertilizer();
        newFertilizer.setFertilizer_id(id);
        newFertilizer.setName(name);
        newFertilizer.setComposition(composition);

        fertilizerDAO.addFertilizer(newFertilizer);
        response.sendRedirect("fertilizers?action=list");
    }

    private void updateFertilizer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("fertilizer_id"));
        String name = request.getParameter("name");
        String composition = request.getParameter("composition");

        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setFertilizer_id(id);
        fertilizer.setName(name);
        fertilizer.setComposition(composition);

        fertilizerDAO.updateFertilizer(fertilizer);
        response.sendRedirect("fertilizers?action=list");
    }

    private void deleteFertilizer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        fertilizerDAO.deleteFertilizer(id);
        response.sendRedirect("fertilizers?action=list");
    }
}