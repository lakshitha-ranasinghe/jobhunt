<%-- 
    Document   : vacancy
    Created on : Nov 25, 2015, 9:43:04 AM
    Author     : Muthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>JOB HUNT</title>

        <sx:head />
        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/external/css/css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="${pageContext.request.contextPath}/external/css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
                        <li class="active">
                            <a href="OpenNewVacancy"><i class="fa fa-fw fa-envelope"></i> New Vacancy</a>
                        </li>
                        <li>
                            <a href="OpenUpdateVacancy"><i class="fa fa-fw fa-book"></i> View Vacancies</a>
                        </li>
                        <li>
                            <a href="OpenCompanyInterviews"><i class="fa fa-fw fa-calendar"></i> Upcoming Interviews</a>
                        </li>
                        <li>
                            <a href="OpenCompanyOptions"><i class="fa fa-fw fa-wrench"></i> Options</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>


            <div id="page-wrapper">
                <div class="container-fluid">
                    <form role="form" action="AddVacancy" data-toggle="validator">
                        <div class="row">
                            <div class="col-lg-12">
                                <h1 class="page-header">
                                    New Vacancy....!!!
                                </h1>

                                <div class="row">
                                    <div class="col-xs-10">
                                        <ol class="breadcrumb">
                                            <li>
                                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                            </li>
                                            <li class="active"> <i class="fa fa-edit">Vacancy</i>
                                            </li>
                                        </ol>
                                    </div>                        
                                    <div class="col-xs-2">
                                        <button id="submitbutton" type="submit" class="btn btn-primary">Submit</button>
                                        <button type="reset" class="btn btn-danger">Reset</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.row -->

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">General Details</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body">

                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Job title</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div  lass="form-group">
                                                    <select name="vacancy.title" class="form-control" required="true">
                                                        <option selected="true" value=" ">Select a job title</option>
                                                        <option>CIO</option>
                                                        <option>Director of Engineering</option>
                                                        <option>Software Engineer</option>
                                                        <option>Technical Lead</option>
                                                        <option>Solution Architecture</option>
                                                        <option>Solution Engineer</option>
                                                        <option>System Engineer</option>
                                                        <option>Sales Representative</option>
                                                        <option>General Manager</option>
                                                        <option>Accounts Executive</option>
                                                        <option>Technical Supervisor</option>
                                                        <option>Marketing Executive</option>
                                                        <option>Channel Sales Operation Manager</option>
                                                        <option>Other</option>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Vacancy Description</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group">
                                                    <textarea name="vacancy.description" class="form-control" rows="3" placeholder="Describe about the vacancy you offer" required></textarea>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Prerequisites</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group">
                                                    <textarea name="vacancy.prerequisites" class="form-control" rows="3" placeholder="What are the qualification you seek" required></textarea>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Location / Branch </label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group">
                                                    <select name="vacancy.branch" class="form-control" required="true">
                                                        <option selected="true" value="">Select a Location</option>
                                                        <option>Central Province</option>
                                                        <option>Western Province</option>
                                                        <option>Northern Province</option>
                                                        <option>North Central Province</option>
                                                        <option>Sabaragamu Province</option>
                                                        <option>Uwa Province</option>
                                                        <option>Southern Province</option>
                                                        <option>Eastern Province</option>
                                                        <option>Other</option>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>                                                                            
                                    </div>
                                    <!-- /.panel-body -->
                                </div>
                                <br> 
                                <!-- /.panel -->
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                        <!-- /.row -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Internal Details</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body">

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Closing Date</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="vacancy.closingDate" />                                            
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Vacant Place Count</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input type="text" name="vacancy.vacancyCount" class="form-control" rows="3" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Salary</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input type="text" name="vacancy.salary" class="form-control" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>							
                                    </div>
                                    <!-- /.panel-body -->
                                </div>
                                <!-- /.panel -->
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                    </form>
                </div>
                <!-- /#page-wrapper -->         
                <!-- /#page-wrapper -->
            </div>
        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>

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

