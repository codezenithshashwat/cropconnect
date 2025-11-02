<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Use the modern Jakarta JSTL tags --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Farmer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Farmer Management</h1>
            <a href="farmers?action=new" class="btn btn-success">Add New Farmer</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Farmer ID</th>
                    <th>Name</th>
                    <th>Contact</th>
                    <th>Reg. No</th>
                    <th>Total Size Owned (Hectares)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="farmer" items="${farmerList}">
                    <tr>
                        <td><c:out value="${farmer.farmer_id}" /></td>
                        <td><c:out value="${farmer.name}" /></td>
                        <td><c:out value="${farmer.contact}" /></td>
                        <td><c:out value="${farmer.reg_no}" /></td>
                        <td><c:out value="${farmer.size_owned}" /></td>
                        <td>
                            <a href="farmers?action=edit&id=<c:out value='${farmer.farmer_id}'/>" class="btn btn-primary btn-sm">Edit</a>

                            <a href="farmers?action=delete&id=<c:out value='${farmer.farmer_id}'/>"
                               onclick="return confirm('Are you sure you want to delete this farmer?')"
                               class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>