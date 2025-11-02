<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crop Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${crop != null}">
                    <h1 class="card-title">Edit Crop</h1>
                    <form action="crops?action=update" method="post">
                </c:if>
                <c:if test="${crop == null}">
                    <h1 class="card-title">Add New Crop</h1>
                    <form action="crops?action=insert" method="post">
                </c:if>

                    <c:if test="${crop != null}">
                        <input type="hidden" name="crop_id" value="<c:out value='${crop.crop_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="crop_id" class="form-label">Crop ID</label>
                        <input type="number" class="form-control" id="crop_id" name="crop_id"
                               value="<c:out value='${crop.crop_id}' />"
                               <c:if test="${crop != null}">readonly</c:if> required>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Crop Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="<c:out value='${crop.name}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="type" class="form-label">Crop Type</label>
                        <select class="form-select" id="type" name="type" required>
                            <option value="">Select a Type</option>
                            <option value="food" ${crop.type == 'food' ? 'selected' : ''}>Food</option>
                            <option value="cash" ${crop.type == 'cash' ? 'selected' : ''}>Cash</option>
                            <option value="horticultural" ${crop.type == 'horticultural' ? 'selected' : ''}>Horticultural</option>
                            <option value="plantation" ${crop.type == 'plantation' ? 'selected' : ''}>Plantation</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="season" class="form-label">Season</label>
                        <select class="form-select" id="season" name="season" required>
                            <option value="">Select a Season</option>
                            <option value="kharif" ${crop.season == 'kharif' ? 'selected' : ''}>Kharif</option>
                            <option value="rabi" ${crop.season == 'rabi' ? 'selected' : ''}>Rabi</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="duration" class="form-label">Duration (in days)</label>
                        <input type="number" class="form-control" id="duration" name="duration"
                               value="<c:out value='${crop.duration}' />" required>
                    </div>

                    <button type="submit" class.btn btn-primary">Save Crop</button>
                    <a href="crops?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>