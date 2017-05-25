<%@page import="com.designhub.fitnessplus.bean.AdminBean"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.designhub.fitnessplus.bean.TrainerBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Trainer List</title>


<link rel="stylesheet"
	href="fitnessplus-Cascading Style Sheet/kendo.common-material.min.css" />
<link rel="stylesheet"
	href="fitnessplus-Cascading Style Sheet/kendo.material.min.css" />

<script src="fitnessplus - Javascript/jquery.min.js"></script>
<script src="fitnessplus - Javascript/kendo.all.min.js"></script>

<link rel="stylesheet" href="admin/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="admin/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="admin/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="admin/plugins/iCheck/flat/blue.css">
<link rel="stylesheet" href="admin/plugins/morris/morris.css">
<link rel="stylesheet"
	href="admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="admin/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="admin/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet"
	href="admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

</head>
<body>
	<%
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");

		if (adminBean != null && adminBean.getAdminId() != null) {
	%>
	<%@ include file="admin/adminHeader.jsp"%>
	<%
		ArrayList<TrainerBean> listOfTrainerCheck = (ArrayList) request
					.getAttribute("listofTrainer");
			if (listOfTrainerCheck != null) {
	%>
	<div class="content-wrapper" style="margin-top: -1000px">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			Trainer <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminhomepage.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Trainer</li>
		</ol>
		<br>
		<br>
		<a href="trainerInsert.jsp"><input type="button" value="ADD"
			name="ADD" class="btn btn-primary"> </a> <br>
		<center>

			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Trainer List</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example1" class="table table-bordered table-striped">
						<%
							ArrayList<TrainerBean> listOfTrainer = (ArrayList) request
											.getAttribute("listofTrainer");
									if (listOfTrainer != null) {
						%>
						<thead>
							<tr align="center">

								<td><b>Trainer Name</b></td>
								<td><b>Email-Id</b></td>
								<td><b>Gender</b></td>
								<td><b>DOB</b></td>
								<td><b>PhoneNo</b></td>
								<td><b>Experiance</b></td>
								<td><b>Action</b></td>
							</tr>
						</thead>
						<tbody>
							<%
								TrainerBean trainerBean;
											for (int i = 0; i < listOfTrainer.size(); i++) {
												trainerBean = listOfTrainer.get(i);
							%>
							<tr align="center">
								<td><%=trainerBean.getTrainerLastName() + " "
									+ trainerBean.getTrainerFirstName()%></td>
								<td><%=trainerBean.getTrainerEmail()%></td>
								<td><%=trainerBean.getTrainerGender()%></td>
								<td><%=trainerBean.getTrainerDOB()%></td>
								<td><%=trainerBean.getTrainerPhoneNo()%></td>
								<td><%=trainerBean.getTrainerExperience()%></td>
								<td><a
									href="TrainerEditServlet?trainerId=<%=trainerBean.getTrainerId()%>">
										<i class="fa fa-edit"></i> </a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="TrainerDeleteServlet?trainerId=<%=trainerBean.getTrainerId()%>">
										<i class="glyphicon glyphicon-trash"></i> </a></td>
							</tr>
							<%
								}
							%>
						</tbody>
						<%
							}
						%>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</center>
		</section>
	</div>
	<%
		} else {
	%>
	<div class="content-wrapper" style="margin-top: -1000px">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			Trainer <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="admin/adminHeader.html"><i
					class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Trainer</li>
		</ol>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<h1 align="center" style="color: red;">No Record Found......</h1>
		</section>
	</div>


	<%
		}
	%>
	<!-- jQuery 2.1.4 -->
	<script src="admin/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="admin/bootstrap/js/bootstrap.min.js"></script>
	<!-- DataTables -->
	<script src="admin/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="admin/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="admin/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="admin/plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script src="admin/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="admin/dist/js/demo.js"></script>
	<script>
		$(function() {
			$("#example1").DataTable();
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false
			});
		});
	</script>
	<%
		} else {
			response.sendRedirect("userlogin.jsp");

		}
	%>
</body>
</html>