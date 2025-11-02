<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Assign Crop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class.card-body">
                <h1 class="card-title">Assign Crop to Plot</h1>
                <form action="grows?action=insert" method="post">

                    <div class="mb-3">
                        <label for="crop_id" class="form-label">Select Crop</label>
                        <select class="form-select" id="crop_id" name="crop_id" required>
                            <option value="">Select a Crop</option>
                            <c:forEach var="crop" items="${cropList}">
                                <option value="${crop.crop_id}"><c:out value="${crop.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="plot_id" class="form-label">Select Plot</label>
                        <select class="form-select" id="plot_id" name="plot_id" required>
                            <option value="">Select a Plot</option>
                            <c:forEach var="plot" items="${plotList}">
                                <option value="${plot.plot_id}">
                                    <c:out value="${plot.location}" /> (Owned by: <c:out value="${plot.farmer_name}" />)
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="start_date" class="form-label">Start Date</label>
                        <input type="date" class="form-control" id="start_date" name="start_date" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Assign Crop</button>
                    <a href="grows?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>