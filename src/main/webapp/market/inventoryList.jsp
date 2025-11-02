<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Warehouse Inventory</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Warehouse Inventory</h1>
            <a href="inventory?action=new" class="btn btn-success">Add Product to Inventory</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Warehouse</th>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Storage Condition</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="i" items="${inventoryList}">
                    <tr>
                        <td><c:out value="${i.warehouse_name}" /></td>
                        <td><c:out value="${i.product_name}" /></td>
                        <td><c:out value="${i.qty}" /></td>
                        <td><c:out value="${i.storage_condition}" /></td>
                        <td>
                            <a href="inventory?action=edit&id=<c:out value='${i.inventory_id}'/>" class="btn btn-primary btn-sm">Edit</a>
                            <a href="inventory?action=delete&id=<c:out value='${i.inventory_id}'/>"
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