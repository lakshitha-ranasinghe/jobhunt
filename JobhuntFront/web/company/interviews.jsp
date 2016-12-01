<%-- 
    Document   : interviews
    Created on : Dec 6, 2015, 12:44:38 PM
    Author     : Lakshitha
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

        <!--Modal CSS-->
        <link href="${pageContext.request.contextPath}/external/css/modaltest.css" rel="stylesheet">

        <!--calendar CSS-->
        <link href="${pageContext.request.contextPath}/external/css/fullcalendar.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="${pageContext.request.contextPath}/external/css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>

        <script>
            $(document).ready(function () {
//                    $("#calendarModal").modal('hide');
                $.getJSON('CompanyNameForTopNav', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });
                function getInterview(today) {
                    var d = new Date(today);
                    $("#modalHeader").html('Sheduled interviews for ' + today.format());
                    var parameter = {year: d.getFullYear(), month: d.getMonth(), day: d.getDate()};
                    $.getJSON('GetInterviewDates', parameter, function (data) {
                        var allInterviews = JSON.stringify(data.interviewsToday);
                        if (allInterviews === '[]') {
                            return;
                        }
                        var appliedVacancies = allInterviews.replace("[", "").replace("]", "").replace('"', "").split(",");
                        $("#modalBody").empty();
                        $.each(appliedVacancies, function (index, element) {
                            var vacancyData = element.toString().replace('"', "").split("-");
                            var closingDateParts = vacancyData[2].toString().split(' ');
                            var display = '<div id="page-wrapper"><div class="container-fluid">';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Position  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + vacancyData[0] + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>No of Vacancies  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + vacancyData[1] + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Closing Date  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + closingDateParts[0] + ' ' + closingDateParts[1] + ' ' + closingDateParts[2] + '</label></div></div></div>';
                            display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Total Applicants </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + vacancyData[3].replace('"',"") + '</label></div></div></div><hr>';

                            $('#modalBody').append(display);

                        });
                        $("#calendarModal").modal();
                    });
                }
                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next',
                        center: 'title',
                        right: false
                    },
                    defaultDate: new Date(),
                    editable: true,
                    selectable: true,
                    eventLimit: true,
                    dayClick: function (date) {
                        getInterview(date);
                    },
                    eventClick: function (event) {
                        $("#calendarModal").modal();
                        $("#modalHeader").html('Sheduled interviews');
                        $("#vacancyTitle").html(event.title);
                        $("#applicants").html(event.description);
                    }
                });

                function refreshCalendar() {
                    $.getJSON('GetAllInterviewDates', {}, function (data) {
                        var daysMap = data.allInterviewDates;
                        var closingDaysMap = data.allClosingDates;
                        $.each(daysMap, function (index, value) {
                            var myEvent = {
                                title: index.split("-")[1],
                                allDay: true,
                                start: value,
                                end: value
//                                color:'#ff0000'
                            };
                            $('#calendar').fullCalendar('renderEvent', myEvent);
                        });
                        $.each(closingDaysMap, function (index, value) {
                            var myEvent = {
                                title: index.split("-")[1],
                                allDay: true,
                                start: value,
                                end: value,
                                color:'#ff0000'
                            };
                            $('#calendar').fullCalendar('renderEvent', myEvent);
                        });
                    });
                }

                $('body').on('click', 'button.fc-prev-button', function () {
                    refreshCalendar();
                });

                $('body').on('click', 'button.fc-next-button', function () {
                    refreshCalendar();
                });
                refreshCalendar();
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
                        <li  class="active">
                            <a href="OpenCompanyInterviews"><i class="fa fa-fw fa-calendar"></i> Upcoming Interviews</a>
                        </li>
                        <li>
                            <a href="OpenCompanyOptions"><i class="fa fa-fw fa-wrench"></i> Options</a>
                        </li>
                    </ul>
                </div>
            </nav>


            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Upcoming Interviews....!!!
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                </li>
                                <li class="active"> <i class="fa fa-table">  Interviews</i>
                                </li>
                            </ol>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">upcoming interviews</h3>
                                </div>
                                <div class="panel-body">
                                    <div id='calendar'></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--modal dialog-->
                <div class="modal fade" id="calendarModal" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header" id="modalHeader">
                                <h4>Interviews</h4>
                            </div>
                            <div class="modal-body" id="modalBody">

                            </div>
                            <div class="modal-footer" id="modalFooter">
                                <button type="reset" class="btn btn-primary btn-default" id="bclose" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>

            <!-- Bootstrap Core JavaScript -->
            <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>

            <!-- Bootstrap Validator JavaScript -->
            <script src="${pageContext.request.contextPath}/external/js/validator.min.js"></script>
            <script src="${pageContext.request.contextPath}/external/js/validator.js"></script>

            <!-- Morris Charts JavaScript -->
            <script src="${pageContext.request.contextPath}/external/js/plugins/morris/raphael.min.js"></script>
            <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris.min.js"></script>
            <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris-data.js"></script>

            <!-- Calendar JavaScript -->
            <script src="${pageContext.request.contextPath}/external/js/moment.min.js"></script>
            <!--<script src="${pageContext.request.contextPath}/external/js/jquery.min.js"></script>-->
            <script src="${pageContext.request.contextPath}/external/js/fullcalendar.min.js"></script>
    </body>

</html>
