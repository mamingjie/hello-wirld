requirejs(['/resources/js/require_config.js'], function() {
	requirejs(['jquery', 'bootstrap'], function() {
		$(function() {
			loadPage(0);
		});
		
		function loadPage(page) {
			$.ajax({
				url: 'user/queryUser',
				method: 'post',
				data: {
					page: page,
					size: $('#pageSize').val()
				},
				success: function(data) {
					setPagination(eval(data));
				}
			});
		}
		
		function setPagination(data) {
			resetDataTable(data.data);
			var html = '';
			if (data.count < 5) {
				for (var i=0; i < page.count; i++) {
					if (i === data.page) {
						html = html + '<li class="active"><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
					} else {
						html = html + '<li><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
					}
				}
			} else {
				if (data.page < 3) {
					for (var i=0; i < 5; i++) {
						if (i == data.page) {
							html = html + '<li class="active"><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
						} else {
							html = html + '<li><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
						}
					}
				} else if (data.page >= data.count - 3) {
					for (var i=data.count-5; i < data.count; i++) {
						if (i == data.page) {
							html = html + '<li class="active"><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
						} else {
							html = html + '<li><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
						}
					}
				} else {
					for (var i=data.page-2; i < data.page+3; i++) {
						if (i == data.page) {
							html = html + '<li class="active"><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
						} else {
							html = html + '<li><a href="javascript:void(0)" aria-label="' + i + '">' + (i+1) + '</a></li>';
						}
					}
				}
			}
			if (data.page == 0 && data.page + 1 == data.count) {
				html = '<li class="disabled"><a href="javascript:void(0)" aria-label="' + (data.page-1) + '"><span aria-hidden="true">&laquo;</span></a></li>' + html;
				html = html + '<li class="disabled"><a href="javascript:void(0)" aria-label="' + (data.page+1) + '"><span aria-hidden="true">&raquo;</span></a></li>';
				
			} else if (data.page == 0) {
				html = '<li class="disabled"><a href="javascript:void(0)" aria-label="' + (data.page-1) + '"><span aria-hidden="true">&laquo;</span></a></li>' + html;
				html = html + '<li><a href="javascript:void(0)" aria-label="' + (data.page+1) + '"><span aria-hidden="true">&raquo;</span></a></li>';
				
			} else if (data.page + 1 == data.count) {
				html = '<li><a href="javascript:void(0)" aria-label="' + (data.page-1) + '"><span aria-hidden="true">&laquo;</span></a></li>' + html;
				html = html + '<li class="disabled"><a href="javascript:void(0)" aria-label="' + (data.page+1) + '"><span aria-hidden="true">&raquo;</span></a></li>';
				
			} else {
				html = '<li><a href="javascript:void(0)" aria-label="' + (data.page-1) + '"><span aria-hidden="true">&laquo;</span></a></li>' + html;
				html = html + '<li><a href="javascript:void(0)" aria-label="' + (data.page+1) + '"><span aria-hidden="true">&raquo;</span></a></li>';
				
			}
			html = html + '<li><div class="input-group" style="width: 24%;"><input type="text" class="form-control" /><span class="input-group-btn"><button class="btn btn-default" type="button">Go</button></span></div></li>';
			$('#pagination').empty();
			$('#pagination').append(html);
			
			$('#pagination').find('a').click(function() {
				var page = $(this).attr('aria-label');
				if (page != data.page && page >=0 && page < data.count) {
					loadPage(page);
				}
			});
			
			$('#pagination').find('button').click(function() {
				loadPage($('#pagination').find('input').val()-1);
			});
			
			$('#pageSize').val(data.size);
			$('#pageSize').change(function() {
				loadPage(0);
			});
			
		}
		
		function resetDataTable(dataList) {
			var body = $('#dataTable').children('tbody');
			body.empty();
			$.each(dataList, function(i, e) {
				body.append('<tr><th scope="row">' + (i+1) +'</th><td>' + e.username + '</td><td>' + e.password + '</td></tr>');
			});
		}
	});
});