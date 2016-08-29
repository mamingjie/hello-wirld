<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>math page</title>
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap.css">
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap-theme.css">
<script type="text/javascript" src="/resources/js/require.js"></script>
</head>
<body>
<div class="container">
	<div class="col-sm-9">
	<form class="form-horizontal">
		<div class="form-group">
			<input id="count" class="form-control" type="text" />
			<button type="button" id="doCount" class="btn btn-primary">generate</button>
			<button type="button" id="doCountCount" class="btn btn-secondary">summary</button>
		</div>
		
	</form>
	</div>
</div>
<script type="text/javascript">
requirejs(['/resources/js/require_config.js'], function() {
	requirejs(['jquery', 'bootstrap'], function() {
		$(function() {
			$('#doCount').click(function() {
				var count = parseInt($('#count').val());
				if (isNaN(count)) {
					return;
				}
				for (var i=0; i < count; i++) {
					$('form').append('<div class="form-group">' +
							'<label class="col-sm-1 control-label">' + i + '</label>' +
							'<div class="col-sm-4"><input id="time' + i + '" class="form-control" type="text" /></div>' +
							'<label class="col-sm-1 control-label">--</label>' +
							'<div class="col-sm-2"><input id="result' + i + '" class="form-control" type="text" /></div>' +
							'</div>');
					$('#result' + i).focus(function() {
						var time = $(this).attr('id').replace('result', 'time');
						var time = $('#' + time).val();
						time = time.split('-');
						
						var start = time[0];
						start = start.split(':');
						var startHour = parseInt(start[0]);
						var startMinute = parseInt(start[1]);
						if (isNaN(startHour) || isNaN(startMinute)) {
							return;
						}
						start = startHour + startMinute / 60;
						console.debug(start);
						
						var end = time[1];
						end = end.split(':');
						var endHour = parseInt(end[0]);
						var endMinute = parseInt(end[1]);
						if (isNaN(endHour) || isNaN(endMinute)) {
							return;
						}
						end = endHour + endMinute / 60;
						console.debug(end);
						
						$(this).val(end - start);
					});
				}
			});
			
			$('#doCountCount').click(function() {
				var count = parseInt($('#count').val());
				if (isNaN(count)) {
					return;
				}
				var sum = 0;
				for (var i=0; i < count; i++) {
					sum += parseFloat($('#result' + i).val());
				}
				console.log(sum);
				console.log(sum / count);
			});
		});
	});
});
</script>
</body>
</html>