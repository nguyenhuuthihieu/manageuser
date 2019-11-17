<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>System Error</title>
		<link href="./view/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<%@include file="Header.jsp" %>
		<form action="ADM002.html" method="post" name="inputform">
			<table  class="tbl_input"   border="0" width="80%"  cellpadding="0" cellspacing="0" >	
				<tr>
					<td align="center" colspan="2">
						<div style="height:50px"></div>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<font color = "red">${error}</font>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<div style="height:70px"></div>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input class="btn" type="submit" value="OK" onclick=""/>
					</td>
				</tr>
			</table>
		</form>
		<%@include file="Footer.jsp" %>
	</body>
</html>