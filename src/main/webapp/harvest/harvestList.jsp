<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Harvest Log</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Harvest Log</h1>
            <a href="harvests?action=new" class="btn btn-success">Log New Harvest</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Harvest ID</th>
                    <th>Date</th>
                    <th>Crop</th>
                    <th>From Plot</th>
                    <th>Total Yield</th>
                    <th>Stored In</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="h" items="${harvestList}">
                    <tr>
                        <td><c:out value="${h.harvest_id}" /></td>
                        <td><c:out value="${h.date}" /></td>
                        <td><c:out value="${h.crop_name}" /></td>
                        <td><c:out value="${h.plot_location}" /></td>
                        <td><c:out value="${h.total_yield}" /></td>
                        <td><c:out value="${h.warehouse_name}" /></td>
                        <td>
                            <a href="harvests?action=delete&id=<c:out value='${h.harvest_id}'/>"
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