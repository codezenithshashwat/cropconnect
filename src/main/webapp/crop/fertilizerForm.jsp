<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fertilizer Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${fertilizer != null}">
                    <h1 class="card-title">Edit Fertilizer</h1>
                    <form action="fertilizers?action=update" method="post">
                </c:if>
                <c:if test="${fertilizer == null}">
                    <h1 class="card-title">Add New Fertilizer</h1>
                    <form action="fertilizers?action=insert" method="post">
                </c:if>

                    <c:if test="${fertilizer != null}">
                        <input type="hidden" name="fertilizer_id" value="<c:out value='${fertilizer.fertilizer_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="fertilizer_id" class="form-label">Fertilizer ID</label>
                        <input type="number" class="form-control" id="fertilizer_id" name="fertilizer_id"
                               value="<c:out value='${fertilizer.fertilizer_id}' />"
                               <c:if test="${fertilizer != null}">readonly</c:if> required>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Fertilizer Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="<c:out value='${fertilizer.name}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="composition" class="form-label">Main Composition</label>
                        <select class="form-select" id="composition" name="composition" required>
                            <option value="">Select a Composition</option>
                            <option value="Nitrogen" ${fertilizer.composition == 'Nitrogen' ? 'selected' : ''}>Nitrogen</option>
                            <option value="Phosphorous" ${fertilizer.composition == 'Phosphorous' ? 'selected' : ''}>Phosphorous</option>
                            <option value="Potassium" ${fertilizer.composition == 'Potassium' ? 'selected' : ''}>Potassium</option>
                            <option value="Calcium" ${fertilizer.composition == 'Calcium' ? 'selected' : ''}>Calcium</option>
                            <option value="Magnesium" ${fertilizer.composition == 'Magnesium' ? 'selected' : ''}>Magnesium</option>
                            <option value="Sulphur" ${fertilizer.composition == 'Sulphur' ? 'selected' : ''}>Sulphur</option>
                            <option value="Iron" ${fertilizer.composition == 'Iron' ? 'selected' : ''}>Iron</option>
                            <option value="Zinc" ${fertilizer.composition == 'Zinc' ? 'selected' : ''}>Zinc</option>
                            <option value="Copper" ${fertilizer.composition == 'Copper' ? 'selected' : ''}>Copper</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Fertilizer</button>
                    <a href="fertilizers?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>