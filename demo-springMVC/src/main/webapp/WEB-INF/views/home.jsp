<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="ra.demospringmvc.model.Post" %>
<%@ page import="ra.demospringmvc.ulti.Format" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/01/2024
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<form class="d-flex" action="/search" method="get">
    <input class="form-control me-2" type="text" name="search" value="${search}" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success" type="submit">Search</button>
</form>
<a href="/save" class="btn btn-primary">Add</a>
<div class="container">
    <h1 class="text-center">Danh sách bài </h1>
    <c:forEach items="${list}" var="post">
        <div class="card w-100 my-3">
            <c:set var="formattedDateTime" value="${ Format.formatLocalDateTime(post.createdAt, 'dd/MM/yyyy HH:mm:ss') }" />
            <p class="fs-6">${formattedDateTime}</p>
            <img src="${post.imageUrl}" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${post.title}</h5>
                <p class="card-text">${post.content}</p>
                <span class="btn btn-primary">${post.author}</span>
            </div>
            <a href="/save?id=${post.id}" class="btn btn-warning">Update</a>
            <a href="/delete?id=${post.id}" class="btn btn-danger">Delete</a>
        </div>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
