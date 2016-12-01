<%-- 
    Document   : error
    Created on : Dec 19, 2015, 11:00:03 AM
    Author     : Muthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title> JOB HUNT </title>
        
        <link href="${pageContext.request.contextPath}/external/css/errorstyle.css" rel="stylesheet">
        
        <style>
             .back{
                background-image: url("${pageContext.request.contextPath}/external/image/black1.jpg");
                min-height:645px;
                width:100%;
                -webkit-background-size: 100%;
                -moz-background-size: 100%;
                -o-background-size: 100%;
                background-size: 100%;

                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                color:black;
             }
        </style>
    </head>
    <body>
	<div class="wrap back">
            <div class="content">
                    <div class="logo">
                            <h2><a href="#"><img src="${pageContext.request.contextPath}/external/image/logo.png"/></a></h2>
                            <span><img src="${pageContext.request.contextPath}/external/image/signal.png"/>Oops! The Page you requested was not found!</span>
                    </div>
                    <div class="buttom">
                            <div class="seach_bar">
                                    <p>you can go to <span><a href="OpenLogin">home</a></span> page</p>
                            </div>
                    </div>
            </div>

            <p class="copy_right">&#169; by <a href="OpenLogin" target="_blank">&nbsp; jobhunt</a> </p>
	
        </div>	
    </body>
</html>
