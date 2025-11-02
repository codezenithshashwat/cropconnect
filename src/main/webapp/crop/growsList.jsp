<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crop Plot Assignments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Crop-to-Plot Assignments</h1>
            <a href="grows?action=new" class="btn btn-success">Assign Crop to Plot</a>
        </div>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Crop</th>
                    <th>Plot Location</th>
                    <th>Farmer</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="g" items="${growsList}">
                    <tr>
                        <td><c:out value="${g.crop_name}" /></td>
                        <td><c:out value="${g.plot_location}" /></td>
                        <td><c:out value="${g.farmer_name}" /></td>
                        <td><c:out value="${g.start_date}" /></td>
                        <td>
                            <c:if test="${g.end_date != null}">
                                <c:out value="${g.end_date}" />
                            </c:if>
                            <c:if test="${g.end_date == null}">
                                <span class="badge bg-success">Active</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${g.end_date == null}">
                                <form action="grows?action=endcrop" method="POST" style="display: inline;">
                                    <input type="hidden" name="crop_id" value="${g.crop_id}">
                                    <input type="hidden" name="plot_id" value="${g.plot_id}">
                                    <input type="date" name="end_date" required>
                                    <button type="submit" class="btn btn-warning btn-sm">End Crop</button>
                                </form>
                            </c:if>

                            <a href="grows?action=delete&crop_id=${g.crop_id}&plot_id=${g.plot_id}"
                               onclick="return confirm('Are you sure you want to permanently delete this record?')"
                               class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="index.html" class="btn btn-secondary mt-3">Back to Home</a>
    </div>
</body>
</html>