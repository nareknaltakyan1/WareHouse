<%@page import="SqlConn.SqlConnection"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WareHouse</title>
</head>
<body>
	<%
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection conn = SqlConnection.getConnection()) {
				System.out.println("Connection to WareHouse.Item succesfull!\n");

				List<String> list = SqlConnection.SelectItemList();

				try {
					for (String element : list) {
						out.print("<h3>" + element + "</h3>");
					}
				} finally {
					out.close();
				}
			}
		} catch (Exception ex) {
			System.out.println("Connection failed...");

			System.out.println(ex);
			try {
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
				out.println(
						"<div class=\"menu\"><a href=\"index.html\"> <button class=\"button\"><span>Error</span></button>");
			} finally {
				out.close();
			}
		}
	%>
</body>
</html>