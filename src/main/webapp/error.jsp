<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%; display: flex; justify-content: center;">
  		<h1>Error</h1>
  		<h2><%=exception.getMessage() %><br/> </h2>
 	</div> 
</body>
</html>