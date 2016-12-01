<%-- 
    Document   : profile
    Created on : Nov 25, 2015, 9:01:44 AM
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
        <sx:head/>
        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/external/css/css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="${pageContext.request.contextPath}/external/css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/external/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>

        <link href="${pageContext.request.contextPath}/external/css/modaltest.css" rel="stylesheet">

        <script>
            $(document).ready(function () {
                hideSuccessApprove();
                hideSuccessReject();
                hideError();
                rejectlabelHide();
                approvelabelHide();

                $('#interviewerrorlabel').hide();
                $('#dateerrorlabel').hide();
                
                $('#interviewdate').change(function () {
                    var interviewDate = $('#interviewdate').val();
                    var now = new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate();
                    if(interviewDate===''){
                        $('#interviewerrorlabel').fadeIn();
                    }
                    else if(interviewDate <= now){
                        $('#interviewerrorlabel').fadeOut('slow');
                        $('#dateerrorlabel').fadeIn();
                    }
                    else{
                        $('#dateerrorlabel').fadeOut('slow');
                        $('#interviewerrorlabel').fadeOut('slow');
                    }
                });

                $.getJSON('CompanyNameForTopNav', {}, function (data) {
                    $('#user').text(" " + data.userName);
                });

                function showSuccessApprove() {
                    $('#successapprove').show();
                    $('#successapprove').delay(5000).fadeOut('slow');
                }
                function showSuccessReject() {
                    $('#successreject').show();
                    $('#successreject').delay(5000).fadeOut('slow');
                }

                function hideSuccessApprove() {
                    $('#successapprove').hide();
                }
                function hideSuccessReject() {
                    $('#successreject').hide();
                }

                function showError() {
                    $('#error').show();
                    $('#error').delay(5000).fadeOut('slow');
                }
                function hideError() {
                    $('#error').hide();
                }

                function rejectlabelHide() {
                    $('.rejectlabelout').hide();
                }

                function approvelabelHide() {
                    $('.approvelabelout').hide();
                }

                var applicantId;
                var vacancyId;
                var clickedButton;
                var interviewday;
                $('.btn').on('click', function () {
                    var reject = '#' + applicantId + 'rejout';
                    var approve = '#' + applicantId + 'appout';
                    var pending = '#' + applicantId + 'pendinglabel';

                    var buttonClicked = this.id;
                    if (buttonClicked.toString() === 'approvemodalbutton') {
                        ApproveReject('approve', vacancyId, applicantId, interviewday);
                        clickedButton.prop('disabled', true);
                        clickedButton.parents('.row').find('#rejectbutton').prop('disabled', true);
                        clickedButton.parents('.row').find(pending).fadeOut('slow');
                        clickedButton.parents('.row').find(approve).show();
                        $('#approvemodal').modal('toggle');
                    }
                    else if (buttonClicked.toString() === 'rejectmodalbutton') {
                        ApproveReject('reject', vacancyId, applicantId);
                        clickedButton.prop('disabled', true);
                        clickedButton.parents('.row').find('#approvebutton').prop('disabled', true);
                        clickedButton.parents('.row').find(pending).fadeOut('slow');
                        clickedButton.parents('.row').find(reject).show();
                        $('#rejectmodal').modal('toggle');
                    }

                    applicantId = $(this).closest('div').find('input[type="hidden"]').val();
                    vacancyId = $('#vacancyId').text();

                    if (buttonClicked.toString() === 'approvebutton') {
                        interviewday = $("#approvebutton").parents('.row').prev(".row").find("#interviewdate").val();
                        if (interviewday.toString() === ''){
                            $('#interviewerrorlabel').fadeIn();
                        }
                        else {
                            $('#approvemodal').modal();
                            clickedButton = $(this);
                        }
                    }
                    else if (buttonClicked.toString() === 'rejectbutton') {
                        $('#rejectmodal').modal();
                        clickedButton = $(this);
                    }
                    else if (buttonClicked.toString() === 'cvbtn') {

                        $('#sname').attr('value', applicantId);
                        var parameter = {applicantId: applicantId};

                        $.getJSON('GetJobseekerProfile', parameter, function (data) {
                            $("#vacancybody").empty();
                            $('#vacancyheader').empty();
                            var olResultSummery = data.olResultSummery;
                            var alResultSummery = data.alResultSummery;
                            var uniResultSummery = data.uniResultSummery;
                            var professionalSummery = data.professionalSummery;

                            var seeker = data.jobseeker;

                            var birthday = JSON.stringify(seeker.birthday).replace('"', "").split('T');
                            $('#vacancyheader').text(seeker.firstName + " " + seeker.lastName);
                            var display = '<div id="page-wrapper"><div class="container-fluid">';
                            display += '<div class = "row"><div class = "col-lg-12"><h3> Personal Information</h3><hr></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Name in Full  </b></label></div></div>\n\
                    <div class="col-lg-8"><div class="form-group"><lable>' + seeker.firstName + " " + seeker.lastName + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Date of Birth  </b></label></div></div>\n\
                        <div class="col-lg-8"><div class="form-group"><lable>' + birthday[0] + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Telephone  </b></label></div></div>\n\
                            <div class="col-lg-8"><div class="form-group"><lable>0' + seeker.telephone + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Mobile  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>0' + seeker.mobile + '</label></div></div></div>';
                            display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Address </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + seeker.address1 + ', ' + seeker.address2 + ', ' + seeker.address3 + '</label></div></div></div>';
                            display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>E-Mail  </b></label></div></div>\n\
                                <div class="col-lg-8"><div class="form-group"><lable>' + seeker.email + '</label></div></div></div>';
                            //                            display += '<div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b>Web Site </b></label></div></div>\n\
                            //                                <div class="col-lg-8"><div class="form-group"><lable>' + seeker.website + '</label></div></div></div>';
                            display += '<div class = "row"><div class = "col-lg-12"><h3> Educational Information </h3><hr></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Ordinary Level Results  </b></label></div></div>\n\
                                    <div class="col-lg-8"><div class="form-group"><lable>' + olResultSummery + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Advanced Level Results  </b></label></div></div>\n\
                                        <div class="col-lg-8"><div class="form-group"><lable>' + alResultSummery + '</label></div></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Higher Education  </b></label></div></div>\n\
                                            <div class="col-lg-8"><div class="form-group"><lable>' + uniResultSummery + '</label></div></div></div>';
                            display += '<div class = "row"><div class = "col-lg-12"><h3> Professional Information </h3><hr></div></div>';
                            display += '<form action="" role="form"><div class = "row"><div class="col-lg-4"><div class="form-group"><lable><b> Higher Education  </b></label></div></div>\n\
                                                <div class="col-lg-8"><div class="form-group"><lable>' + professionalSummery + '</label></div><hr>';
                            display += '<div class = "row"><div class = "col-lg-12"></div></div></div></div>';
                            $('#vacancybody').append(display);
                            $("#vacancymodal").modal();
                        });

                    }
                    else if (buttonClicked.toString() === 'fullcv') {
                        $("#vacancymodal").modal('toggle');
                    }
                });

                function ApproveReject(status, vacancyId, applicantId, interviewDate) {
                    var interview;
                    if (typeof interviewDate !== 'undefined') {
                        interview = interviewDate.toString();
                    }

                    var parameter = {vacancyId: vacancyId, applicantId: applicantId, status: status, interviewDate: interview};
                    $.getJSON('ApproveRejectVacancy', parameter, function (data) {
                        if (data.isApproved.toString() === 'true') {
                            showSuccessApprove();
                        }
                        else if (data.isRejected.toString() === 'true') {
                            showSuccessReject();
                        }
                        else {
                            showError();
                        }
                    });
                }
            });
        </script>
        <style>
            .modal-body{
                height:500px;
            }
        </style>
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
                        <li>
                            <a href="OpenCompanyInterviews"><i class="fa fa-fw fa-table"></i> Upcoming Interviews</a>
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
                                Applicants...!!
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                                </li>
                                <li class="active"> <i class="fa fa-edit">Applicants</i>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="modal fade" id="vacancymodal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" >
                                    <h3 id="vacancyheader"></h3>
                                </div>
                                <div class="modal-body" id="vacancybody">

                                </div>
                                <div class="modal-footer">
                                    <form action="GenerateCV">
                                        <s:hidden id="sname" name="seekerId"/>
                                        <button type="submit" class="btn btn-primary btn-default" id="fullcv">Complete CV</button>
                                        <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>     
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div class="modal fade" id="approvemodal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" >
                                    <h4 id="approveheader">Do you want to Approve the Applicant </h4>
                                </div>

                                <div class="modal-footer">
                                    <button class="btn btn-success btn-default" id="approvemodalbutton">Approve</button>
                                    <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>     
                                </div>
                            </div>
                        </div>
                    </div>      
                    <div class="modal fade" id="rejectmodal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" >
                                    <h4 id="rejectmodalheader">Do you want to Reject the Applicant </h4>
                                </div>

                                <div class="modal-footer">
                                    <button class="btn btn-danger btn-default" id="rejectmodalbutton">Reject</button>
                                    <button type="reset" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>     
                                </div>
                            </div>
                        </div>
                    </div>                         

                    <div class="row">
                        <div id="successapprove" class="alert alert-success">
                            <strong>Success!</strong> You have successfully approved the Applicant for an upcoming interview
                        </div>
                        <div id="successreject" class="alert alert-success">
                            <strong>Success!</strong> You have successfully rejected the Applicant
                        </div>
                        <div id="error" class="alert alert-danger">
                            <strong>Oh snap!</strong> Change a few things up and try again.
                        </div>

                        <div class="col-lg-2">

                            <label id="interviewlabel">Interview Date</label> <input type="date" class="textbox-inline form-control" id="interviewdate" name="interviewdate">
                            <label class="label label-danger" id="interviewerrorlabel">Select an Interview Date</label>
                            <label class="label label-danger" id="dateerrorlabel">Select an date after today</label>
                            <%--<sx:datetimepicker label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy" cssClass="textbox-inlie form-control" cssStyle="width:15em;" id="interviewdate"/>--%>
                        </div>
                        <div class="col-lg-4">    
                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-2">
                            <div class="form-group">
                                Vacancy ID  <label id="vacancyId"><s:property value="vacancyId"/></label><br>
                                Position  <label><s:property value="vacancy.title"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="row panel panel-primary" style="margin-top:2%;">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Applicant Details</h3>
                                </div>
                                <div class="panel-body">
                                    <s:iterator value="applicants" var="applicant">
                                        <div class="row">                                 
                                            <div class="col-lg-12 col-md-12">
                                                <div class="row">
                                                    <div class="col-lg-3 col-md-3">
                                                        <center>
                                                            <span class="text-left">             
                                                                <s:if test="%{#applicant.value==0}">
                                                                    <span id="successlabel" class="label label-success">Approved</span>
                                                                </s:if>
                                                                <s:elseif test="%{#applicant.value==2}">
                                                                    <span id=rejectlabel" class="label label-danger">Rejected</span>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <span id="<s:property value="#applicant.key.id"/>pendinglabel" class="label label-warning">Pending</span>
                                                                    <span id="<s:property value="#applicant.key.id"/>appout" class="label label-success approvelabelout">Approved</span>
                                                                    <span id="<s:property value="#applicant.key.id"/>rejout" class="label label-danger rejectlabelout">Rejected</span>
                                                                </s:else>
                                                                <!--<html:img id="<s:property value="#applicant.key.id"/>" class="img-responsive img-thumbnail imageclass" width="100px" height="100px"/>-->                   
                                                                <img id="appimage" src="external/image/applicant<s:property value="#applicant.key.id"/>.jpg" class="img-responsive img-thumbnail" width="150px" height="150px">                   
                                                            </span>
                                                        </center>
                                                    </div>
                                                    <div class="col-lg-9 col-md-9">

                                                        <div class="col-lg-3">
                                                            <label>Applicant Name</label>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <s:property value="#applicant.key.firstName"/> <s:property value="#applicant.key.lastName"/>
                                                        </div>
                                                        <div class="col-lg-3">

                                                        </div>
                                                        <div class="col-lg-3">                                                         
                                                            <button id="cvbtn" class="btn btn-info">View CV</button>
                                                            <s:hidden id="applicantid" value="%{#applicant.key.id}"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9">
                                                        <div class="col-lg-3">
                                                            <label>E-Mail</label>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <s:property value="#applicant.key.email"/>
                                                        </div>
                                                        <div class="col-lg-3">

                                                        </div>
                                                        <div class="col-lg-3">   
                                                            <div id="approvediv">                                                    
                                                                <s:if test="%{#applicant.value==0}">
                                                                    <button id="approvebutton" class="btn btn-success" disabled="true">Approve</button>
                                                                </s:if>
                                                                <s:elseif test="%{#applicant.value==2}">
                                                                    <button id="approvebutton" class="btn btn-success" disabled="true">Approve</button>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <button id="approvebutton" class="btn btn-success">Approve</button>
                                                                </s:else>
                                                                <s:hidden id="applicantid" value="%{#applicant.key.id}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9">
                                                        <div class="col-lg-3">
                                                            <label>Telephone </label>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            0<s:property  value="#applicant.key.mobile"/>
                                                        </div>
                                                        <div class="col-lg-3">

                                                        </div>
                                                        <div class="col-lg-3">
                                                            <div id="rejectdiv">
                                                                <s:if test="%{#applicant.value==0}">
                                                                    <button id="rejectbutton" class="btn btn-danger" disabled="true">&#160Reject&#160&#160</button>
                                                                </s:if>
                                                                <s:elseif test="%{#applicant.value==2}">
                                                                    <button id="rejectbutton" class="btn btn-danger" disabled="true">&#160Reject&#160&#160</button>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <button id="rejectbutton" class="btn btn-danger">&#160Reject&#160&#160</button>
                                                                </s:else>

                                                                <s:hidden id="applicantid" value="%{#applicant.key.id}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9"></div>
                                                    <div class="col-lg-9 col-md-9">
                                                        <div class="col-lg-3">
                                                            <label>Birthday </label>
                                                        </div>
                                                        <div class="col-lg-5">                                                          
                                                            <form>
                                                                <div class="form-group">
                                                                    <s:property value="#applicant.key.birthday"/>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr>   
                                    </s:iterator> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
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

    </body>

</html>
