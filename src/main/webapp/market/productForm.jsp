<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${product != null}">
                    <h1 class="card-title">Edit Product</h1>
                    <form action="products?action=update" method="post">
                </c:if>
                <c:if test="${product == null}">
                    <h1 class="card-title">Add New Product</h1>
                    <form action="products?action=insert" method="post">
                </c:if>

                    <c:if test="${product != null}">
                        <input type="hidden" name="product_id" value="<c:out value='${product.product_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="product_id" class="form-label">Product ID</label>
                        <input type="number" class="form-control" id="product_id" name="product_id"
                               value="<c:out value='${product.product_id}' />"
                               <c:if test="${product != null}">readonly</c:if> required>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Product Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="<c:out value='${product.name}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="type" class="form-label">Type</label>
                        <select class="form-select" id="type" name="type" required>
                            <option value="">Select a Type</option>
                            <option value="raw" ${product.type == 'raw' ? 'selected' : ''}>Raw</option>
                            <option value="processed" ${product.type == 'processed' ? 'selected' : ''}>Processed</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="unit_price" class="form-label">Unit Price</label>
                        <input type="text" class="form-control" id="unit_price" name="unit_price"
                               value="<c:out value='${product.unit_price}' />" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Product</button>
                    <a href="products?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>