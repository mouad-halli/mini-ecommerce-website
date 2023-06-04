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

	<div class="products-container">
	<% for (Product product:  products) { %>
		<div class="product">
			<h1><%= product.getProductName() %></h1>
			<span><%= product.getCategory() %></span>
			<span><%= product.getPrice() %>$</span>
			<div class="buttons">
				<a href="cart?action=add&id=<%= product.getId() %>" class="button" style="width: 80%;background-color:#1E2F97">ADD TO CART</a>
			</div>
		</div>
	<% } %>
	</div>
</body>
