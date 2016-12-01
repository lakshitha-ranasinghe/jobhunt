<%-- 
    Document   : educationqualification
    Created on : Nov 24, 2015, 12:29:03 PM
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
        <link href="${pageContext.request.contextPath}/external/css/sb-admin.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/font.js"></script>
        <script>
            $(document).ready(function () {
                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                var OLResult = new Array('<s:property value='olResults'/>');
                var ALResult = new Array('<s:property value='alResults'/>');

                var OL = OLResult.toString().replace("[", "").replace("]", "").split(",");
                var AL = ALResult.toString().replace("[", "").replace("]", "").split(",");
                var count = 0;

                $(".al").each(function () {
                    var result = $.trim(AL[count]);
                    $(this).val(result);
                    count++;
                });
                count = 0;
                $(".ol").each(function () {
                    var result = $.trim(OL[count]);
                    $(this).val(result);
                    count++;
                });

                $("#submitbutton").click(function () {
                    var emptyYears = $('input[id="uniyear"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyCourse = $('input[id="unicourse"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyName = $('input[id="uniname"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyGPA = $('input[id="unigpa"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyAlYear = $('input[id="alYear"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyAlSchool = $('input[id="alSchool"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyAlSubject = $('input[id="alSubject"]').filter(function () {
                        return this.value === "";
                    });
                    var emptyAlResult = $('select[id="alsubjectmark"]').filter(function () {
                        return this.value === "";
                    });
                    emptyYears.val("0");
                    emptyCourse.val("Course not Specified");
                    emptyName.val("Name not Specified");
                    emptyGPA.val("0");
                    emptyAlYear.val("0");
                    emptyAlSchool.val("Not Specified");
                    emptyAlSubject.val("Not Specified");
                    emptyAlResult.val("X");
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
                                <li class="active">
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

                    <form role="form" action="ManageEducationalProfile" data-toggle="validator">
                        <!-- Page Heading -->
                        <div class="row">
                            <div class="col-lg-12">
                                <h1 class="page-header">
                                    Education Qualification
                                </h1>
                                <div class="row">
                                    <div class="col-xs-10">
                                        <ol class="breadcrumb">
                                            <li>
                                                <i class="fa fa-dashboard"></i>  <a href="index.html"> Dashboard</a>
                                            </li>
                                            <li>
                                                <i class="fa fa-meh-o"></i> Profile
                                            </li>
                                            <li class="active">
                                                <i class="fa fa-edit"></i> Educational
                                            </li>
                                        </ol>
                                    </div>                        
                                    <div class="col-xs-2">
                                        <button type="submit" id="submitbutton" class="btn btn-primary">Submit</button>
                                        <button type="reset" class="btn btn-danger">Reset</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.row -->

                        <br>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Ordinary Level</h3>
                                    </div>
                                    <div class="panel-body">

                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Examination Year</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">                                          
                                                    <input name="jobseekerEducation.olYear" class="textbox-inline form-control" placeholder="Exam Year" value="<s:property value='jobseekerEducation.olYear'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>								
                                        </div>

                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>School</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">                                          
                                                    <input name="jobseekerEducation.olSchool" class="textbox-inline form-control" placeholder="School Name" value="<s:property value='jobseekerEducation.olSchool'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>									
                                        </div>
                                        <br>
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Subject</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Grade</label>  
                                                </div>											
                                            </div>									
                                        </div>

                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input name="jobseekerOl[0].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerOl[0].subject'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="olsubjectmark" name="jobseekerOl[0].mark" class="form-control ol" required="true">
                                                    <option selected="true" value="">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select><div class="help-block with-errors"></div>
                                            </div>
                                        </div>  
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input name="jobseekerOl[1].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerOl[1].subject'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="olsubjectmark" required="true" name="jobseekerOl[1].mark" class="form-control ol">
                                                    <option selected="true" value="">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select><div class="help-block with-errors"></div>
                                            </div>
                                        </div>   
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input name="jobseekerOl[2].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerOl[2].subject'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="olsubjectmark" name="jobseekerOl[2].mark" class="form-control ol" value="<s:property value='jobseekerOl[2].mark'/>" required="true">
                                                    <option selected="true" value="">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select><div class="help-block with-errors"></div>
                                            </div>
                                        </div>   
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input name="jobseekerOl[3].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerOl[3].subject'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="olsubjectmark" name="jobseekerOl[3].mark" class="form-control ol" required="true">
                                                    <option selected="true" value="">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select><div class="help-block with-errors"></div>
                                            </div>
                                        </div>   
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input name="jobseekerOl[4].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerOl[4].subject'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="olsubjectmark" name="jobseekerOl[4].mark" class="form-control ol" required="true">
                                                    <option selected="true" value="">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select><div class="help-block with-errors"></div>
                                            </div>
                                        </div>   
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input name="jobseekerOl[5].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerOl[5].subject'/>" required="true">
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="olsubjectmark" name="jobseekerOl[5].mark" class="form-control ol" required="true">
                                                    <option selected="true" value="">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select><div class="help-block with-errors"></div>
                                            </div>
                                        </div>   
                                    </div>

                                    <!-- /.row -->
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Advanced Level</h3>
                                    </div>
                                    <div class="panel-body">


                                        <div class = "row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Examination Year</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">                                          
                                                    <input id="alYear" name="jobseekerEducation.alYear" class="textbox-inline form-control" placeholder="Exam Year" value="<s:property value='jobseekerEducation.alYear'/>">
                                                </div>
                                            </div>								
                                        </div>

                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>School</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">                                          
                                                    <input id="alSchool" name="jobseekerEducation.alSchool" class="textbox-inline form-control" placeholder="School Name" value="<s:property value='jobseekerEducation.alSchool'/>">
                                                </div>
                                            </div>									
                                        </div>
                                        <br>
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Subject</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label>Grade</label>  
                                                </div>											
                                            </div>									
                                        </div>

                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input id="alSubject" name="jobseekerAl[0].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerAl[0].subject'/>">
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="alsubjectmark" name="jobseekerAl[0].mark" class="form-control al">
                                                    <option hidden="true" selected="true">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select>
                                            </div>
                                        </div>  
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input id="alSubject" name="jobseekerAl[1].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerAl[1].subject'/>">
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="alsubjectmark" name="jobseekerAl[1].mark" class="form-control al">
                                                    <option hidden="true" selected="true">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input id="alSubject" name="jobseekerAl[2].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerAl[2].subject'/>">
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="alsubjectmark" name="jobseekerAl[2].mark" class="form-control al" value="B">
                                                    <option hidden="true" selected="true">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select>
                                            </div>
                                        </div> 
                                        <div class = "row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <input id="alSubject" name="jobseekerAl[3].subject" type="text" class="textbox-inline form-control" value="<s:property value='jobseekerAl[3].subject'/>">
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <select id="alsubjectmark" name="jobseekerAl[3].mark" class="form-control al">
                                                    <option hidden="true" selected="true">X</option>
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>S</option>
                                                    <option>F</option>
                                                </select>
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
                                        <h3 class="panel-title">Higher Education</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class = "row">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>University/Institute</label>  
                                                </div>											
                                            </div>							
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Course</label>  
                                                </div>											
                                            </div>	
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Year</label>  
                                                </div>											
                                            </div>	
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>GPA</label>  
                                                </div>											
                                            </div>										
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="uniname" name="jobseekerUniversity[0].name" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[0].name'/>" hidden="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="unicourse" name="jobseekerUniversity[0].course" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[0].course'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="uniyear" name="jobseekerUniversity[0].completedYear" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[0].completedYear'/>">
                                                </div>
                                            </div>		<div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="unigpa" name="jobseekerUniversity[0].gpa" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[0].gpa'/>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="uniname" name="jobseekerUniversity[1].name" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[1].name'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="unicourse" name="jobseekerUniversity[1].course" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[1].course'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="uniyear" name="jobseekerUniversity[1].completedYear" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[1].completedYear'/>">
                                                </div>
                                            </div>		
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="unigpa" name="jobseekerUniversity[1].gpa" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[1].gpa'/>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="uniname" name="jobseekerUniversity[2].name" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[2].name'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="unicourse" name="jobseekerUniversity[2].course" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[2].course'/>">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="uniyear" name="jobseekerUniversity[2].completedYear" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[2].completedYear'/>">
                                                </div>
                                            </div>		
                                            <div class="col-md-3">
                                                <div class="form-group">                                          
                                                    <input id="unigpa" name="jobseekerUniversity[2].gpa" class="textbox-inline form-control" value="<s:property value='jobseekerUniversity[2].gpa'/>">
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

            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <!--        <script src="js/jquery.js"></script>-->

        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js"></script>

        <!-- Bootstrap Validator JavaScript -->
        <script src="${pageContext.request.contextPath}/external/js/validator.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/validator.js"></script>
    </body>

</html>
