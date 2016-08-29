<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login page</title>
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap.css">
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap-theme.css">
<script type="text/javascript" data-main="/resources/js/login.js" src="/resources/js/require.js"></script>
</head>
<body>
<h1 align="center">Hello</h1>
<div class="container col-sm-4 col-sm-offset-4">
<form id="form1" class="form-horizontal">
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">username</label>
		<div class="col-sm-10">
			<input id="username" name="username" type="text" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">password</label>
		<div class="col-sm-10">
			<input id="password" name="password" type="password" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-2">
			<button type="submit" id="login" class="btn btn-primary">login</button>
		</div>
		<div id="loginMessage" class="alert alert-danger col-sm-6 hidden"></div>
	</div>
</form>
</div>
</body>
</html>