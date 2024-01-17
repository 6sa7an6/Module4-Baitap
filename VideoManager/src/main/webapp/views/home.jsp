<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/01/2024
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<form class="d-flex" action="/search" method="get">
    <input class="form-control me-2" type="text" name="search" value="${search}" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success" type="submit">Search</button>
</form>
<a href="/save" class="btn btn-primary">Add</a>
<div class="container">
    <h1 class="text-center">Danh sách bài hát </h1>
    <c:forEach items="${list}" var="song">
        <div class="card w-100 my-3">
            <p class="fs-6">${song.songName}</p>
            <img src="${song.imageUrl}" class="card-img-top" alt="...">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${song.videoUrl}" allowfullscreen></iframe>
            </div>
            <div class="card-body">
                <h5 class="card-title">${song.description}</h5>
                <p class="card-text">Thời gian ${song.duration} Phút </p>
                <span class="btn btn-primary">${song.author}</span>
            </div>
            <a href="/save?id=${song.id}" class="btn btn-warning" >Update</a>
            <a href="/delete?id=${song.id}" class="btn btn-danger">Delete</a>
        </div>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
