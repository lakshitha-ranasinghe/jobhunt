<%-- 
    Document   : companydetails
    Created on : Dec 4, 2015, 12:22:03 AM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JOB HUNT</title>

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/external/css/sb-admin.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/modaltest.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/css/Lobibox.min.css"/>
        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script>
            $(document).ready(function () {

                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                $('#approveddiv a').click(function () {
                    var selectedItem = $(this).find("p").find('span').text();
                    var parameter = {selectedVacancyApplyId: selectedItem};
                    $('#mbody').empty();
                    $.getJSON('JobSeekerInterviewVacancyData', parameter, function (data) {
                        var vacancy = data.vacancy;
                        var vacancyApply = data.vacancyApply;
                        var interviewDate = JSON.stringify(vacancyApply.interviewDate).replace('"', "").split('T');
                        var display = '<div id="page-wrapper"><div class="container-fluid">';
                        display += '<div class = "row"><div class="col-lg-12"><div class="form-group"><lable>Congratulations, You have been Selected for an Interview \n\
                                    at <b>' + vacancy.company.name + ' Company</b> for <b>' + vacancy.title + '</b> position. Your Interview is on <b>' + interviewDate[0] + '</b>. Please Contact ' + vacancy.company.name + '\n\
                                    for more details. <br><br></label></div></div>';
                        display+= '<div class="row"><div class="col-lg-4 col-lg-offset-1"><b>Telephone</b></div><div class="col-lg-4">0'+ vacancy.company.telephone + '</div></div><br>';
                        display+= '<div class="row"><div class="col-lg-4 col-lg-offset-1"><b>Mobile</b></div><div class="col-lg-4"> 0' + vacancy.company.mobile + '</div></div><br>';
                        display+= '<div class="row"><div class="col-lg-4 col-lg-offset-1"><b>E-mail</b></div><div class="col-lg-4"> ' + vacancy.company.email + '</div></div><br>';
                        display+= '<div class="row"><div class="col-lg-4 col-lg-offset-1"><b>Web Site</b></div><div class="col-lg-4">' + vacancy.company.website + '</div></div>';

                        $('#mbody').append(display);
                    });
                    $("#interviewModal").modal();
                });

            });

        </script>
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
                                <li class="active">
                                    <a href="OpenProfessionalProfile"> Professional</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="OpenRecommendation"><i class="fa fa-fw fa-table"></i> Recommendations</a>
                        </li>
                        <li  class="active">
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

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Interviews
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-table"></i> Interviews
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->      
                    <!-- /.row -->
                    <hr>
                    <br>
                    <div class="row">

                        <div class="col-sm-4">
                            <label>Approved Interviews</label>
                            <br><br>
                            <s:if test="%{getApprovedVacancies().isEmpty()}">
                                <div class="list-group">
                                    <a href="#" class="list-group-item list-group-item-success active">
                                        <h4 class="list-group-item-heading">No Approved Requests</h4>
                                        <p class="list-group-item-text">You currently don't have any approved interviews. All your requests are either pending or rejected</p>
                                    </a>
                                </div>
                            </s:if>
                            <s:else>
                                <div id="approveddiv">
                                    <s:iterator value="approvedVacancies" var="var">
                                        <div class="list-group">
                                            <a href="#" class="list-group-item list-group-item-success active">
                                                <h4 class="list-group-item-heading"><s:property value="#var.title"/></h4>
                                                <p class="list-group-item-text">Your Interview Request for <b><s:property value="#var.title"/></b> position at <b><s:property value="#var.company.name"/></b> company
                                                    is Approved. Your Application ID is <b><span><s:property value="#var.id"/>-<s:property value="seekerId"/></span></b>.
                                                </p>
                                            </a>
                                        </div>
                                    </s:iterator>
                                </div>
                            </s:else>
                        </div>
                        <div class="col-sm-4">
                            <label>Pending Interviews</label>
                            <br><br>
                            <div class="list-group">
                                <s:if test="%{getPendingVacancies().isEmpty()}">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item list-group-item-warning active">
                                            <h4 class="list-group-item-heading ">No Pending Requests</h4>
                                            <p class="list-group-item-text">You currently don't have any pending interviews. All your requests are either approved or rejected</p>
                                        </a>
                                    </div>
                                </s:if>
                                <s:else>
                                    <div id="pendingdiv">
                                        <s:iterator value="pendingVacancies" var="var" status="st">
                                            <div class="list-group">
                                                <a href="#" class="list-group-item active list-group-item-warning">        
                                                    <h4 class="list-group-item-heading"><s:property value="#var.title"/></h4>
                                                    <p class="list-group-item-text">Your Interview Request for <b><s:property value="#var.title"/></b> position at <b><s:property value="#var.company.name"/></b> company
                                                        is still pending. Your Application ID is <b><span><s:property value="#var.id"/>-<s:property value="seekerId"/></span></b>.
                                                    </p>
                                                </a>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </s:else>
                            </div>
                        </div>


                        <div class="col-sm-4">
                            <label>Rejected Interviews</label>
                            <br><br>
                            <div class="list-group">
                                <s:if test="%{getRejectedVacancies().isEmpty()}">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item list-group-item-danger active">
                                            <h4 class="list-group-item-heading">No Rejected Requests</h4>
                                            <p class="list-group-item-text">You currently don't have any rejected interviews. All your requests are either pending or approved</p>
                                        </a>
                                    </div>
                                </s:if>
                                <s:else>
                                    <s:iterator value="rejectedVacancies" var="var">
                                        <div class="list-group">
                                            <a href="#" class="list-group-item list-group-item-danger active">
                                                <h4 class="list-group-item-heading"><s:property value="#var.title"/></h4>
                                                <p class="list-group-item-text">Your Interview Request for <b><s:property value="#var.title"/></b> position at <b><s:property value="#var.company.name"/></b> company
                                                    has been rejected</p>
                                            </a>
                                        </div>
                                    </s:iterator>
                                </s:else>
                            </div>
                        </div>

                        <div class="modal fade" id="interviewModal" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header" id="mheader">
                                        <h4>Interview</h4>
                                    </div>
                                    <div class="modal-body" id="mbody">

                                    </div>
                                    <div class="modal-footer" id="mfooter">
                                        <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
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

    </body>
</html>
