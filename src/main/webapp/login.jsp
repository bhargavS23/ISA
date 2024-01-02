<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/9dc55b3f56.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./jsp/login.css">
        <title>Login Here</title>
        <style type="text/css">
        button{
        margin-top:16px;
        padding:12px;
        }
        </style> 
    </head>
    <body>
        <div id="wrapper">
        <% session.setMaxInactiveInterval(0); %>
            <i class="fa-solid fa-user"></i>
            <form action="login" method="post">
                <label for="user">Email Address</label>
            
                <input type="text" required name="email" placeholder="email">
                <label for="password">Password</label>
                <input type="text" required name="password" placeholder="password">
                <button type="submit">Login</button>
            </form>
           <%
           String invalid=(String)request.getAttribute("invalid");
           if(invalid!=null)
          out.println( "<p style=color:white;text-align:center;font-size:22px>"+ invalid +"</p>");
           %>
          
        </div>
        </body>
</html>