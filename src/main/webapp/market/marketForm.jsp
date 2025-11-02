<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Market Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${market != null}">
                    <h1 class="card-title">Edit Market</h1>
                    <form action="markets?action=update" method="post">
                </c:if>
                <c:if test="${market == null}">
                    <h1 class="card-title">Add New Market</h1>
                    <form action="markets?action=insert" method="post">
                </c:if>

                    <c:if test="${market != null}">
                        <input type="hidden" name="market_id" value="<c:out value='${market.market_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="market_id" class="form-label">Market ID</label>
                        <input type="number" class="form-control" id="market_id" name="market_id"
                               value="<c:out value='${market.market_id}' />"
                               <c:if test="${market != null}">readonly</c:if> required>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Market Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="<c:out value='${market.name}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="city" class="form-label">City</label>
                        <input type="text" class="form-control" id="city" name="city"
                               value="<c:out value='${market.city}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="type" class.form-label">Type</label>
                        <select class="form-select" id="type" name="type" required>
                            <option value="">Select a Type</option>
                            <option value="wholesale" ${market.type == 'wholesale' ? 'selected' : ''}>Wholesale</option>
                            <option value="retail" ${market.type == 'retail' ? 'selected' : ''}>Retail</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Market</button>
                    <a href="markets?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>