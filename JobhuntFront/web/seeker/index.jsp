<%-- 
    Document   : index
    Created on : Nov 23, 2015, 11:26:09 AM
    Author     : Muthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <script>
            history.pushState(null, null, 'index.jsp');
            window.addEventListener('popstate', function (event) {
                history.pushState(null, null, 'index.jsp');
            });
        </script>

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

        <script>
            $(document).ready(function () {
                hideNoPersonalError();
                hideSuccess();
                hideCommonError();
                $('#passwordchangeerror').hide();
                $('#errorindexheader').hide(); //error page header

                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });
                var actionOpen = $('#actionopen').val();
                if (actionOpen.toString() === 'false') {
//                    hideIndexContent();     //hide index body Content
                    showNoPersonalError();
                }

                var educationUpdateSuccess = $('#educationUpdateSuccess').val();
                if (educationUpdateSuccess.toString() === 'true') {
                    showSuccess();
                }

                var professionalUpdateSuccess = $('#professionalUpdateSuccess').val();
                if (professionalUpdateSuccess.toString() === 'true') {
                    showSuccess();
                }

                var personalUpdateSuccess = $('#personalUpdateSuccess').val();
                if (personalUpdateSuccess.toString() === 'true') {
                    showSuccess();
                }

                var isPasswordChangeError = $('#isPasswordChangeError').val();
                if (isPasswordChangeError.toString() === 'false') {
                    showSuccess();
                }
                else if (isPasswordChangeError.toString() === 'true') {
                    $('#passwordchangeerror').show();
                    $('#passwordchangeerror').delay(5000).fadeOut('slow');
                }

                function showSuccess() {
                    $('#success').show();
                    $('#success').delay(5000).fadeOut('slow');
                }
                function hideSuccess() {
                    $('#success').hide();
                }
                function showNoPersonalError() {
                    $('#nopersonalerror').show();
                    $('#nopersonalerror').delay(5000).fadeOut('slow');
                }
                function hideNoPersonalError() {
                    $('#nopersonalerror').hide();
                }
                function hideCommonError() {
                    $('#commonerror').hide();
                }
                function showCommonError() {
                    $('#commonerror').show();
                    $('#commonerror').delay(5000).fadeOut('slow');
                }

                /* Error Page Hide Function */
                function hideIndexContent() {
                    $('#indexcontent').hide();
                    $('#indexheader').hide();
                    $('#errorindexheader').show();
                    var display = '<div id="page-wrapper"><div class="container-fluid"><div class = "row"><div class="col-lg-12"><form action="" role="form"><button type="submit" class="btn btn-primary">Back to dashboard</button></form></div></div></div></div>';
                    $('#errorbutton').append(display);
                }


                $.getJSON('VacancyInformationForJobseeker', {}, function (data) {
                    var vacancies = data.vacancyWithDate;
                    var newVacancies = data.newVacancySize;
                    $('#newvacancies').text(newVacancies.toString());
                    var count = 0;
                    $.each(vacancies, function (index, element) {
                        count++;
                        if(count===8){
                            return;
                        }
                        var titleParts = index.split('-');
                        var display = '<a href = "#" class = "list-group-item">';
                        display += '<span class = "badge"> '+ element +' </span>';
                        display += ' <i class = "fa fa-fw fa-user"> </i> New '+ titleParts[0] +' Vacancy</a>';
                        $('#vacancylistbody').append(display);   
                        
                    });
                });

                var approvedTimes;
                var rejectedTimes;
                var pendingTimes;
                $.getJSON('GetApprovesAndRejected', {}, function (data) {
                    approvedTimes = data.approved;
                    rejectedTimes = data.rejected;
                    pendingTimes = data.pending;
                    if (approvedTimes === 0 && rejectedTimes === 0 && pendingTimes === 0) {
                        var pieData = [
                            {value: 360, color: "#1a8cff", highlight: "#80bfff", label: "No Application Submited"}]
                    }
                    else {
                        var pieData = [
                            {value: approvedTimes, color: "#00b300", highlight: "#33ff33", label: "Approved"},
                            {value: rejectedTimes, color: "#ff0000", highlight: "#ff4d4d", label: "Rejected"},
                            {value: pendingTimes, color: "#ffcc00", highlight: "#ffdb4d", label: "Pending"}];
                    }
                    var ctx = document.getElementById("chart-area").getContext("2d");
                    window.myPie = new Chart(ctx).Pie(pieData);
                });
            });</script>
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
                                <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
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
                        <li class="active">
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
                        <li>
                            <a href="OpenCV"><i class="fa fa-fw fa-desktop"></i> Generate CV</a>
                        </li>
                        <li>
                            <a href="OpenJobseekerOptions"><i class="fa fa-fw fa-wrench"></i> Options</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <div class="wrap" id="indexheader">
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
                    </div>

                    <div class="wrap" id="errorindexheader">
                        <!--Error Page Heading -->
                        <div class="row">
                            <div class="col-lg-12"><h1 class="page-header">JOB HUNT</h1>
                                <ol class="breadcrumb"><li class="active"><i class="fa fa-globe"></i>  Job Hunt</li></ol></div>
                        </div>
                    </div>

                    <!-- /.row -->
                    <div id="success" class="alert alert-success">
                        <strong>Successful!</strong> You have successfully Updated your Profile!
                    </div>
                    <div id="nopersonalerror" class="alert alert-danger">
                        <strong>Oh snap!</strong> You need to complete your Personal Profile to complete this action!
                    </div>       
                    <div id="commonerror" class="alert alert-danger">
                        <strong>Oh snap!</strong> You need to complete your Personal,Educational,Professional Profiles to complete this action!
                    </div>  
                    <div id="passwordchangeerror" class="alert alert-danger">
                        <strong>Oh snap!</strong> Your current password does not seem to be valid!
                    </div>   
                    <s:hidden id="educationopen" name="isPersonalExist" />
                    <s:hidden id="professionalopen" name="isPersonalExist" />
                    <s:hidden id="actionopen" name="isCompleteJobseeker"/>

                    <s:hidden id="educationUpdateSuccess" name="isUpdateSuccess"/>
                    <s:hidden id="professionalUpdateSuccess" name="isUpdateSuccess"/>
                    <s:hidden id="personalUpdateSuccess" name="isUpdateSuccess"/>

                    <s:hidden id="isPasswordChangeError" name="isPasswordChangeError"/>

                    <div class="well-sm col-lg-offset-10" id="errorbutton"></div>

                    <div class="wrap" id="indexcontent">
                        <div class="row">
                            <div class="col-lg-4 col-md-6">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3"
                                                 <i class="fa fa-comments fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div id="newvacancies" class="huge"> </div>
                                                <div>New Vacancies!</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="OpenRecommendation">
                                        <div class="panel-footer">
                                            <span class="pull-left">View Recommendations</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Approvals and Rejects</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div id="canvas-holder">
                                            <canvas id="chart-area" width="300" height="300"/>
                                        </div>
                                        <div class="text-right">
                                            <a href="OpenJobseekerInterviews">View Details <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                            <div class="col-lg-4 col-md-6">

                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Vacancy Panel</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div id="vacancylistbody" class="list-group" style="min-height:437px;">
                                        </div>
                                        <div class="text-right">
                                            <a href="OpenRecommendation">View All Vacancies <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-lg-4 col-md-6">
                                <div class="panel panel-primary">
                                    <div class="panel-body">
                                        <div class="info" style="min-height:510px;">
                                            <img id="pic" src="external/image/googleplay.jpg" class="img-responsive img-thumbnail" width="350px" height="10px">
                                            <p class="col-lg-offset-0"><p class="text-primary">"Job Hunt" Job Search gives you all the tools you need to easily find your dream job – or let it find you</p>
                                            <div class="text-center">
                                                <p class="text-danger text-justify"> And do it all in total privacy.</p>
                                                Put the job-finding power of smart world to work with the jobhunt Job Search App:<br><br></div>
                                            <ul class="form-group-sm text-success">
                                                <li class="text-success">Quickly and simply search jobs..</li>
                                                <li class="text-success">Filter your job searches..</li>
                                                <li class="text-success">Further refine your job search results..</li>
                                                <li class="text-success"> Notifications new jobs..</li>
                                            </ul>
                                            <div class="text-center">
                                                <p class="alert-info">Total privacy indeed – your network won’t hear a thing about your employment-seeking activity</p>
                                                <p class="text">Snag your next career opportunity with the<br><a class="text-danger"> job hunt</a> Job Search App to get hired today</p>
                                                <a href="https://play.google.com/intl/ALL_uk/about/features/index.html" class="text-primary" target="_blank">Download Now</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->



        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>



    </body>

</html>
