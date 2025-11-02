<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log Harvest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">
                <h1 class="card-title">Log New Harvest</h1>
                <form action="harvests?action=insert" method="post">

                    <div class="mb-3">
                        <label for="harvest_id" class="form-label">Harvest ID</label>
                        <input type="number" class="form-control" id="harvest_id" name="harvest_id" required>
                    </div>

                    <div class="mb-3">
                        <label for="grows_id" class="form-label">Select Active Crop</label>
                        <select class="form-select" id="grows_id" name="grows_id" required>
                            <option value="">Select a crop that is currently growing...</option>
                            <c:forEach var="g" items="${activeGrowsList}">
                                <option value="${g.crop_id}-${g.plot_id}">
                                    <c:out value="${g.crop_name}" /> at
                                    <c:out value="${g.plot_location}" /> (Farmer: <c:out value="${g.farmer_name}" />)
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="harvest_date" class="form-label">Harvest Date</label>
                        <input type="date" class="form-control" id="harvest_date" name="harvest_date" required>
                    </div>

                    <div class="mb-3">
                        <label for="total_yield" class="form-label">Total Yield (in tons or units)</label>
                        <input type="text" class="form-control" id="total_yield" name="total_yield" required>
                    </div>

                    <div class="mb-3">
                        <label for="warehouse_id" class="form-label">Store In Warehouse</label>
                        <select class="form-select" id="warehouse_id" name="warehouse_id" required>
                            <option value="">Select a warehouse...</option>
                            <c:forEach var="w" items="${warehouseList}">
                                <option value="${w.warehouse_id}">
                                    <c:out value="${w.name}" /> (<c:out value="${w.city}" />)
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Log Harvest</button>
                    <a href="harvests?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>