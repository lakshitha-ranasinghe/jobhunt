<%-- 
    Document   : option
    Created on : Dec 13, 2015, 5:16:40 PM
    Author     : Muthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>JOB HUNT</title>

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/external/css/css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="${pageContext.request.contextPath}/external/css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script>
            $.getJSON('CompanyNameForTopNav', {}, function (data) {
                $('#user').text(" " + data.userName);
            });
        </script>
    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="OpenCompanyDashboard">JOB HUNT</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-default navbar-right top-nav">
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
                <div class="collapse navbar-collapse navbar-default navbar-ex1-collapse">
                    <ul class="nav navbar-nav default-nav side-nav">
                        <li>
                            <a href="OpenCompanyDashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="OpenCompanyProfile"><i class="fa fa-fw fa-edit"></i> Profile</a>
                        </li>
                        <li>
                            <a href="OpenNewVacancy"><i class="fa fa-fw fa-envelope"></i> New Vacancy</a>
                        </li>
                        <li>
                            <a href="OpenUpdateVacancy"><i class="fa fa-fw fa-book"></i> View Vacancies</a>
                        </li>
                        <li>
                            <a href="OpenCompanyInterviews"><i class="fa fa-fw fa-table"></i> Upcoming Interviews</a>
                        </li>
                        <li class="active">
                            <a href="OpenCompanyOptions"><i class="fa fa-fw fa-wrench"></i> Options</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>


            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Customize Your Account..!!
                            </h1>

                            <div class="row">
                                <div class="col-xs-12">
                                    <ol class="breadcrumb">
                                        <li>
                                            <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                        </li>
                                        <li class="active"> <i class="fa fa-edit">Options</i>
                                        </li>
                                    </ol>
                                </div>                        
                            </div>
                        </div>
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-6">
                            <form action="ChangeCompanyPassword">
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
                                                <input type="text" class="textbox-inline form-control" value="<s:property value='userName'/>" id="">                            
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
                                        <br>
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
                            <form>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Profile Details</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body" style="min-height:400px;">
                                        <div class = "row col-lg-offset-0">
                                            <div class="form-group">
                                                <label class="control-label"> Select Image</label>
                                                <s:file name="profilePicture"/>
                                                <!--<input type="file" id="" name="" class="file" multiple data-show-upload="false" data-show-caption="true" accept="jpg,jpeg,png">-->
                                            </div>
                                            <div class="col-lg-offset-4">
                                                <img src="external/image/company.jpg"  alt="..." class="img-thumbnail">
                                            </div>
                                        </div>
                                        <br>
                                        <div class =  "row col-lg-offset-0">
                                            <div class="col-xs-4">
                                                <button id="submitbutton" type="submit" class="btn btn-primary">Submit</button>
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
                    <!-- /.col-lg-12 -->
                </div>                
                <br>
            </div>
            <!-- /#page-wrapper -->         
            <!-- /#page-wrapper -->
        </div>
    </div>
    <!-- /#wrapper -->


    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>

    <!-- Bootstrap Validator JavaScript -->
    <script src="${pageContext.request.contextPath}/external/js/validator.min.js"></script>
    <script src="${pageContext.request.contextPath}/external/js/validator.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/external/js/plugins/morris/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris-data.js"></script>

</body>

</html>
