<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fertilizer Applications</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Fertilizer Application Log</h1>
            <a href="applies?action=new" class="btn btn-success">Log New Application</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Date Applied</th>
                    <th>Crop</th>
                    <th>Fertilizer Applied</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cb" items="${cbList}">
                    <tr>
                        <td><c:out value="${cb.date_app}" /></td>
                        <td><c:out value="${cb.crop_name}" /></td>
                        <td><c:out value="${cb.fertilizer_name}" /></td>
                        <td>
                            <a href="applies?action=delete&crop_id=${cb.crop_id}&fertilizer_id=${cb.fertilizer_id}&date_app=${cb.date_app}"
                               onclick="return confirm('Are you sure you want to delete this log entry?')"
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