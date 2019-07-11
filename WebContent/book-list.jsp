<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%> 
<%@page import="net.bookmanagement.model.Book"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<h1>User Management</h1>
	<h2>
		<a href="book-new">Add New Book</a>
		&nbsp;&nbsp;&nbsp;
		<a href="book-list">List All Books</a>     
	</h2>
	 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Category</th>
                <th>Author</th>
                <th>Actions</th>
            </tr>
            <% 
            List<Book> bookList = (List<Book>)request.getAttribute("bookList");
            int index = 0;
        	for(Book book : bookList){%> 
            <tr> 
                <td><%=index%></td> 
                <td><%=book.get_bookName()%></td> 
                <td><%=book.get_category().get_categName()%></td> 
                <td><%=book.getAuthorNames(book.get_authors())%></td> 
                <td>
                     <a href="book-edit?id=<%=book.get_bookId()%>">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="book-delete?id=<%=book.get_bookId()%>">Delete</a>                     
                </td>
            </tr> 
            <% index ++;}%> 
        </table>
    </div> 
	
</body>
</html>