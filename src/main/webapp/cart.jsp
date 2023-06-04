<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List"%>
<%@ page import = "org.upec.ecommerce.utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>

body {
	width: 100%;
	height: 100vh;
	padding: 8rem 6rem;
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 2rem 0;
}

.container {
	width: 50%;
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 2rem 0;
}

.item {
	width: 100%;
	display: flex;
	align-items: center;
	padding: 2rem;
	box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(30, 47, 151);
	--tw-shadow-color: #1E2F97;
}

.button {
	width: 100%;
  	border: none;
  	color: white;
  	padding: 12px 0px;
	text-align: center;
  	font-size: 14px;
  	cursor: pointer; 
  	border-radius: 0.25rem;
	transition: .2s ease-in-out 0s;
	text-decoration:none;
	font-weight: 600;
	background-color:#1E2F97;
}

.button:hover {
	transform: scale(1.1);
}

.total-container {
	width:100%;
	display: flex;
	flex-direction: column;
	border-top: 3px solid #1E2F97;
	margin-top: 4rem;
	padding: 1rem 0;
	gap: 0.7rem 0;
}

.total-item {
	display:flex;
	justify-content: space-between;
	justify-content;gap:0 1rem;
	padding: 0 0.5rem;
}

</style>

<body>
<%@include  file="navbar.html" %>
<%
	List<Product> products = (List<Product>) session.getAttribute("cartProducts");
%>
	<h1>My Cart</h1>
	<div class="container">
		<% for (Product product:  products) { %>
		<div class="item">
			<div style="width:80%;width: 100%;display: flex;align-items: center;gap: 0 2rem;">
				<h1><%= product.getProductName() %></h1>
				<span><%= product.getCategory() %></span>
				<span><%= product.getPrice() %>%</span>
			</div>
			<div style="width:20%">
				<button class="button"><a style="color:white; text-decoration:none;" href="cart?action=remove&id=<%=product.getId()%>">REMOVE</a></button>
			</div>
		</div>
		<% } %>
		<div class="total-container">
			<div class="total-item">
				<h3>subtotal</h3>
				<h3>${sessionScope.subtotal}$</h3>
			</div>
			<div class="total-item">
				<span>TVA</span>
				<h3>${sessionScope.tvaTotal}$</h3>
			</div>
			<div class="total-item">
				<h2>TOTAL</h2>
				<h2>${sessionScope.total}$</h2>
			</div>
		</div>
	</div>
</body>
</html>