<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Plot Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${plot != null}">
                    <h1 class="card-title">Edit Plot</h1>
                    <form action="plots?action=update" method="post">
                </c:if>
                <c:if test="${plot == null}">
                    <h1 class="card-title">Add New Plot</h1>
                    <form action="plots?action=insert" method="post">
                </c:if>

                    <c:if test="${plot != null}">
                        <input type="hidden" name="plot_id" value="<c:out value='${plot.plot_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="plot_id" class="form-label">Plot ID</label>
                        <input type="number" class="form-control" id="plot_id" name="plot_id"
                               value="<c:out value='${plot.plot_id}' />"
                               <c:if test="${plot != null}">readonly</c:if> required>
                    </div>

                    <div class="mb-3">
                        <label for="size" class="form-label">Size (Hectares)</label>
                        <input type="text" class="form-control" id="size" name="size"
                               value="<c:out value='${plot.size}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="location" class="form-label">Location</label>
                        <input type="text" class="form-control" id="location" name="location"
                               value="<c:out value='${plot.location}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <select class="form-select" id="status" name="status" required>
                            <option value="vacant" ${plot.status == 'vacant' ? 'selected' : ''}>Vacant</option>
                            <option value="cultivated" ${plot.status == 'cultivated' ? 'selected' : ''}>Cultivated</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="owned_by" class="form-label">Owned By</label>
                        <select class="form-select" id="owned_by" name="owned_by" required>
                            <option value="">Select a Farmer</option>
                            <c:forEach var="farmer" items="${farmerList}">
                                <option value="${farmer.farmer_id}" ${plot.owned_by == farmer.farmer_id ? 'selected' : ''}>
                                    <c:out value="${farmer.name}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Plot</button>
                    <a href="plots?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>