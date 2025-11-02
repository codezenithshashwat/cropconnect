package com.agri.market;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/markets")
public class MarketServlet extends HttpServlet {
    private MarketDAO marketDAO;

    @Override
    public void init() {
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
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteMarket(request, response);
                break;
            default:
                listMarkets(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertMarket(request, response);
        } else if ("update".equals(action)) {
            updateMarket(request, response);
        }
    }

    private void listMarkets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Market> marketList = marketDAO.getAllMarkets();
        request.setAttribute("marketList", marketList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("market/marketList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("market/marketForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Market existingMarket = marketDAO.getMarketById(id);
        request.setAttribute("market", existingMarket);
        RequestDispatcher dispatcher = request.getRequestDispatcher("market/marketForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMarket(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("market_id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String type = request.getParameter("type");

        Market newMarket = new Market();
        newMarket.setMarket_id(id);
        newMarket.setName(name);
        newMarket.setCity(city);
        newMarket.setType(type);

        marketDAO.addMarket(newMarket);
        response.sendRedirect("markets?action=list");
    }

    private void updateMarket(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("market_id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String type = request.getParameter("type");

        Market market = new Market();
        market.setMarket_id(id);
        market.setName(name);
        market.setCity(city);
        market.setType(type);

        marketDAO.updateMarket(market);
        response.sendRedirect("markets?action=list");
    }

    private void deleteMarket(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        marketDAO.deleteMarket(id);
        response.sendRedirect("markets?action=list");
    }
}