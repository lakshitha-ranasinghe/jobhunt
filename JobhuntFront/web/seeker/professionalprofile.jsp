<%-- 
    Document   : professionalprofile
    Created on : Nov 24, 2015, 4:28:20 PM
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
        <link href="${pageContext.request.contextPath}/external/css/sb-admin.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/font.js"></script>
        <script>
            $(document).ready(function () {
                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                $("#submitbutton").click(function () {
                    var emptyExperienceYears = $('input[id="expYears"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyCompany = $('input[id="company"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyDesignation = $('input[id="designation"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyOtherYear = $('input[id="otherYear"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyOtherTitle = $('input[id="otherTitle"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyOtherDetails = $('input[id="otherDetails"]').filter(function () {
                        return this.value === "";
                    });
                    emptyExperienceYears.val("0");
                    emptyCompany.val("Company not Specified");
                    emptyDesignation.val("Designation not Specified");
                    emptyOtherDetails.val("Not Specified");
                    emptyOtherYear.val("0");
                    emptyOtherTitle.val("Not Specified");
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
            <form role="form" action="ManageProfessionalProfile">
                <div id="page-wrapper">

                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="row">
                            <div class="col-lg-12">
                                <h1 class="page-header">
                                    Professional Qualification
                                </h1>
                                <div class="row">
                                    <div class="col-xs-10">
                                        <ol class="breadcrumb">
                                            <li>
                                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                            </li>
                                            <li>
                                                <i class="fa fa-edit"></i> <a href="#">Profile</a>
                                            </li>
                                            <li class="active">
                                                <i class="fa fa-edit"></i> <a href="professionalprofile.html">Professional</a>
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
                        <br>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Work Experience <small>( Relevent to the post applying )</small></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class = "row">


                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Total number of years of Experience as of now <small> (In tearms of years)</small> </label>
                                                </div>
                                            </div>
                                            <div class="col-lg-1">
                                                <div class="form-group">				
                                                    <input id="expYears" name="experience.totalYears" type="text" class="form-control" value="<s:property value='experience.totalYears'/>">
                                                </div>
                                            </div>
                                        </div>	
                                        <hr>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Designation</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">				
                                                    <input id="designation" name="workedCompanies[0].designation" type="text" class="form-control" value="<s:property value='workedCompanies[0].designation'/>">
                                                </div>
                                            </div>
                                        </div>						
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Company</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">				
                                                    <input id="company" name="workedCompanies[0].name" type="text" class="form-control" value="<s:property value='workedCompanies[0].name'/>">
                                                </div>
                                            </div>
                                        </div>	
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Duration</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group col-lg-1">
                                                    <label>From</label>
                                                </div>
                                                <div class="form-group col-lg-4">				
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" cssStyle="width:10em;" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="workedCompanies[0].startDate" value="workedCompanies[0].startDate"/>
                                                    <!--                                                    <input name="datecheck" data-format="yyyy-MM-dd" type="date" class="textbox-inline form-control">
                                                                                                        <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>-->
                                                </div>


                                                <div class="form-group col-lg-1">
                                                    <label>To</label>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="workedCompanies[0].endDate" value="workedCompanies[0].endDate" />
                                                    <!--                                                    <input name="datecheck" data-format="yyyy-MM-dd" type="date" class="textbox-inline form-control">
                                                                                                        <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>-->
                                                </div>
                                            </div>
                                        </div>	
                                        <hr>	
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Designation</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">				
                                                    <input id="designation" name="workedCompanies[1].designation" type="text" class="form-control" value="<s:property value='workedCompanies[1].designation'/>">
                                                </div>
                                            </div>
                                        </div>						
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Company</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">				
                                                    <input id="company" name="workedCompanies[1].name" type="text" class="form-control" value="<s:property value='workedCompanies[1].name'/>">
                                                </div>
                                            </div>
                                        </div>	
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Duration</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group col-lg-1">
                                                    <label>From</label>
                                                </div>
                                                <div class="form-group col-lg-4">	
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="workedCompanies[1].startDate" value="workedCompanies[1].startDate"/>
                                                    <!--                                                    <input name="previousJobStartDates" data-format="yyyy-MM-dd" type="date" class="textbox-inline form-control">
                                                                                                        <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>-->
                                                </div>


                                                <div class="form-group col-lg-1">
                                                    <label>To</label>
                                                </div>
                                                <div class="form-group col-lg-4">			
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="workedCompanies[1].endDate" value="workedCompanies[1].endDate" />
                                                    <!--                                                    <input name="previousJobEndDates" data-format="yyyy-MM-dd" type="date" class="textbox-inline form-control">
                                                                                                        <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>-->
                                                </div>
                                            </div>
                                        </div>	
                                        <hr>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Designation</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">				
                                                    <input id="designation" name="workedCompanies[2].designation" type="text" class="form-control" value="<s:property value='workedCompanies[2].designation'/>">
                                                </div>
                                            </div>
                                        </div>						
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Company</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">				
                                                    <input id="company" name="workedCompanies[2].name" type="text" class="form-control" value="<s:property value='workedCompanies[2].name'/>">
                                                </div>
                                            </div>
                                        </div>	
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Duration</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group col-lg-1">
                                                    <label>From</label>
                                                </div>
                                                <div class="form-group col-lg-4">		
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="workedCompanies[2].startDate" value="workedCompanies[2].startDate"/>
                                                    <!--                                                    <input name="previousJobStartDates" data-format="yyyy-MM-dd" type="date" class="textbox-inline form-control">
                                                                                                        <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>-->
                                                </div>


                                                <div class="form-group col-lg-1">
                                                    <label>To</label>
                                                </div>
                                                <div class="form-group col-lg-4">		
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="add-on" name="workedCompanies[2].endDate" value="workedCompanies[2].endDate"/>
                                                    <!--                                                    <input name="previousJobEndDates" data-format="yyyy-MM-dd" type="date" class="textbox-inline form-control">
                                                                                                        <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>-->
                                                </div>
                                            </div>
                                        </div>	


                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Other<small>    Projects,Publications,Researches</small></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class = "row">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Title</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Year</label>  
                                                </div>											
                                            </div>	
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Details</label>  
                                                </div>											
                                            </div>										
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="otherTitle" name="otherQualifications[0].title" class="textbox-inline form-control" value="<s:property value='otherQualifications[0].title'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="otherYear" name="otherQualifications[0].publishedYear" class="textbox-inline form-control" value="<s:property value='otherQualifications[0].publishedYear'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">                                          
                                                    <input id="otherDetails" name="otherQualifications[0].details" class="textbox-inline form-control" value="<s:property value='otherQualifications[0].details'/>">
                                                </div>
                                            </div>		
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="otherTitle" name="otherQualifications[1].title" class="textbox-inline form-control" value="<s:property value='otherQualifications[1].title'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="otherYear" name="otherQualifications[1].publishedYear" class="textbox-inline form-control" value="<s:property value='otherQualifications[1].publishedYear'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">                                          
                                                    <input id="otherDetails" name="otherQualifications[1].details" class="textbox-inline form-control" value="<s:property value='otherQualifications[1].details'/>">
                                                </div>
                                            </div>		
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="otherTitle" name="otherQualifications[2].title" class="textbox-inline form-control" value="<s:property value='otherQualifications[2].title'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="otherYear" name="otherQualifications[2].publishedYear" class="textbox-inline form-control" value="<s:property value='otherQualifications[2].publishedYear'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">                                          
                                                    <input id="otherDetails" name="otherQualifications[2].details" class="textbox-inline form-control" value="<s:property value='otherQualifications[2].details'/>">
                                                </div>
                                            </div>		
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <br><br>


                    </div>

                </div>
            </form>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->

        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>

    </body>

</html>
