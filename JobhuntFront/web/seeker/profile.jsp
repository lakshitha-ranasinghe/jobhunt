<%-- 
    Document   : profile
    Created on : Nov 23, 2015, 11:29:21 AM
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

        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/font.js"></script>
        
        <script type="text/javascript" language="javascript">
            $(document).ready(function () {

                $('#seekertitle').val("<s:property value='jobseeker.title'/>");
                $('#lastjob').val("<s:property value='jobseeker.lastJob'/>");
                $('#qualifiedfield').val("<s:property value='jobseeker.qualifiedField'/>");
                $('#expectedjob').val("<s:property value='jobseeker.expectedJob'/>");

                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
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
                        <li >
                            <a href="OpenSeekerDashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Profile<i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="demo" class="collapse">
                                <li class="active">
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
                    <form action="ManageProfile" role="form" data-toggle="validator">
                        <!-- Page Heading -->
                        <div class="row">
                            <div class="col-lg-12">
                                <h1 class="page-header">
                                    Tell us about yourself!
                                </h1>
                                <div class="row">
                                    <div class="col-xs-10">
                                        <ol class="breadcrumb">
                                            <li>
                                                <i class="fa fa-dashboard"></i>  <a href="index.html"> Dashboard</a>
                                            </li>
                                            <li>
                                                <i class="fa fa-database"></i> Profile
                                            </li>
                                            <li class="active">
                                                <i class="fa fa-edit"></i> Personal
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
                                        <h3 class="panel-title">Personal Details</h3>
                                    </div>
                                    <div class="panel-body">

                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>First Name</label>
                                                    <input name="jobseeker.firstName" class="textbox-inline form-control" id="fName" placeholder="Enter First name" value="<s:property value='jobseeker.firstName'/>" required="true">   
                                                    <div class="help-block with-errors"></div>
                                                </div>

                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Last Name</label>
                                                    <input name="jobseeker.lastName" class="textbox-inline form-control" placeholder="Enter Second Name" value="<s:property value='jobseeker.lastName'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>									
                                        </div>


                                        <div class="row">				
                                            <div class="col-lg-6">						
                                                <div class="form-group">
                                                    <label>Title</label>
                                                    <select id="seekertitle" name="jobseeker.title" class="form-control" value="<s:property value='jobseeker.title'/>" required="true">
                                                        <option selected="true" value="">Title</option>
                                                        <option>Mr</option>
                                                        <option>Ms</option>
                                                        <option>Mrs</option>
                                                        <option>Dr</option>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">	
                                                <div class="form-group">
                                                    <label>Date of Birth</label>
                                                    <sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="textbox-inlie form-control" cssStyle="width:56em;" name="jobseeker.birthday" value="jobseeker.birthday"/>
                                                </div>
                                            </div>  
                                        </div>

                                        <div class="row">

                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Telephone</label>
                                                    <input name="jobseeker.telephone" class="textbox-inline form-control" placeholder="Enter Telephone" value="<s:property value='jobseeker.telephone'/>" minlength="10" maxlength="10" required="true">
                                                    <div class="help-block with-errors" data-error="Invalid phone number"></div>
                                                </div>
                                            </div>

                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Mobile</label>
                                                    <input name="jobseeker.mobile" class="textbox-inline form-control" placeholder="Enter Mobile" value="<s:property value='jobseeker.mobile'/>" minlength="10" maxlength="10" required="true">
                                                    <div class="help-block with-errors" data-error="Invalid phone number"></div>
                                                </div>
                                            </div>	
                                        </div>

                                        <div class="row">

                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Address Line 1</label>
                                                    <input name="jobseeker.address1" class="textbox-inline form-control" placeholder="Enter Address" value="<s:property value='jobseeker.address1'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>	
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Address Line 2</label>
                                                    <input name="jobseeker.address2" class="textbox-inline form-control" placeholder="Enter Address" value="<s:property value='jobseeker.address2'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>

                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Address Line 3</label>
                                                    <input name="jobseeker.address3" class="textbox-inline form-control" placeholder="Enter Address" value="<s:property value='jobseeker.address3'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>E-Mail Address</label>
                                                    <input name="jobseeker.email" type="email" class="textbox-inline form-control" placeholder="Enter email" value="<s:property value='jobseeker.email'/>" required="true">
                                                    <div class="help-block with-errors"  data-error="Invalid Email Address"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>                              
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Preferences</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class = "row">                           
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Field you are Qualified in</label>
                                                    <select id="qualifiedfield" name="jobseeker.qualifiedField" class="form-control" value="<s:property value='jobseeker.qualifiedField'/>" required="true">
                                                        <option selected="true" value="">Qualified Field</option>
                                                        <option>IT</option>
                                                        <option>Management</option>
                                                        <option>Engineering</option>
                                                        <option>Academic</option>
                                                        <option>Sales and Marketing</option>
                                                        <option>Other</option>
                                                        <option>Health</option>                                                       
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>

                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>What kind of job you are looking for</label>
                                                    <select id="expectedjob" name="jobseeker.expectedJob" class="form-control" value="<s:property value='jobseeker.expectedJob'/>" required="true">
                                                        <option selected="true" value="">Select a job type</option>
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
                                                        <ComboBoxItem Content="Select a Job field" IsSelected="True" Foreground="DarkGray"/>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class = "row">                           
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Last Job(if any)</label>
                                                    <select id="lastjob" name="jobseeker.lastJob" class="form-control" value="<s:property value='jobseeker.lastJob'/>" required="true">
                                                        <option selected="true" value="">Last Job </option>
                                                        <option>Intern</option>
                                                        <option>Freelance</option>
                                                        <option>Temporary</option>
                                                        <option>Other</option>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </form>
                </div>

            </div>

            <!-- Bootstrap Core JavaScript -->
            <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>

            <!-- Bootstrap Validator JavaScript -->
            <script src="${pageContext.request.contextPath}/external/js/validator.min.js"></script>
            <script src="${pageContext.request.contextPath}/external/js/validator.js"></script>
    </body>

</html>
