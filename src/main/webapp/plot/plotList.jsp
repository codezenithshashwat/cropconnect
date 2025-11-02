<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Farm Plot Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Farm Plot Management</h1>
            <a href="plots?action=new" class="btn btn-success">Add New Plot</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Plot ID</th>
                    <th>Size (Hectares)</th>
                    <th>Location</th>
                    <th>Status</th>
                    <th>Owned By (Farmer)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plot" items="${plotList}">
                    <tr>
                        <td><c:out value="${plot.plot_id}" /></td>
                        <td><c:out value="${plot.size}" /></td>
                        <td><c:out value="${plot.location}" /></td>
                        <td><c:out value="${plot.status}" /></td>
                        <td><c:out value="${plot.farmer_name}" /></td>
                        <td>
                            <a href="plots?action=edit&id=<c:out value='${plot.plot_id}'/>" class="btn btn-primary btn-sm">Edit</a>
                            <a href="plots?action=delete&id=<c:out value='${plot.plot_id}'/>"
                               onclick="return confirm('Are you sure? This will also update farmer total size.')"
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