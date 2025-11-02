<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crop Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Crop Master List</h1>
            <a href="crops?action=new" class="btn btn-success">Add New Crop</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Crop ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Season</th>
                    <th>Duration (Days)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="crop" items="${cropList}">
                    <tr>
                        <td><c:out value="${crop.crop_id}" /></td>
                        <td><c:out value="${crop.name}" /></td>
                        <td><c:out value="${crop.type}" /></td>
                        <td><c:out value="${crop.season}" /></td>
                        <td><c:out value="${crop.duration}" /></td>
                        <td>
                            <a href="crops?action=edit&id=<c:out value='${crop.crop_id}'/>" class="btn btn-primary btn-sm">Edit</a>
                            <a href="crops?action=delete&id=<c:out value='${crop.crop_id}'/>"
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