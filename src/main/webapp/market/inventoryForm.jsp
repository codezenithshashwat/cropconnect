<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inventory Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${inventory != null}">
                    <h1 class="card-title">Edit Inventory Item</h1>
                    <form action="inventory?action=update" method="post">
                    <input type="hidden" name="inventory_id" value="<c:out value='${inventory.inventory_id}' />" />
                </c:if>
                <c:if test="${inventory == null}">
                    <h1 class="card-title">Add Product to Inventory</h1>
                    <form action="inventory?action=insert" method="post">
                </c:if>

                    <div class="mb-3">
                        <label for="warehouse_id" class="form-label">Warehouse</label>
                        <select class="form-select" id="warehouse_id" name="warehouse_id" required>
                            <option value="">Select a Warehouse</option>
                            <c:forEach var="w" items="${warehouseList}">
                                <option value="${w.warehouse_id}" ${inventory.warehouse_id == w.warehouse_id ? 'selected' : ''}>
                                    <c:out value="${w.name}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="product_id" class="form-label">Product</label>
                        <select class="form-select" id="product_id" name="product_id" required>
                            <option value="">Select a Product</option>
                            <c:forEach var="p" items="${productList}">
                                <option value="${p.product_id}" ${inventory.product_id == p.product_id ? 'selected' : ''}>
                                    <c:out value="${p.name}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="qty" class="form-label">Quantity</label>
                        <input type="text" class="form-control" id="qty" name="qty"
                               value="<c:out value='${inventory.qty}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="storage_condition" class="form-label">Storage Condition</label>
                        <select class="form-select" id="storage_condition" name="storage_condition" required>
                            <option value="">Select a Condition</option>
                            <option value="ambient" ${inventory.storage_condition == 'ambient' ? 'selected' : ''}>Ambient</option>
                            <option value="cool" ${inventory.storage_condition == 'cool' ? 'selected' : ''}>Cool</option>
                            <option value="cold" ${inventory.storage_condition == 'cold' ? 'selected' : ''}>Cold</option>
                            <option value="warm" ${inventory.storage_condition == 'warm' ? 'selected' : ''}>Warm</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Inventory</button>
                    <a href="inventory?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>