<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main page</title>
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap.css">
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap-theme.css">
<script type="text/javascript" data-main="/resources/js/main.js" src="/resources/js/require.js"></script>
</head>
<body>
<div class="text-primary">Hello ${username }&nbsp;&nbsp;&nbsp;&nbsp;<a href="/signOut">sign out</a></div>
<div class="container">
	<div class="col-sm-9">
		<table class="table table-striped table-bordered" id="dataTable">
			<caption>user list</caption>
			<thead>
				<tr>
					<th>#</th>
					<th>username</th>
					<th>password</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<div class="col-sm-2 pull-left">
			<select id="pageSize" class="form-control"><option value="10">每页10条</option><option value="20">每页20条</option><option value="50">每页50条</option><option value="100">每页100条</option></select>
			</div>
			<div class="col-sm-6 pull-right">
			<ul id="pagination" class="pagination" style="margin-top: 0;">
				<li>
					<a href="javascript:void(0)" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<li>
					<a href="javascript:void(0)" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				<li>
					<div class="input-group" style="width: 24%;">
						<input type="text" class="form-control" />
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">Go</button>
						</span>
					</div>
				</li>
			</ul>
			</div>
		</nav>
	</div>
</div>
</body>
</html>