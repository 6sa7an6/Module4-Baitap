<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/01/2024
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form action="/save" method="post">
        <c:if test="${song.id != null}">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="floatingId" value="${song.id}" readonly>
            <label for="floatingId">ID</label>
            </c:if>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingName" name="songName" placeholder="SongName"
                       value="${song.songName}">
                <label for="floatingName">SongName</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingAuthor" name="author" placeholder="Author"
                       value="${song.author}">
                <label for="floatingAuthor">Author</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" name="description" placeholder="Leave a description here"
                          id="floatingTextarea2" style="height: 100px">${song.description}</textarea>
                <label for="floatingTextarea2">Description</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" name="imageUrl" id="floatingImageUrl" placeholder="ImageUrl"
                       value="${song.imageUrl}">
                <label for="floatingImageUrl">ImageUrl</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" name="videoUrl" id="floatingVideoUrl" placeholder="VideoUrl"
                       value="${song.videoUrl}">
                <label for="floatingVideoUrl">VideoUrl</label>
            </div>
            <div class="form-floating">
                <input type="number" class="form-control" name="duration" id="floatingDuration" placeholder="Duration"
                       value="${song.duration}">
                <label for="floatingDuration">Duration</label>
            </div>
            <select name="status" class="form-select" aria-label="Default select example">
                <option selected>Show/Hidden</option>
                <option value="true">Show</option>
                <option value="false">Hidden</option>
            </select>
            <div>
                <p><input type="submit" class="btn btn-primary" value="Accept"></p>
                <p><a href="/home" class="btn btn-seconary">Cancel</a></p>
            </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
