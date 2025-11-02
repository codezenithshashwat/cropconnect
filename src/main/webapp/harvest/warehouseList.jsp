<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Warehouse Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Warehouse Master List</h1>
            <a href="warehouses?action=new" class="btn btn-success">Add New Warehouse</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>Total Capacity</th>
                    <th>Left Capacity</th>
                    <th>Actions</th>
                </tr>
            </thead>
           <tbody>
                           <c:forEach var="w" items="${warehouseList}">
                               <tr>
                                   <td><c:out value="${w.warehouse_id}" /></td>
                                   <td><c:out value="${w.name}" /></td>
                                   <td><c:out value="${w.city}" /></td>
                                   <td><c:out value="${w.total_capacity}" /></td>
                                   <td><c:out value="${w.left_capacity}" /></td>
                                   <td>
                                       <a href="warehouses?action=edit&id=<c:out value='${w.warehouse_id}'/>" class="btn btn-primary btn-sm">Edit</a>
                                       <a href="warehouses?action=delete&id=<c:out value='${w.warehouse_id}'/>"
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