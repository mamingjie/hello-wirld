<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>export page</title>
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap.css">
<link rel="stylesheet" href="/resources/js/bootstrap-3.3.7/css/bootstrap-theme.css">
<script type="text/javascript" src="/resources/js/require.js"></script>
</head>
<body>
<div class="container">
	<div class="col-sm-10">
	<div class="col-sm-4">
		<div class="form-group">
			<input id="total" class="form-control" type="number" />
		</div>
	</div>
	</div>
	<div class="col-sm-10">
	<div class="col-sm-4">
		<div class="form-group">
			<button type="button" id="export" class="btn btn-primary">export</button>
		</div>
	</div>
	</div>
</div>
<div class="modal fade" id="progressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="progress progress-striped active" style="margin-top: 18px;">
					<div id="progressBar" class="progress-bar progress-bar-success" role="progressbar" style="width: 0;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
requirejs(['/resources/js/require_config.js'], function() {
	requirejs(['jquery', 'bootstrap'], function() {
		$(function() {
			var key, url1, total, progress, url2;
			$('#export').click(function() {
				if (!$('#total').val()) {
					return;
				}
				$('#progressBar').css('width', '0');
				$('#progressModal').modal({
					backdrop: 'static',
					keyboard: false,
					show: true
				});
				key = '';
				url1 = '/export?total=' + $('#total').val();
				url2 = '';
				process();
			});
			var process = function() {
				if (key && key != '') {
					url2 = '&key=' + key;
				}
				$.get(url1 + url2, function(data) {
					key = data.key;
					total = data.total;
					progress = data.progress;
					$('#progressBar').css('width', (progress * 100 / total) + '%');
					if (progress == total) {
						$('#progressModal').modal('hide');
						url2 = '&key=' + key + '&down=1';
						window.location.href = url1 + url2;
					} else {
						setTimeout(process, 50);
					}
				});
			}
		});
	});
});
</script>
</body>
</html>