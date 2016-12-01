<%-- 
    Document   : index
    Created on : Nov 25, 2015, 8:57:37 AM
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

        <!--Pie chart JS-->
        <script src="${pageContext.request.contextPath}/external/js/Chart.js"></script>

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>


        <script>
            $(document).ready(function () {
                hideNoProfileError();
                hideSuccess();

                $.getJSON('CompanyNameForTopNav', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                function showSuccess() {
                    $('#success').show();
                    $('#success').delay(5000).fadeOut('slow');
                }
                function hideSuccess() {
                    $('#success').hide();
                }
                function showNoProfileError() {
                    $('#noprofileerror').show();
                    $('#noprofileerror').delay(5000).fadeOut('slow');
                }
                function hideNoProfileError() {
                    $('#noprofileerror').hide();
                }


                var openAction = $('#openaction').val();
                if (openAction.toString() === 'false') {
                    showNoProfileError();
                }

                var approvedTimes;
                var rejectedTimes;
                var pendingTimes;
                $.getJSON('CompanyApprovalRejectInformation', {}, function (data) {
                    approvedTimes = data.approved;
                    rejectedTimes = data.rejected;
                    pendingTimes = data.pending;
                    $('#upcominginterview').text(data.upcomingInterviewSize.toString());
                    if (approvedTimes === 0 && rejectedTimes === 0 && pendingTimes === 0) {
                        var pieData = [
                            {value: 360, color: "#1a8cff", highlight: "#80bfff", label: "No Application Submited"}];
                    }
                    else {
                        var pieData = [
                            {value: approvedTimes, color: "#00b300", highlight: "#33ff33", label: "Approved"},
                            {value: rejectedTimes, color: "#ff0000", highlight: "#ff4d4d", label: "Rejected"},
                            {value: pendingTimes, color: "#ffcc00", highlight: "#ffdb4d", label: "Pending"}];
                    }
                    var ctx = document.getElementById("chart-area").getContext("2d");
                    window.myPie = new Chart(ctx).Pie(pieData);

                    var pendingApplicants = data.pendingApplicants;
                    var count = 0;
                    $.each(pendingApplicants, function (index, element) {
                        count++;
                        if (count === 8) {
                            return;
                        }
                        var titleParts = index.split('-');
                        var display = '<a href = "#" class = "list-group-item">';
                        display += '<span class = "badge"> ' + element + ' </span>';
                        display += ' <i class = "fa fa-fw fa-user"> </i>' + titleParts[0] + ' </a>';
                        $('#applicantbody').append(display);

                    });
                });
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
                        <li class="active">
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

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Dashboard <small>Statistics Overview</small>
                            </h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="fa fa-dashboard"></i> Dashboard
                                </li>
                            </ol>
                        </div>
                    </div>

                    <div id="success" class="alert alert-success">
                        <strong>Successful!</strong> You have successfully Updated your Profile!
                    </div>
                    <div id="noprofileerror" class="alert alert-danger">
                        <strong>Oh snap!</strong> You need to complete your Company Profile to complete this action!
                    </div>  
                    <s:hidden id="openaction" name="isProfileComplete" />

                    <div class="row">
                        <div class="col-lg-4 col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-3"
                                             <i class="fa fa-comments fa-5x"></i>
                                        </div>
                                        <div class="col-xs-9 text-right">
                                            <div class="huge" id="upcominginterview"></div>
                                            <div>Upcoming Closing Dates!</div>
                                        </div>
                                    </div>
                                </div>
                                <a href="OpenCompanyInterviews">
                                    <div class="panel-footer">
                                        <span class="pull-left">Check Calendar</span>
                                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Interviews</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="canvas-holder">
                                        <canvas id="chart-area" width="300" height="300"/>
                                    </div>
                                    <div class="text-right">
                                        <a href="OpenCompanyInterviews">View all <i class="fa fa-arrow-circle-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>  
                        <div class="col-lg-4 col-md-6">

                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Recent Applicants</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="applicantbody" class="list-group" style="min-height:437px;">

                                    </div>
                                    <div class="text-right">
                                        <a href="#">View All<i class="fa fa-arrow-circle-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div> 

                        <div class="col-lg-4 col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-body">
                                    <div class="info" style="min-height:510px;">
                                        <img id="pic" src="external/image/googleplay.jpg" class="img-responsive img-thumbnail" width="350px" height="10px">
                                        <p class="col-lg-offset-0"><p class="text-primary">"Job Hunt" Job Search gives you all the tools you need to easily post your company job – or let seekers find you</p>
                                        <div class="text-center">
                                            <p class="text-danger text-justify"> And do it all in total privacy.</p>
                                            Put the job-marketing power of smart world to work with the jobhunt Job Search App:<br><br></div>
                                        <ul class="form-group-sm text-success">
                                            <li class="text-success">Quickly and simply post jobs..</li>
                                            <li class="text-success">Easily find applied job seekers..</li>
                                            <li class="text-success">Update vacancies simply..</li>
                                            <li class="text-success">Approve application in one touch..</li>
                                            <li class="text-success"> Notifications about upcoming vacancies..</li>
                                        </ul>
                                        <div class="text-center">
                                            <p class="alert-info">Total privacy indeed – your network won’t hear a thing about your employment activity</p>
                                            <p class="text">present your company's career opportunity with the<br><a class="text-danger"> job hunt</a> Job Search App..</p>
                                            <a href="https://play.google.com/intl/ALL_uk/about/features/index.html" class="text-primary" target="_blank">Download Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 

                    </div>
                    <!-- /.row -->

                    <!-- /.row -->


                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/plugins/morris/raphael.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris-data.js"></script>

    </body>

</html>
