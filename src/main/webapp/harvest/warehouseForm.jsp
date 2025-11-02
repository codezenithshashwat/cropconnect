<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Warehouse Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${warehouse != null}">
                    <h1 class="card-title">Edit Warehouse</h1>
                    <form action="warehouses?action=update" method="post">
                </c:if>
                <c:if test="${warehouse == null}">
                    <h1 class="card-title">Add New Warehouse</h1>
                    <form action="warehouses?action=insert" method="post">
                </c:if>

                    <c:if test="${warehouse != null}">
                        <input type="hidden" name="warehouse_id" value="<c:out value='${warehouse.warehouse_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="warehouse_id" class="form-label">Warehouse ID</label>
                        <input type="number" class="form-control" id="warehouse_id" name="warehouse_id"
                               value="<c:out value='${warehouse.warehouse_id}' />"
                               <c:if test="${warehouse != null}">readonly</c:if> required>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Warehouse Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="<c:out value='${warehouse.name}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="city" class="form-label">City</label>
                        <input type="text" class="form-control" id="city" name="city"
                               value="<c:out value='${warehouse.city}' />" required>
                    </div>

                    <div class.mb-3">
                        <label for="total_capacity" class="form-label">Total Capacity</label>
                        <input type="text" class="form-control" id="total_capacity" name="total_capacity"
                               value="<c:out value='${warehouse.total_capacity}' />" required>
                    </div>

                    <c:if test="${warehouse != null}">
                        <div class="mb-3">
                            <label for="left_capacity" class="form-label">Left Capacity</label>
                            <input type="text" class="form-control" id="left_capacity" name="left_capacity"
                                   value="<c:out value='${warehouse.left_capacity}' />" required>
                        </div>
                    </c:if>

                    <button type="submit" class="btn btn-primary">Save Warehouse</button>
                    <a href="warehouses?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>