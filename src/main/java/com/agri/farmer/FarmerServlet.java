package com.agri.farmer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// This annotation maps the URL '/farmers' to this servlet
@WebServlet("/farmers")
public class FarmerServlet extends HttpServlet {
    private FarmerDAO farmerDAO;

    @Override
    public void init() {
        // Initialize the DAO once when the servlet is first loaded
        farmerDAO = new FarmerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the 'action' parameter from the URL
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action is to list farmers
        }

        // Route the request based on the action
        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteFarmer(request, response);
                break;
            default: // "list"
                listFarmers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the 'action' parameter from the form submission
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertFarmer(request, response);
        } else if ("update".equals(action)) {
            updateFarmer(request, response);
        }
    }

    // --- Private Helper Methods ---

    // Method to show the list of all farmers
    private void listFarmers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Farmer> farmerList = farmerDAO.getAllFarmers();
        request.setAttribute("farmerList", farmerList); // Store the list in the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("farmer/farmerList.jsp");
        dispatcher.forward(request, response); // Forward to the JSP page
    }

    // Method to show the "Add New Farmer" form
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("farmer/farmerForm.jsp");
        dispatcher.forward(request, response);
    }

    // Method to show the "Edit Farmer" form (pre-filled with data)
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Farmer existingFarmer = farmerDAO.getFarmerById(id);
        request.setAttribute("farmer", existingFarmer); // Store the farmer object in the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("farmer/farmerForm.jsp");
        dispatcher.forward(request, response);
    }

    // Method to handle inserting a new farmer
    private void insertFarmer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Get data from the HTML form
        int id = Integer.parseInt(request.getParameter("farmer_id"));
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");

        Farmer newFarmer = new Farmer();
        newFarmer.setFarmer_id(id);
        newFarmer.setName(name);
        newFarmer.setContact(contact);

        farmerDAO.addFarmer(newFarmer);
        response.sendRedirect("farmers?action=list"); // Redirect back to the list
    }

    // Method to handle updating an existing farmer
    private void updateFarmer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Get data from the HTML form
        int id = Integer.parseInt(request.getParameter("farmer_id"));
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");

        Farmer farmer = new Farmer();
        farmer.setFarmer_id(id);
        farmer.setName(name);
        farmer.setContact(contact);

        farmerDAO.updateFarmer(farmer);
        response.sendRedirect("farmers?action=list"); // Redirect back to the list
    }

    // Method to handle deleting a farmer
    private void deleteFarmer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        farmerDAO.deleteFarmer(id);
        response.sendRedirect("farmers?action=list"); // Redirect back to the list
    }
}