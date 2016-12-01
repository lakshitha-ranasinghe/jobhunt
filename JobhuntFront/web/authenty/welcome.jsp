<%-- 
    Document   : welcome
    Created on : Dec 16, 2015, 8:04:01 AM
    Author     : Lakshitha
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

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/external/css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="${pageContext.request.contextPath}/external/css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>

        <script src="${pageContext.request.contextPath}/external/js/Chart.js"></script>


        <style>
             .back{
                background-image: url("${pageContext.request.contextPath}/external/image/welcomeimage.jpg");
                background-attachment: relative;
                background-position: center center;
                min-height: 600px;
                width:100%;
                -webkit-background-size: 100%;
                -moz-background-size: 100%;
                -o-background-size: 100%;
                background-size: 100%;

                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                color: white;
             }
            .btn-primary{
                background-color: transparent;
                color: #6f5499;
                border: 1px solid blue;
            }
        </style>
    </head>
    
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">JOB HUNT</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                
            </ul>
          <!-- /.navbar-collapse -->
        </nav>
        
        <form action="OpenLogin">
            <div class="back">
                <div class = "row" style = "padding:380px 80px 10px 850px;">
                    <div class = "show" style = "left-margin:500px;">
                       <button type="submit" class="btn btn-primary btn-lg">&#160&#160Continue&#160&#160</button>
                    </div>   
                </div>
            </div>
        </form>
        
        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>
<!--        <form action="OpenLogin">
            <input type="submit" value="welcome"/>
        </form>-->
    </body>
</html>
