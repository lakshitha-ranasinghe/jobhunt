<%-- 
    Document   : custormize
    Created on : Dec 7, 2015, 1:50:10 PM
    Author     : Muthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
        <title>JOB HUNT</title>

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/external/css/sb-admin.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
    </head>
    <body>

        <div id="wrapper">
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
                    <a class="navbar-brand" href="OpenSeekerDashboard">JOB HUNT</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">

                    <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><span id="user"> </span><b class="caret"></b></a>
                        <ul class="dropdown-menu">   
                            <li>
                                <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="Logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li>
                            <a href="OpenSeekerDashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Profile<i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="demo" class="collapse">
                                <li>
                                    <a href="OpenProfile"> Personal</a>
                                </li>
                                <li>
                                    <a href="OpenEducationalProfile"> Education</a>
                                </li>
                                <li>
                                    <a href="OpenProfessionalProfile"> Professional</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="OpenRecommendation"><i class="fa fa-fw fa-table"></i> Recommendations</a>
                        </li>
                        <li>
                            <a href="OpenJobseekerInterviews"><i class="fa fa-fw fa-desktop"></i> Interviews</a>
                        </li>
                        <li>
                            <a href="ViewCompanies"><i class="fa fa-fw fa-edit"></i> Companies</a>
                        </li>
                        <li >
                            <a href="OpenCV"><i class="fa fa-fw fa-desktop"></i> Generate CV</a>
                        </li>
                        <li class="active">
                            <a href="OpenJobseekerOptions"><i class="fa fa-fw fa-wrench"></i> Options</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Customize your account...!!
                            </h1>
                            <div class="row">
                                <div class="col-xs-10">
                                    <ol class="breadcrumb">
                                        <li>
                                            <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                        </li>
                                        <li class="active">
                                            <i class="fa fa-edit"></i>Options
                                    </ol>
                                </div>                        
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <form action="ChangeUserPassword">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Security Details</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body" style="min-height:400px;">
                                        <div class = "row col-lg-offset-0">

                                            <div class="form-group">
                                                <div class="col-lg-10">
                                                    <label> User Name</label>
                                                    <input type="text" name="" class="textbox-inline form-control" value="<s:property value='userName'/>" id=""> 
                                                </div>
                                            </div>															
                                        </div>
                                        <div class = "row col-lg-offset-0">

                                            <div class="form-group">
                                                <div class="col-lg-10">
                                                    <label>Current Password</label>
                                                    <input type="password" name="currentPassword" class="textbox-inline form-control" id=""> 
                                                </div>
                                            </div>															
                                        </div>
                                        <div class = "row col-lg-offset-0">

                                            <div class="form-group">
                                                <div class="col-lg-10">
                                                    <label>New Password</label>
                                                    <input type="password" name="newPassword" class="textbox-inline form-control" id=""> 
                                                </div>
                                            </div>																
                                        </div>
                                        <div class = "row col-lg-offset-0">

                                            <div class="form-group">
                                                <div class="col-lg-10">
                                                    <label>Confirm New Password</label>
                                                    <input type="password" name="confirmNewPassword" class="textbox-inline form-control" id="">  
                                                </div>
                                            </div>																
                                        </div>
                                        <br><br>
                                        <div class = "row col-lg-offset-0">
                                            <div class="col-xs-4">
                                                <button id="submitbutton" type="submit" class="btn btn-primary">Submit</button>
                                                <button type="reset" class="btn btn-danger">Reset</button>
                                            </div>																
                                        </div>
                                    </div>

                                </div>
                            </form>
                            <!-- /.panel-body -->
                        </div>
                        <div class="col-lg-6">
                            <form action="ManageSeekerOption" enctype="multipart/form-data" method="post">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Profile Details</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body" style="min-height:400px;">
                                        <div class = "row col-lg-offset-0">
                                            <div class="form-group">
                                                <label class="control-label">Select Image</label>
                                                <s:file name="profilePicture" />
                                                <!--<input type="file" id="" name="" class="file" multiple data-show-upload="false" data-show-caption="true" accept="jpg,jpeg,png">-->
                                            </div>
                                            <div class="col-lg-offset-4">
                                                <img src="external/image/applicant.jpg" alt="..." class="img-thumbnail" width="150px" height="150px">
                                            </div>
                                        </div>
                                        <br><br><br>
                                        <div class =  "row col-lg-offset-0">
                                            <div class="col-xs-4">
                                                <button type="submit" class="btn btn-toolbar btn-primary">Submit</button>
                                                <button type="reset" class="btn btn-danger">Reset</button>
                                            </div>									
                                        </div>
                                    </div>
                                    <!-- /.panel-body -->
                                </div>
                                <!-- /.panel -->
                            </form>
                        </div>
                        <br> 
                        <!-- /.panel -->
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>       
        <script src="${pageContext.request.contextPath}/external/js/fileinput.js" type="text/javascript"></script>
    </body>
</html>
