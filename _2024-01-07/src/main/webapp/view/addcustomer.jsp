<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/01/2024
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="add-customer" method="post">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${customer.name}">
    <label for="birthday">Birthday</label>
    <input type="text" name="birthday" id="birthday" value="${customer.birthday}">
    <label for="address">Address</label>
    <input type="text" name="address" id="address" value="${customer.address}">
    <input type="submit" value="add" name="addcustomer">
</form>
</body>
</html>
