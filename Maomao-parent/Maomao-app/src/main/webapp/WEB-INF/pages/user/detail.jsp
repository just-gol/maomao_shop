<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>student list</title>
<%
	pageContext.setAttribute("PRO_PATH", request.getContextPath());
%>
<script type="text/javascript"
	src="${PRO_PATH}/static/js/jquery-1.4.2.min.js"></script>
<link
	href="${PRO_PATH}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${PRO_PATH}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="lxsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>学生!</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button type="button" class="btn btn-info" id="addbuttonid">Add</button>
				<button type="button" class="btn btn-warning">Del</button>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>id</th>
						<th>name</th>
						<th>phone</th>
						<th>account</th>
					</tr>
					<tr>
						<th>${user.userId}</th>
						<th>${user.username}</th>
						<th>${user.phone}</th>
						<th>${user.account}</th>
					</tr>
				</table>
			</div>
		</div>

		

	</div>
	
	<script type="text/javascript">
	$("#addbuttonid").click(function(){
		window.location.href="${PRO_PATH}/adduserList";
		$("#lxsModal").modal({
			backdrop:"static"
		});
	});
	</script>
</body>
</html>