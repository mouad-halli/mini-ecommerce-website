<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.button {
	width: 40%;
  	border: none;
  	color: white;
  	padding: 12px 0px;
  	margin-top: 1rem;
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
</style>
<body style="width: 100%; height: 100vh; padding: 10rem 6rem;">
<%@include  file="navbar.html" %>
<%@ page import = "org.upec.ecommerce.utils.*"%>

	<div style="width:100%; display:flex; justify-content: center;">
	<form method="post" action="${link}" style="width:50%; display:flex; flex-direction: column; align-items: center; row-gap: 1rem;">
		<h1 style="padding-bottom: 1rem;">${title}</h1>
		<input type="hidden" name="id" value="${productId}">
		<div style="width: 80%; display:flex; flex-direction: column; row-gap: 0.5rem;">
			<h3>Product name</h3>
			<input style="padding: 6px 0.8rem;border-radius: 0.375rem; border: 3px solid silver; " type="text" name="product-name" value="${productName}">
		</div>
		<div style="width:80%; display:flex; flex-direction: column; row-gap: 0.5rem;">
			<h3>Product Category</h3>
			<input style="padding: 6px 0.8rem;border-radius: 0.375rem; border: 3px solid silver; " type="text" name="product-category" value="${productCategory}">
		</div>
		<div style="width:80%; display:flex; flex-direction: column; row-gap: 0.5rem;">
			<h3>Product Price</h3>
			<input style="padding: 6px 0.8rem;border-radius: 0.375rem; border: 3px solid silver; " type="number" name="product-price" value="${productPrice}">
		</div>
		<div style="width:80%; display:flex; flex-direction: column; row-gap: 0.5rem;">
			<h3>Product TVA</h3>
			<input style="padding: 6px 0.8rem;border-radius: 0.375rem; border: 3px solid silver; " type="number" name="product-tva" value="${productTva}">
		</div>
		<button class="button" type="submit">${buttonText}</button>
	</form>
	</div>
</body>
</html>