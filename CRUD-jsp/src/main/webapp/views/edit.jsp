<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/01/2024
  Time: 00:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/product-controller/update" method="post">
    <div class="modal-content">
        <div class="modal-header">
            <h1 class="modal-title fs-5">Update Product</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
                <div class="mb-3">
                    <label for="product_id" class="form-label">Product ID</label>
                    <input type="number" class="form-control" name="id" id="product_id" value="${product.id}" readonly>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Product Name</label>
                    <input type="text" class="form-control" name="productName" id="name" value="${product.productName}">
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Descriptions</label>
                    <textarea class="form-control" name="description" id="description" rows="3">${product.description}</textarea>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Product Price</label>
                    <input type="number" class="form-control" name="price" id="price" value="${product.price}">
                </div>
                <div class="mb-3">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="number" id="stock" name="stock" class="form-control" value="${product.stock}">
                </div>
                <div class="mb-3">
                    <label for="product_status" class="form-label">Product Status</label>
                    <select  id="product_status" name="status">
                        <option value="true">Bán</option>
                        <option value="false">Không Bán</option>
                    </select>
                </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Edit</button>
        </div>
    </div>
</form>
</body>
</html>
