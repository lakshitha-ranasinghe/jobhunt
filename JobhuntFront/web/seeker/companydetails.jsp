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

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>

        <link href="${pageContext.request.contextPath}/external/css/modaltest.css" rel="stylesheet">
        <script>
            $(document).ready(function () {
                $("#myModal").modal('hide');

                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                $('#searchbutton').click(function () {
                    var search = $('#search').val();
                    getCompanies(search);
                });
                $('#clearbutton').click(function () {
                    getCompanies(".");
                    $('#search').val("");
                });
                $(document).on("click", "#rectable tr", function (e) {
                    var companyName = $(this).closest("tr").find("#name").text();
                    var parameter = {name: companyName};
                    $('#cname').attr('value', companyName);
                    $("#mbody").empty();
                    $.getJSON('SearchCompany', parameter, function (data) {
                        var company = data.companyProfile;
                        var display = '<div id="page-wrapper"><div class="container-fluid">';
                        display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Company Name  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.name + '</label></div></div></div>';
                        display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Type  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.type + '</label></div></div></div>';
                        display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Job Type  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.jobType + '</label></div></div></div>';
                        display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Telephone </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>0' + company.telephone + '</label></div></div></div>';
                        display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>E-Mail  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.email + '</label></div></div></div>';
                        display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Web Site </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + company.website + '</label></div></div></div>';

                        $('#mbody').append(display);
                        $("#myModal").modal();
                    });
                });

                function getCompanies(search) {
                    var parameter = {searchString: search};
                    $.getJSON('SearchCompanies', parameter, function (data) {
                        $("#rectable tbody tr").remove();
                        $.each(data.filteredCompanies, function (index, element) {
                            var row = '<tr style="cursor: pointer;"><td id="name">' + element.name + '</td>';
                            row += '<td>' + element.type + '</td>';
                            row += '<td>' + element.jobType + '</td>';
                            row += '<td>' + element.address1 + ', ' + element.address2 + ', ' + element.address3 + '</td>';
                            row += '<td>' + element.email + '</td>';
                            row += '<td>0' + element.telephone + '</td>';
                            row += '<td>0' + element.mobile + '</td>';
                            row += '<td>' + element.website + '</td>';
                            row += '</tr>';
                            $('#rectable').append(row);
                        });
                    });
                }

                $('#print').click(function () {
                    $("#myModal").modal('toggle');
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
                        <li>
                            <a href="OpenJobseekerInterviews"><i class="fa fa-fw fa-desktop"></i> Interviews</a>
                        </li>
                        <li class="active">
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
                                Company Details
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-table"></i> Company Details
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->      
                    <!-- /.row -->
                    <hr>
                    <br>
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" id="">
                                    <h4>Company Information</h4>
                                </div>
                                <div class="modal-body" id="mbody">

                                </div>
                                <div class="modal-footer">
                                    <form action="GenerateCompanyDetails">
                                        <s:hidden id="cname" name="companyName"/>
                                        <button type="submit" class="btn btn-primary btn-default" id="print">Print</button>
                                        <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>     
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div id="selectrow" class="row">
                        <div class="column">
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <input id="search" placeholder="Name, Addres, Anything" class="textbox-inline form-control"> 
                                </div>
                            </div>
                        </div>
                        <div class="column">
                            <div class="col-lg-2">                              
                                <button id="searchbutton" class="btn btn-primary">Search</button>
                                <button id="clearbutton" class="btn btn-warning">Clear&#160</button>
                            </div>
                        </div>

                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="table-responsive">
                                <table id="rectable" class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>Company Name</th>
                                            <th>Company Type</th>
                                            <th>Job Type</th>
                                            <th>Address</th>
                                            <th>E-Mail Address</th>
                                            <th>Telephone</th>
                                            <th>Mobile</th>           
                                            <th>Web Site</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="companies" var="var">
                                            <tr style="cursor: pointer;">
                                                <td id="name">
                                                    <s:property value="#var.name"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.type"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.jobType"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.address1"/>,
                                                    <s:property value="#var.address2"/>,
                                                    <s:property value="#var.address3"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.email"/>
                                                </td>
                                                <td>
                                                    0<s:property value="#var.telephone"/>
                                                </td>
                                                <td>
                                                    0<s:property value="#var.mobile"/>
                                                </td>
                                                <td>
                                                    <s:property value="#var.website"/>
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
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>       
    </body>
</html>
