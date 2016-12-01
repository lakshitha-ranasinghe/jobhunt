<%-- 
    Document   : profile
    Created on : Nov 25, 2015, 9:01:44 AM
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

        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/font.js"></script>
        <script type="text/javascript" language="javascript">
            $(document).ready(function () {
                $.getJSON('CompanyNameForTopNav', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });
                
                $('#companydescription').val("<s:property value='companyProfile.description'/>");
                $('#jobtype').val("<s:property value='companyProfile.jobType'/>");
                $('#companytype').val("<s:property value='companyProfile.type'/>");
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
                        <li class="active">
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
                    <form action="ManageCompanyProfile" role="form" data-toggle="validator">
                        <div class="row">
                            <div class="col-lg-12">
                                <h1 class="page-header">
                                    Tell us about your company..!
                                </h1>

                                <div class="row">
                                    <div class="col-xs-10">
                                        <ol class="breadcrumb">
                                            <li>
                                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                            </li>
                                            <li class="active"> <i class="fa fa-edit">Profile</i>
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
                                        <h3 class="panel-title">Company Details</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body">

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Company Name</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.name" value="<s:property value='companyProfile.name'/>" type="text" class="form-control" placeholder="Company Name" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Address</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.address1" value="<s:property value='companyProfile.address1'/>" type="text" class="form-control" placeholder="Address Line 1" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Company Type</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <select id="companytype" name="companyProfile.type" class="form-control" required="true">
                                                        <option selected="true" value="">Select</option>
                                                        <option>Private</option>
                                                        <option>Public</option>
                                                        <option>Subsidiaries</option>
                                                        <option>offshore Company</option>
                                                        <option>Joint Venture Company</option>
                                                        <option>Other</option>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label></label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.address2" value="<s:property value='companyProfile.address2'/>" type="text" placeholder="Address Line 2" class="form-control" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>E-Mail Address</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input type="email" name="companyProfile.email" value="<s:property value='companyProfile.email'/>" class="form-control" placeholder="Email"  required>
                                                    <div class="help-block with-errors" data-error="Invalid Email Address"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label></label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.address3" value="<s:property value='companyProfile.address3'/>" type="text" placeholder="Address Line 3" class="form-control" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Telephone</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.telephone" value="<s:property value='companyProfile.telephone'/>" type="text" class="form-control" minlength="10" maxlength="10" required>
                                                    <div class="help-block with-errors" data-error="Invalid phone number"></div>  
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Web Site</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.website" value="<s:property value='companyProfile.website'/>" type="text" placeholder="Web site Address" class="form-control" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Mobile</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input name="companyProfile.mobile" value="<s:property value='companyProfile.mobile'/>" type="text" class="form-control" minlength="10" maxlength="10" required>
                                                    <div class="help-block with-errors" data-error="Invalid phone number"></div>  
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
                                        <h3 class="panel-title">Preferences</h3>
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body">

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Job Types you offer</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-9">
                                                <div class="form-group">
                                                    <select id="jobtype" name="companyProfile.jobType" class="form-control" required="true">
                                                        <option selected="true" value="">Select a job type</option>
                                                        <option>Information Technology</option>
                                                        <option>Management</option>
                                                        <option>Engineering</option>
                                                        <option>Education</option>
                                                        <option>Sales and Marketing</option>
                                                        <option>Other</option>
                                                    </select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <label>Description</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-9">
                                                <div class="form-group">
                                                    <textarea id="companydescription" name="companyProfile.description" value="<s:property value='companyProfile.description'/>" class="form-control" rows="3" placeholder="Briefly Describe about job type you offer" required></textarea>
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
                        <br>
                    </form>
                </div>
                <!-- /#page-wrapper -->         
                <!-- /#page-wrapper -->
            </div>
        </div>
        <!-- /#wrapper -->


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
