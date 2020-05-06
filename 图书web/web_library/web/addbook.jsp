<%-- 
    Document   : addbook
    Created on : 2020-4-20, 17:10:09
    Author     : 周志军
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>  
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap 实例</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>添加书籍</h2>
  <form action="DoBook" method="post" charset="utf-8">
    <div class="form-group">
      <label for="title">书名:</label>
      <input type="text" class="form-control" name="title" placeholder="书名">
    </div>
    <div class="form-group">
      <label for="price">价格</label>
      <input type="text" class="form-control" name="price" placeholder="输入价格">
    </div>
   <div class="form-group">
      <label for="author">作者</label>
      <input type="text" class="form-control" name="author" placeholder="输入作者">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>
</html>
