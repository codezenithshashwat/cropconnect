<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shipment Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">
                <h1 class="card-title">Log New Shipment</h1>
                <form action="shipments?action=insert" method="post">

                    <div class="mb-3">
                        <label for="product_id" class="form-label">Product</label>
                        <select class="form-select" id="product_id" name="product_id" required>
                            <option value="">Select a Product</option>
                            <c:forEach var="p" items="${productList}">
                                <option value="${p.product_id}"><c:out value="${p.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="market_id" class="form-label">Market</label>
                        <select class="form-select" id="market_id" name="market_id" required>
                            <option value="">Select a Market</option>
                            <c:forEach var="m" items="${marketList}">
                                <option value="${m.market_id}">
                                    <c:out value="${m.name}" /> (<c:out value="${m.city}" />)
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="vehicle_id" class="form-label">Vehicle ID</label>
                        <input type="text" class="form-control" id="vehicle_id" name="vehicle_id" required>
                    </div>

                    <div class="mb-3">
                        <label for="date" class="form-label">Shipment Date</label>
                        <input type="date" class="form-control" id="date" name="date" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Log Shipment</button>
                    <a href="shipments?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>