<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2024
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<form action="/save" method="post">
    <c:if test="${post.id != null}">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="floatingId" name="id" value="${post.id}" readonly>
            <label for="floatingId">ID</label>
        </div>
        <div class="form-floating">
            <input type="datetime-local" class="form-control" id="floatingCreatedAt" name="createdAt" value="${post.createdAt}" readonly>
            <label for="floatingCreatedAt">CreateAt</label>
        </div>
    </c:if>
<div class="form-floating mb-3">
    <input type="text" class="form-control" id="floatingAuthor" placeholder="Author Name" name="author" value="${post.author}">
    <label for="floatingAuthor">Author</label>
</div>
<div class="form-floating">
    <input type="text" class="form-control" id="floatingTitle" placeholder="Title" name="title" value="${post.title}">
    <label for="floatingTitle">Title</label>
</div>
    <div class="form-floating">
        <input type="text" class="form-control" id="floatingContent" placeholder="Content" name="content" value="${post.content}">
        <label for="floatingContent">Content</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" id="floatingImageUrl" placeholder="ImageUrl" name="imageUrl" value="${post.imageUrl}">
        <label for="floatingImageUrl">ImageUrl</label>
    </div>
    <div>
        <p><input type="submit" class="btn btn-primary" value="Accept"></p>
        <p><a href="/home" class="btn btn-seconary">Cancel</a></p>
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
