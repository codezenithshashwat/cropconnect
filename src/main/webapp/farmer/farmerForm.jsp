<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Farmer Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-body">

                <c:if test="${farmer != null}">
                    <h1 class="card-title">Edit Farmer</h1>
                    <form action="farmers?action=update" method="post">
                </c:if>
                <c:if test="${farmer == null}">
                    <h1 class="card-title">Add New Farmer</h1>
                    <form action="farmers?action=insert" method="post">
                </c:if>

                    <c:if test="${farmer != null}">
                        <input type="hidden" name="farmer_id" value="<c:out value='${farmer.farmer_id}' />" />
                    </c:if>

                    <div class="mb-3">
                        <label for="farmer_id" class="form-label">Farmer ID</label>
                        <input type="number" class="form-control" id="farmer_id" name="farmer_id"
                               value="<c:out value='${farmer.farmer_id}' />"
                               <c:if test="${farmer != null}">readonly</c:if>
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="<c:out value='${farmer.name}' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="contact" class="form-label">Contact Number</label>
                        <input type="text" class="form-control" id="contact" name="contact"
                               value="<c:out value='${farmer.contact}' />" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Farmer</button>
                    <a href="farmers?action=list" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>