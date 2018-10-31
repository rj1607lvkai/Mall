<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2018/10/16
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${userList }" var="u">

        ${u.username }
    <br>

    </c:forEach>
</body>
</html>
