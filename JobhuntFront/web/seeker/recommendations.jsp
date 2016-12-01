<%-- 
    Document   : recommendations
    Created on : Nov 30, 2015, 12:34:50 AM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Job Hunt</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/external/css/sb-admin.css" rel="stylesheet">


    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/external/css/theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/external/css/modaltest.css" rel="stylesheet">
    <script>
        $(document).ready(function () {
            $("#myModal").modal('hide');
            hideSuccess();
            hideError();
            $('#filtermethod').prop('checked', true);
            $("#area").prop("disabled", true);
            $("#job").prop("disabled", true);
            $("#salary").prop("disabled", true);

            $.getJSON('GetTopNavData', {}, function (data) {
                $('#user').text(" " + data.userName);
            });

            function getAutomaticData() {
                $.getJSON('AutomaticRecommendation', {}, function (data) {
                    $("#rectable tbody tr").remove();
                    var applied = new Array(data.appliedVacancyIds);
                    var appliedVacancies = applied.toString().replace("[", "").replace("]", "").split(",");

                    $.each(data.filteredVacancies, function (index, element) {
                        var applicationFound = 0;
                        var row = '<tr style="cursor: pointer;">';
                        $.each(appliedVacancies, function (index, e) {
                            if (e === (element.id).toString()) {
                                row += '<td id="status"><span class="label label-success">Applied</span></td>';
                                applicationFound = 1;
                                return false;
                            }
                        });
                        if (applicationFound !== 1) {
                            row += '<td id="status"><span class="label label-info">&#160&#160Apply&#160&#160</span></td>';
                        }
                        row += '<td style="display:none;" id="vacancyId">' + element.id + '</td>';
                        row += '<td>' + element.title + '</td>';
                        row += '<td>' + element.branch + '</td>';
                        var closingDate = JSON.stringify(element.closingDate).replace('"', "").split('T');
                        row += '<td>' + closingDate[0] + '</td>';
                        var salary;
                        if ((JSON.stringify(element.salary).indexOf(".")) === -1) {
                            salary = element.salary + ".00";
                        }
                        else {
                            salary = element.salary;
                        }
                        row += '<td>' + salary + '</td>';
                        row += '<td>' + element.description + '</td>';
                        row += '<td>' + element.company.name + '</td>';

                        row += '</tr>';
                        $('#rectable').append(row);
                    });
                });
            }

            function refreshTable() {
                if ($('#filtermethod').is(":checked")) {
                    getAutomaticData();
                }
                else {
                    getManualData();
                }
            }

            function showSuccess() {
                $('#success').show();
                $('#success').delay(5000).fadeOut('slow');
            }
            function hideSuccess() {
                $('#success').hide();
            }
            function showError() {
                $('#error').show();
                $('#error').delay(5000).fadeOut('slow');
            }
            function hideError() {
                $('#error').hide();
            }

            var vacancyToRequest;

            getAutomaticData();

            $(document).on("click", "#rectable tr", function (e) {
                var vacancyId = $(this).closest("tr").find("#vacancyId").text();
                var applyStatus = $(this).closest("tr").find("#status").text();
                vacancyToRequest = vacancyId;
                var parameter = {selectedVacancyId: vacancyId};
                $.getJSON('VacancyDataForRecommendation', parameter, function (data) {
                    var vacancy = data.vacancy;
                    var company = vacancy.company;
                    var display = '<div id="page-wrapper"><div class="container-fluid">';
                    display += '<div class = "row"><div class="col-lg-12"><div class="form-group"><lable><b>Required Qualifiations</b> </label></div></div>\n\
                                <div class="col-lg-12"><div class="form-group"><lable>' + vacancy.prerequisites + '</label></div></div></div><br>';
                    display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Company Name  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.name + '</label></div></div></div>';
                    display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Telephone </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>0' + company.telephone + '</label></div></div></div>';
                    display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>E-Mail  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.email + '</label></div></div></div>';
                    display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Web Site </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.website + '</label></div></div></div>';

                    $('#mbody').html(display);

                });
                if(applyStatus === 'Applied'){
                   $("#apply").prop('disabled', true);
                   $("#apply").text('Applied');
                }
                else{
                    $("#apply").prop('disabled', false);
                    $("#apply").text('Apply');
                }
                $("#myModal").modal();
            });

            $('#apply').click(function () {
                var parameter = {vacancyId: vacancyToRequest};
                $.getJSON('ApplyJob', parameter, function (data) {
                    var isApplied = data.isApplied;
                    if (isApplied.toString() === 'true') {
                        showSuccess();
                        refreshTable();
                    }
                    else {
                        showError();
                        refreshTable();
                    }
                    $("#myModal").modal('toggle');
                });
            });

            function getManualData() {
                var area = $('#area').val();
                var job = $('#job').val();
                var salary = $('#salary').val();
                var parameter = {preferredJob: job, preferredArea: area, expectedSalary: salary};
                $.getJSON('Recommendation', parameter, function (data) {
                    var applied = new Array(data.appliedVacancyIds);
                    var appliedVacancies = applied.toString().replace("[", "").replace("]", "").split(",");

                    $("#rectable tbody tr").remove();
                    $.each(data.vacancies, function (index, element) {
                        var applicationFound = 0;
                        var row = '<tr style="cursor: pointer;">';
                        $.each(appliedVacancies, function (index, e) {
                            if (e === (element.id).toString()) {
                                row += '<td id="status"><span class="label label-success">Applied</span></td>';
                                applicationFound = 1;
                                return false;
                            }
                        });
                        if (applicationFound !== 1) {
                            row += '<td id="status"><span class="label label-info">&#160&#160Apply&#160&#160</span></td>';
                        }
                        row += '<td style="display:none;" id="vacancyId">' + element.id + '</td>';
                        row += '<td>' + element.title + '</td>';
                        row += '<td>' + element.branch + '</td>';
                        var closingDate = JSON.stringify(element.closingDate).replace('"', "").split('T');
                        row += '<td>' + closingDate[0] + '</td>';
                        var salary;
                        if ((JSON.stringify(element.salary).indexOf(".")) === -1) {
                            salary = element.salary + ".00";
                        }
                        else {
                            salary = element.salary;
                        }
                        row += '<td>' + salary + '</td>';
                        row += '<td>' + element.description + '</td>';
                        row += '<td>' + element.company.name + '</td>';

                        row += '</tr>';
                        $('#rectable').append(row);
//                        $('td:nth-child(1)').hide();
                    });
                });
            }
            $('#selectrow select').change(function () {
                getManualData();
            });
            $('#filtermethod').change(function () {
                if ($(this).is(":checked")) {
                    getAutomaticData();
                    $("#area").prop("disabled", true);
                    $("#job").prop("disabled", true);
                    $("#salary").prop("disabled", true);
                }
                else {
                    getManualData();
                    $("#area").prop("disabled", false);
                    $("#job").prop("disabled", false);
                    $("#salary").prop("disabled", false);
                }
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
                            <li>
                                <a href="OpenProfessionalProfile"> Professional</a>
                            </li>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="OpenRecommendation"><i class="fa fa-fw fa-table"></i> Recommendations</a>
                    </li>
                    <li>
                        <a href="OpenJobseekerInterviews"><i class="fa fa-fw fa-desktop"></i> Interviews</a>
                    </li>
                    <li>
                        <a href="ViewCompanies"><i class="fa fa-fw fa-edit"></i> Companies </a>
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
                            Recommendations
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Recommendations
                            </li>
                        </ol>
                    </div>
                </div>
                <hr>
                <!-- /.row -->      
                <!-- /.row -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header" id="">
                                <h4>Apply Now</h4>
                            </div>
                            <div class="modal-body" id="mbody">

                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary btn-default" id="apply">Apply</button>
                                <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div> 
                <div id="success" class="alert alert-success">
                    <strong>Successful</strong> You successfully applied for the Job
                </div>
                <div id="error" class="alert alert-danger">
                    <strong>Oh snap!</strong> Change a few things up and try submitting again.
                </div>
                <div id="selectrow" class="row">
                    <div class="column">
                        <div class="col-lg-2">
                            <div class="form-group">
                                <label>Job Type</label>
                                <select name="preferredJob" id="job" class="form-control">
                                    <option selected="true">Not Specified</option>
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
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="col-lg-2">
                            <div class="form-group">
                                <label>Expected Salary</label>
                                <select id="salary" name="expectedSalary" class="form-control">
                                    <option selected="true">Not Specified</option>
                                    <option>10000 - 30000</option>
                                    <option>30000 - 60000</option>
                                    <option>60000 - 100000</option>
                                    <option>100000 - 150000</option>
                                    <option>More than 150000</option>                 
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="col-lg-2">
                            <div class="form-group">
                                <label>Preferred Area</label>
                                <select id="area" name="preferredArea" class="form-control">
                                    <option selected="true">Not Specified</option>
                                    <option>Central Province</option>
                                    <option>Western Province</option>
                                    <option>Northern Province</option>
                                    <option>North Central Province</option>
                                    <option>Sabaragamu Province</option>
                                    <option>Uwa Province</option>
                                    <option>Southern Province</option>
                                    <option>Eastern Province</option>
                                    <option>Other</option>           
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="col-lg-2"></div>
                    </div>

                    <div class="column">
                        <div class="col-lg-2"></div>
                    </div>
                    <div class="column col-lg-2">
                        <label class="checkbox-inline">
                            <input id="filtermethod" type="checkbox">Automatic Data Filter
                        </label>
                    </div>
                </div>
                <br>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="table-responsive">
                            <table id="rectable" class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Status</th>
                                        <th>Job Title</th>
                                        <th>Location</th>
                                        <th>Closing Date</th>
                                        <th>Salary</th>
                                        <th>Description</th>
                                        <th>Company</th>     
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>

                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>