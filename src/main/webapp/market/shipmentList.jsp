<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shipment Log</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Shipment Log</h1>
            <a href="shipments?action=new" class="btn btn-success">Log New Shipment</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Date</th>
                    <th>Product</th>
                    <th>Sent to Market</th>
                    <th>Vehicle ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${shipmentList}">
                    <tr>
                        <td><c:out value="${s.date}" /></td>
                        <td><c:out value="${s.product_name}" /></td>
                        <td><c:out value="${s.market_name}" /></td>
                        <td><c:out value="${s.vehicle_id}" /></td>
                        <td>
                            <a href="shipments?action=delete&pid=${s.product_id}&mid=${s.market_id}&vid=${s.vehicle_id}&date=${s.date}"
                               onclick="return confirm('Are you sure?')"
                               class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="index.html" class="btn btn-secondary mt-3">Back to Home</a>
    </div>
</body>
</html>