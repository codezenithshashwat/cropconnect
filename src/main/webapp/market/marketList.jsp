<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Market Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Market Master List</h1>
            <a href="markets?action=new" class="btn btn-success">Add New Market</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${marketList}">
                    <tr>
                        <td><c:out value="${m.market_id}" /></td>
                        <td><c:out value="${m.name}" /></td>
                        <td><c:out value="${m.city}" /></td>
                        <td><c:out value="${m.type}" /></td>
                        <td>
                            <a href="markets?action=edit&id=<c:out value='${m.market_id}'/>" class="btn btn-primary btn-sm">Edit</a>
                            <a href="markets?action=delete&id=<c:out value='${m.market_id}'/>"
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