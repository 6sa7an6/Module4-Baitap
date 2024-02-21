<%@ page import="java.util.List" %>
<%@ page import="com.example._20240107.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<% List<Customer> list = (List<Customer>) request.getAttribute("list");%>
<h1>
  <%= "Danh sách khách hàng" %></h1>
<br/>
<a href="/add-customer">Add customer</a>
<br>
<form method="get" action="/add-customer" id="send">
  <table border="1px">
<thead>
<td>ID</td>
  <td>Tên</td>
  <td>Ngày sinh</td>
  <td>Địa chỉ</td>
  <td>Chỉnh sửa</td>
</thead>
  <tbody>
<c:forEach items="${list}" var="customer" varStatus="loop">
  <tr>
    <td>${customer.id}</td>
    <td>${customer.name}</td>
    <td>${customer.birthday}</td>
    <td>${customer.address}</td>
    <td><input onclick="clickid('${customer.id}')" type="button" value="update" name="action">
      <input onclick="clickid('${customer.id}')" type="button" value="delete" name="action">
    </td>
  </tr>
</c:forEach>
  </tbody>
</table>
  <input type="hidden" name="id" id="id">
</form>
</body>
<script>
  function clickid(id){
    document.getElementById("id").value = id;
    document.getElementById("send").submit();
  }
</script>
</html>