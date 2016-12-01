<%-- 
    Document   : viewvacancy
    Created on : Dec 3, 2015, 10:57:04 PM
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
        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link href="${pageContext.request.contextPath}/external/css/theme.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/modaltest.css" rel="stylesheet">

        <script>
            $(document).ready(function () {
                hideSuccess();
                hideError();
                $('#closingdateerror').hide();
                $('#successupdate').hide();
                $('#errorupdate').hide();
                var vacancyId;
                var currentRow;

                $.getJSON('CompanyNameForTopNav', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                $("#updatedialog").hide();

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

                $('#update').click(function () {
                    var newDate = $('#updateddate').val();
                    if (newDate === '') {
                        $('#closingdateerror').fadeIn();
                    }
                    else {
                        var parameter = {vacancyId: vacancyId, closingDate: newDate.toString()};
                        $("#updateModal").modal('toggle');
                        $.getJSON('UpdateClosingDate', parameter, function (data) {
                            var isSuccessful = data.isUpdateSuccessful;
                            if (isSuccessful.toString() === 'true') {
                                $('#successupdate').show();
                                $('#successupdate').delay(5000).fadeOut('slow');
                                location.reload();
                            }
                            else {
                                $('#errorupdate').show();
                                $('#errorupdate').delay(5000).fadeOut('slow');
                            }
                        });
                    }
                });

                $('#updateddate').change(function () {
                    var interviewDate = $('#updateddate').val();
                    if (interviewDate === '') {
                        $('#closingdateerror').fadeIn();
                    }
                    else {
                        $('#closingdateerror').fadeOut('slow');
                    }
                });

                $('#vacancybody').on('click', '#updatebutton', function () {
                    $('#updateModal').modal();
                    currentRow = $(this).closest("tr");
                    vacancyId = currentRow.find("#vacancyid").text();

                });

                $('#vacancybody').on('click', '#deletebutton', function () {
                    $('#deleteModal').modal();
                    currentRow = $(this).closest("tr");
                    vacancyId = currentRow.find("#vacancyid").text();
                });

                $('#delete').click(function () {
                    var parameter = {selectedVacancy: vacancyId};
                    $.getJSON('DeleteVacancy', parameter, function (data) {
                        if (data.isDeleted === "true") {
                            currentRow.remove();
                            showSuccess();
                            $("#myModal").modal('toggle');
                        }
                        else {
                            showError();
                            $("#myModal").modal('toggle');
                        }
                    });
                });

            });</script>
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
                        <li class="active">
                            <a href="OpenUpdateVacancy"><i class="fa fa-fw fa-book"></i> View Vacancies</a>
                        </li>
                        <li>
                            <a href="OpenCompanyInterviews"><i class="fa fa-fw fa-calendar"></i>Upcoming Interviews</a>
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
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                View Vacancy....!!!
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                </li>
                                <li class="active"> <i class="fa fa-edit">view Vacancy</i>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="modal fade" id="updateModal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" id="">
                                    <h4>Update</h4>
                                </div>
                                <div class="modal-body" id="mbody">
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-1">
                                                <label>New Closing Date</label>
                                            </div>
                                            <div class="col-lg-4">
                                                <input type="date" class="textbox-inline form-control" id="updateddate" name="updateddate">
                                                <label class="label label-danger" id="closingdateerror">Select an Closing Date</label>
                                                <%--<sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="textbox-inlie form-control" cssStyle="width:25em;" />--%>
                                            </div>
                                        </div><br>
<!--                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-1">
                                                <label>No of Vacancies</label>
                                            </div>
                                            <div class="col-lg-4">
                                                <input type="test" class="textbox-inline form-control" id="updatednoofvacancies" name="updatednoofvacancies">               
                                            </div>
                                        </div><br>
                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-1">
                                                <label>Updated Salary</label>
                                            </div>
                                            <div class="col-lg-4">
                                                <input type="text" class="textbox-inline form-control" id="updateddalary" name="updatedsalary">                                          
                                            </div>
                                        </div><br>-->
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary btn-default" id="update">Update</button>
                                    <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div class="modal fade" id="deleteModal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" id="">
                                    <h4>Do you want to delete the vacancy?</h4>
                                </div>
                                <div class="modal-body" id="mbody">
                                    <!--                                <div class="form-group">
                                                                        <div class="form-group">
                                                                            
                                                                        </div>
                                                                    </div>-->
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary btn-danger" id="delete">Delete</button>
                                    <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div> 

                    <div id="updatedialog" class='form-group' title="Apply Now!!">
                        <div id="message">

                        </div>
                    </div>
                    <div class="row">
                        <div id="success" class="alert alert-success">
                            <strong>Success!</strong> You have successfully deleted the Vacancy
                        </div>
                        <div id="error" class="alert alert-danger">
                            <strong>Oh snap!</strong> Change a few things up and try again.
                        </div>
                        <div id="successupdate" class="alert alert-success">
                            <strong>Success!</strong> You have successfully updated the Vacancy
                        </div>
                        <div id="errorupdate" class="alert alert-danger">
                            <strong>Oh snap!</strong> Change a few things up and try again.
                        </div>
                        <div class="col-lg-2">
                            <div class="form-group">
                                <label>Sort By</label>
                                <select name="preferredJob" id="job" class="form-control">
                                    <option selected="true">Not Specified</option>

                                </select>
                            </div>
                        </div>
                    </div>

                    <br>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="table-responsive" id="table">
                                <table id="rectable" class="table table-hover table-striped" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Job Title</th>
                                            <th>Location</th>
                                            <th>Description</th>
                                            <th>Recruiters</th>
                                            <th>Salary</th>
                                            <th>Closing Date</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="vacancybody">
                                        <s:iterator value="vacancies" var="var">
                                            <tr>
                                                <td style="display:none;" id="vacancyid">
                                                    <s:property value="#var.id"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.title"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.branch"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.description"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.vacancyCount"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.salary"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.closingDate"/>
                                                </td>
                                                <td>
                                                    <button id="updatebutton" class="btn btn-sm btn-warning">Update</button>
                                                </td>
                                                <td>
                                                    <button id="deletebutton" class="btn btn-sm btn-danger">Delete</button>
                                                </td> 
                                                <td>
                                                    <form action="ViewApplicants">  
                                                        <s:hidden name="vacancyId" value="%{#var.id}"/>
                                                        <button type="submit" id="manageapplicationbutton" class="btn btn-sm btn-success">Applications</button>
                                                    </form>
                                                </td>  
                                            </tr>
                                        </s:iterator> 
                                    </tbody>
                                </table>
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


        <!-- Bootstrap Validator JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/validator.min.js"></script>
        <script src="js/validator.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/plugins/morris/raphael.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/plugins/morris/morris-data.js"></script>

    </body>

</html>
