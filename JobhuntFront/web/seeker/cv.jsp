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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/css/Lobibox.min.css"/>
        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script>
            $(document).ready(function () {
                $.getJSON('GetTopNavData', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });
                var experienceRemoved = 0;
                var higherEduRemoved = 0;
                $('#noal').click(function () {
                    $('#isALRemoved').attr('value', true);
                    $('#aldivheader').fadeOut('slow');
                    $('#aldiv').fadeOut('slow');
                });
                $('#nojob1').click(function () {
                    $('#isCompanyOneRemoved').attr('value', true);
                    experienceRemoved++;
                    $('#nojob1dd').fadeOut('slow');
                    if (experienceRemoved === 3) {
                        $('#totalexperience').fadeOut('slow');
                    }
                });
                $('#nojob2').click(function () {
                    $('#isCompanyTwoRemoved').attr('value', true);
                    experienceRemoved++;
                    $('#nojob2dd').fadeOut('slow');
                    if (experienceRemoved === 3) {
                        $('#totalexperience').fadeOut('slow');
                    }
                });
                $('#nojob3').click(function () {
                    $('#isCompanyThreeRemoved').attr('value', true);
                    experienceRemoved++;
                    $('#nojob3dd').fadeOut('slow');
                    if (experienceRemoved === 3) {
                        $('#totalexperience').fadeOut('slow');
                    }
                });
                $('#nohigheredu1').click(function () {
                    $('#isHigherEducationOneRemoved').attr('value', true);
                    $('#higheredu1').fadeOut('slow');
                    higherEduRemoved++;
                    if (higherEduRemoved === 3) {
                        $('#highereduheader').fadeOut('slow');
                    }
                });

                $('#nohigheredu2').click(function () {
                    $('#isHigherEducationTwoRemoved').attr('value', true);
                    $('#higheredu2').fadeOut('slow');
                    higherEduRemoved++;
                    if (higherEduRemoved === 3) {
                        $('#highereduheader').fadeOut('slow');
                    }
                });

                $('#nohigheredu3').click(function () {
                    $('#isHigherEducationThreeRemoved').attr('value', true);
                    $('#higheredu3').fadeOut('slow');
                    higherEduRemoved++;
                    if (higherEduRemoved === 3) {
                        $('#highereduheader').fadeOut('slow');
                    }
                });
            });
        </script>

        <style>
            * { margin: 0; padding: 0; }        
            .clear { clear: both; }
            #page-wrap { width: 800px; margin: 40px auto 60px; }
            #pic { float: right; margin: -30px 0 0 0; }
            h2 { font-size: 20px; margin: 0 0 6px 0; position: relative; }
            h2 span { float: left; bottom: 0; right: 0; font-style: italic; font-family: Georgia, Serif; font-size: 16px; color: #999; font-weight: normal; }
            #objective { margin-bottom:15px; margin-top:15px; width: 800px; float: left;font-family: Georgia, Serif; font-style: italic; color: #666; }
            dt { font-style: italic; font-weight: bold; font-size: 18px; text-align: right; padding: 0 26px 0 0; width: 150px; float: left; height: 100px; border-right: 1px solid #999;  }
            dd { width: 600px; float: right; }
            dd.clear { float: none; margin: 0; height: 15px; }            
            textarea{resize:none;}
        </style>
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
                        <li  class="active">
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
                                Generate your CV...!! 
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-table"></i> Generate CV
                                </li>
                            </ol>
                        </div>
                    </div>
                    <hr>
                    <br>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h4 class="panel-title"><center>Curriculum Vitae</center></h4>
                                </div>
                                <div class="panel-body">   
                                    <form action="GenerateCV">
                                        <div id="page-wrap">
                                            <center>
                                                <span class="text-left">
                                                    <img id="pic" src="external/image/applicant.jpg" class="img-responsive img-thumbnail" width="150px" height="150px">                   
                                                </span>
                                            </center>                                      

                                            <div id="contact-info" class="vcard">
                                                <h1 class="fn"><s:property value="nameWithInitials"/></h1>
                                                <hr width="75%">
                                                <div class="col-lg-12">
                                                    <div class="row"><div class="col-lg-4"><b> Name in full: </b></div> <div class="col-lg-offset-3"><s:property value="jobseeker.firstName"/> <s:property value="jobseeker.lastName"/></div></div><br>
                                                    <div class="row"><div class="col-lg-4"><b> Address: </b> </div><div class="col-lg-offset-3"><s:property value="jobseeker.address1"/>, <s:property value="jobseeker.address2"/>, </div><div class="col-lg-offset-4"><s:property value="jobseeker.address3"/></div></div><br>
                                                    <div class="row"><div class="col-lg-4"><b> Telephone: </b></div> <div class="col-lg-offset-3">0<s:property value="jobseeker.telephone"/></div><div class="col-lg-offset-4">0<s:property value="jobseeker.mobile"/></div></div><br>
                                                    <div class="row"><div class="col-lg-4"><b> Date of birth: </b></div> <div class="col-lg-offset-3"><s:property value="birthday"/></div></div><br>
                                                    <div class="row"><div class="col-lg-4"><b> Email: </b> </div> <div class="col-lg-offset-3"><a class="email" href="#"><s:property value="jobseeker.email"/></a></div></div><br>
                                                </div>
                                                <br><br>
                                            </div>

                                            <div id="objective">
                                                <textarea name="objective" class="form-control" name="" rows="3" placeholder="Objective"></textarea>
                                            </div>

                                            <div class="clear"></div>
                                            <dl>
                                                <dd class="clear"></dd>

                                                <div class="divider">
                                                    <dt>Education</dt>
                                                    <dd>                 
                                                        <h2>Ordinary Level</h2>
                                                        <div class="col-lg-12">
                                                            <h2><span> <label><s:property value="jobseekerEducation.olSchool"/></label> (<label><s:property value="jobseekerEducation.olYear"/></label>)</span></h2><br>
                                                            <div class="row"><div class="col-lg-3"><strong><s:property value="jobseekerOl[0].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerOl[0].mark"/></div>
                                                                <div class="col-lg-3"><strong><s:property value="jobseekerOl[1].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerOl[1].mark"/></div>
                                                            </div>
                                                            <div class="row"><div class="col-lg-3"><strong><s:property value="jobseekerOl[2].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerOl[2].mark"/></div>
                                                                <div class="col-lg-3"><strong><s:property value="jobseekerOl[3].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerOl[3].mark"/></div>                                                          
                                                            </div>
                                                            <div class="row"><div class="col-lg-3"><strong><s:property value="jobseekerOl[4].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerOl[4].mark"/></div>
                                                                <div class="col-lg-3"><strong><s:property value="jobseekerOl[5].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerOl[5].mark"/></div>
                                                            </div>
                                                            <hr width="50%">
                                                        </div>                                                                           

                                                        <div id="aldivheader" class="row"><div class="col-lg-6"><h2>Advanced Level</h2></div> <div class="col-lg-3"><button id="noal" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button></div></div>
                                                        <div class="col-lg-12" id="aldiv"> 
                                                            <h2><span> <label><s:property value="jobseekerEducation.alSchool"/></label> (<label><s:property value="jobseekerEducation.alYear"/></label>)</span></h2><br>
                                                            <div class="row"><div class="col-lg-3"><strong><s:property value="jobseekerAl[0].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerAl[0].mark"/></div>
                                                                <div class="col-lg-3"><strong><s:property value="jobseekerAl[1].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerAl[1].mark"/></div>
                                                            </div>
                                                            <div class="row"><div class="col-lg-3"><strong><s:property value="jobseekerAl[2].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerAl[2].mark"/></div>
                                                                <div class="col-lg-3"><strong><s:property value="jobseekerAl[3].subject"/></strong></div> <div class="col-lg-3"><s:property value="jobseekerAl[3].mark"/></div>                                                          
                                                            </div>
                                                            <hr width="50%">
                                                        </div>

                                                        <div id="highereduheader" class="row"><div class="col-lg-6"><h2>Higher Education</h2></div></div><br>
                                                        <div id="higheredu" class="col-lg-12" id="">
                                                            <div id="higheredu1" class="row" >
                                                                <div class="row">
                                                                    <div class="col-lg-6">
                                                                        <h2><span><label><s:property value="jobseekerUniversity[0].name"/></label> (<label><s:property value="jobseekerUniversity[0].completedYear"/></label>)</span></h2>
                                                                    </div>
                                                                    <div class="col-lg-3">
                                                                        <button id="nohigheredu1" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
                                                                    </div>
                                                                </div><br>
                                                                <div class="row">
                                                                    <div class="col-lg-1 col-lg-offset-1"><strong><s:property value="jobseekerUniversity[0].course"/></strong></div>
                                                                    <div class="col-lg-3 col-lg-offset-1"><s:property value="jobseekerUniversity[0].gpa"/></div>
                                                                </div><br>   
                                                            </div>
                                                            <div id="higheredu2" class="row" >
                                                                <div class="row">
                                                                    <div class="col-lg-6">
                                                                        <h2><span><label><s:property value="jobseekerUniversity[1].name"/></label> (<label><s:property value="jobseekerUniversity[1].completedYear"/></label>)</span></h2>
                                                                    </div>
                                                                    <div class="col-lg-3">
                                                                        <button id="nohigheredu2" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
                                                                    </div>
                                                                </div><br>
                                                                <div class="row">
                                                                    <div class="col-lg-1 col-lg-offset-1"><strong><s:property value="jobseekerUniversity[1].course"/></strong></div>
                                                                    <div class="col-lg-3 col-lg-offset-1"><s:property value="jobseekerUniversity[1].gpa"/></div>
                                                                </div><br>   
                                                            </div>
                                                            <div id="higheredu3" class="row" >
                                                                <div class="row">
                                                                    <div class="col-lg-6">
                                                                        <h2><span><label><s:property value="jobseekerUniversity[2].name"/></label> (<label><s:property value="jobseekerUniversity[2].completedYear"/></label>)</span></h2>
                                                                    </div>
                                                                    <div class="col-lg-3">
                                                                        <button id="nohigheredu3" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
                                                                    </div>
                                                                </div><br>
                                                                <div class="row">
                                                                    <div class="col-lg-1 col-lg-offset-1"><strong><s:property value="jobseekerUniversity[2].course"/></strong></div>
                                                                    <div class="col-lg-3 col-lg-offset-1"><s:property value="jobseekerUniversity[2].gpa"/></div>
                                                                </div><br>   
                                                            </div>
                                                            <br>
                                                        </div>       
                                                    </dd>
                                                    <hr width="100%">
                                                </div>

                                                <dd class="clear"></dd>

                                                <div id="totalexperience" class="divider">
                                                    <dt>Experience</dt>
                                                    <dd id="nojob1dd">
                                                        <div class="row"><div class="col-lg-6"><h2><s:property value="jobseekerWorkedCompanies[0].designation"/></h2></div> <div class="col-lg-3"><button id="nojob1" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button></div></div><br>
                                                        <div class="col-lg-12" id="">
                                                            <div class="row"><div class="col-lg-3"><strong>Company Name</strong></div> <div class="col-lg-3"><s:property value="jobseekerWorkedCompanies[0].name"/></div></div>
                                                            <div class="row"><div class="col-lg-3"><strong>Duration</strong></div> <div class="col-lg-3"><s:property value="jobseekerWorkedCompanies[0].startDate"/> to <s:property value="jobseekerWorkedCompanies[0].endDate"/></div></div>
                                                            <hr width="50%">
                                                        </div>
                                                    </dd>
                                                    <dd id="nojob2dd">
                                                        <div class="row"><div class="col-lg-6"><h2><s:property value="jobseekerWorkedCompanies[1].designation"/></h2></div> <div class="col-lg-3"><button id="nojob2" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button></div></div><br>
                                                        <div class="col-lg-12" id="">
                                                            <div class="row"><div class="col-lg-3"><strong>Company Name</strong></div> <div class="col-lg-3"><s:property value="jobseekerWorkedCompanies[1].name"/></div></div>
                                                            <div class="row"><div class="col-lg-3"><strong>Duration</strong></div> <div class="col-lg-3"><s:property value="jobseekerWorkedCompanies[1].startDate"/> to <s:property value="jobseekerWorkedCompanies[1].endDate"/></div></div>
                                                            <hr width="50%">
                                                        </div>                              
                                                    </dd>
                                                    <dd id="nojob3dd">
                                                        <div class="row"><div class="col-lg-6"><h2><s:property value="jobseekerWorkedCompanies[2].designation"/></h2></div> <div class="col-lg-3"><button id="nojob3" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-minus"></span></button></div></div><br>
                                                        <div class="col-lg-12" id="">
                                                            <div class="row"><div class="col-lg-3"><strong>Company Name</strong></div> <div class="col-lg-3"><s:property value="jobseekerWorkedCompanies[2].name"/></div></div>
                                                            <div class="row"><div class="col-lg-3"><strong>Duration</strong></div> <div class="col-lg-3"><s:property value="jobseekerWorkedCompanies[2].startDate"/> to <s:property value="jobseekerWorkedCompanies[2].endDate"/></div></div>       
                                                            <br>
                                                        </div> 
                                                    </dd>
                                                    <hr width="100%">
                                                </div>

                                                <dd class="clear"></dd>                                            

                                                <dd class="clear"></dd>

                                                <div class="divider">
                                                    <dt>Other Qualification</dt>
                                                    <dd>
                                                        <div class="col-lg-12">
                                                            <div class="row"><textarea id="other" class="form-control" name="" rows="3" placeholder="Other Qualification"></textarea></div>
                                                        </div>
                                                    </dd>
                                                </div>

                                                <dd class="clear"></dd>

                                                <div class="divider">
                                                    <dt>References</dt>
                                                    <dd> 
                                                        <div class="col-lg-12">
                                                            <div class="col-lg-5"><div class="row"><textarea id="reference1" class="form-control" name="firstReference" rows="4" placeholder="Reference"></textarea></div></div>
                                                            <div class="col-lg-5 col-lg-offset-2"><div class="row"><textarea id="reference2" class="form-control" name="secondReference" rows="4" placeholder="Reference"></textarea></div></div>
                                                        </div>
                                                    </dd>
                                                </div>

                                            </dl>


                                            <dd class="clear"></dd>
                                            <div class="clear"></div>

                                        </div>
                                        <s:hidden name="isCompanyOneRemoved"/>
                                        <s:hidden name="isCompanyTwoRemoved"/>
                                        <s:hidden name="isCompanyThreeRemoved"/>
                                        <s:hidden name="isALRemoved"/>
                                        <s:hidden name="isHigherEducationOneRemoved"/>
                                        <s:hidden name="isHigherEducationTwoRemoved"/>
                                        <s:hidden name="isHigherEducationThreeRemoved"/>
                                        <s:hidden name="isThisForJobseeker" value="true"/>
                                        <button type="submit" class="btn btn-primary">Print</button>
                                    </form>
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
        <script src="${pageContext.request.contextPath}/external/js/jquery.1.11.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/Lobibox.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/demo.js"></script>
    </body>
</html>
