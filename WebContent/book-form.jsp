<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.bookmanagement.model.*"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Book book = (Book)request.getAttribute("book");%> 
	<center>
  		<h1>User Management</h1>
        <h2>
         <a href="book-new">Add New User</a>
         &nbsp;&nbsp;&nbsp;
         <a href="book-list">List All Users</a>
        </h2>
 	</center>
    <div align="center">
  	<%  if (book != null) {%>
   		<form action="book-update" method="post">
   	<%}%>
    <% if (book == null) { %>
   		<form action="book-insert" method="post">
   	<%}%>
        <table border="1" cellpadding="5">
            <caption>
	             <h2>
	              <% if (book != null) {%>
	               Edit Book
	               <%}%>
	              <% if (book == null) {%>
	               Add New Book
	               <%}%>
	             </h2>
            </caption>
			<% if (book != null) {%>
          		<input type="hidden" name="id" value="${book.get_bookId()}" />
          	<%}%>
            <tr>
                <th>Book Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="${book.get_bookName()}"
                  />
                </td>
            </tr>
            <tr>
                <th>Category: </th>
                <td>
                 <input type="text" name="category" size="45"
                   value="${book.get_category().get_categName()}"
                 />
                </td>
            </tr>
            <tr>
                <th>Author: </th>
                <td>
                 <input type="text" name="author" size="45"
                   value="${book.getAuthorNames(book.get_authors())}"
                 />
                </td>
            </tr>
        </table>
        <input type="submit" value="Save" />
        </form>
    </div> 
</body>
</html>