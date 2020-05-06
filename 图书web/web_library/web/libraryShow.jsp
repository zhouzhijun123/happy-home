<%-- 
    Document   : libraryShow
    Created on : 2020-4-20, 11:41:24
    Author     : 周志军
--%>
<%@page import="tool.book"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>  
<!DOCTYPE html>
<html>
<head>
<h3>图书展示</h3>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>

<body>  
 <% ArrayList bookslist=(ArrayList)session.getAttribute("bookslist"); %>	 
<table  border="1" width="1500" rules="none" cellspacing="0" cellpadding="0">
	<tr height="50"><td colspan="5" align="center">书籍信息如下</td></tr>
	<tr align="center" height="30" bgcolor="lightgrey">
		<td>id</td>
		<td>名称</td>
                <td>价格(元)</td>
		<td>作者</td>
                <td>日期</td>
	</tr>
	
 <% if (bookslist==null||bookslist.size()==0) { %>
     <tr height=\"50\"><td colspan=\"5\" align=\"center\">没有书籍可显示！</td></tr>
<% } else { 
        for(int i=0;i<bookslist.size();i++)
        {
           
	    book b=(book)bookslist.get(i);
%>
      <tr height="50" align="center">
            <td><%= b.getId()%></td>		
            <td><%= b.getTitle()%></td>
            <td><%= b.getPrice()%></td>
            <td><%= b.getAuthor()%></td>
            <td><%= b.getSubmission_date()%></td>
	</tr>
<% } %>
<% } %>		
	<tr height="50">
        <td align="center" colspan="5"><a href="addbook.jsp">添加图书</a></td>
</tr>
<tr>
    <td align="center" colspan="5">
    <ul class="pagination">
    <li class="page-item"><a class="page-link" href="Paging?action=pre">Previous</a></li>
    <li class="page-item"><a class="page-link" href="Paging?action=1">1</a></li>
    <li class="page-item"><a class="page-link" href="Paging?action=2">2</a></li>
    <li class="page-item"><a class="page-link" href="Paging?action=3">3</a></li>
    <li class="page-item"><a class="page-link" href="Paging?action=next">Next</a></li>
  </ul>
    </td>
</tr>
</table>
</body>
</html>