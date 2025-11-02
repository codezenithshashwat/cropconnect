<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fertilizer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Fertilizer Master List</h1>
            <a href="fertilizers?action=new" class="btn btn-success">Add New Fertilizer</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Fertilizer ID</th>
                    <th>Name</th>
                    <th>Main Composition</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="fertilizer" items="${fertilizerList}">
                    <tr>
                        <td><c:out value="${fertilizer.fertilizer_id}" /></td>
                        <td><c:out value="${fertilizer.name}" /></td>
                        <td><c:out value="${fertilizer.composition}" /></td>
                        <td>
                            <a href="fertilizers?action=edit&id=<c:out value='${fertilizer.fertilizer_id}'/>" class="btn btn-primary btn-sm">Edit</a>
                            <a href="fertilizers?action=delete&id=<c:out value='${fertilizer.fertilizer_id}'/>"
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