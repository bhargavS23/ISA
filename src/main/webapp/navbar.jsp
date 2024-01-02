<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<style>
*{
    padding: 0;
    margin: 0;
    font-family: sans-serif;
}
nav{
    background:linear-gradient(45deg,rgb(70, 70, 248),rgb(130, 186, 238));
    padding:26px 32px;
    display: flex;
    justify-content:center;
    gap: 70px;
    box-shadow: 0px 0px 10px black;
}
nav>a{
    color: white;
    text-decoration: none;
    font-weight: 600;

}
nav>a:hover{
    color: black;
}
</style>
</head>
<body>
<nav>
        <a href="index.jsp">Home</a>
        <a href="">About Us</a>
        <a href="">Contact Us</a>
        <a href="login.jsp">Logout</a>
    </nav>

</body>
</html>