<%@ page import ="java.util.List"%>
<%@ page import = "org.upec.ecommerce.utils.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="./index.css"%></style>
<title>MyEcommerceCatalogue</title>
</head>
<body>
<%
	List<Product> products = (List<Product>) request.getAttribute("products");
%>
<%@include  file="navbar.html" %>

	<div style="display: flex;justify-content: space-between; padding: 0 2rem;">
		<h1>My Catalogue</h1>
		<a href="create-product-form" class="button" style="width: 15%;background-color:#1E2F97">CREATE PRODUCT</a>
	</div>
	<div class="products-container">
	<% for (Product product: products) { %>
		<div class="product">
			<h2><%= product.getProductName() %></h2>
			<span><%= product.getCategory() %></span>
			<span><%= product.getPrice() %>$</span>
			<div class="buttons">
				<a href="update-product-form?id=<%=product.getId()%>" class="button" style="width: 45%;background-color:#1AA7EC">UPDATE</a>
				<a href="delete-product?id=<%=product.getId()%>" class="button" style="width: 45%;background-color:#E63B60">DELETE</a>
			</div>
		</div>
	<% } %>
	</div>
</body>
