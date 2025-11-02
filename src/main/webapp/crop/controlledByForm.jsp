<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log Fertilizer Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">
                <h1 class="card-title">Log Fertilizer Application</h1>
                <form action="applies?action=insert" method="post">

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
                        <label for="fertilizer_id" class="form-label">Select Fertilizer</label>
                        <select class="form-select" id="fertilizer_id" name="fertilizer_id" required>
                            <option value="">Select a Fertilizer</option>
                            <c:forEach var="f" items="${fertilizerList}">
                                <option value="${f.fertilizer_id}"><c:out value="${f.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="date_app" class="form-label">Application Date</label>
                        <input type="date" class="form-control" id="date_app" name="date_app" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Log Application</button>
                    <a href="applies?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>