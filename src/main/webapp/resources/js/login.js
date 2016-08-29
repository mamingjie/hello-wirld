requirejs(['/resources/js/require_config.js'], function() {
	requirejs(['jquery', 'bootstrap', 'jquery.validate', 'jquery.validate.additional.methods', 'jquery.form'], function() {
		$(function() {
			$('#form1').validate({
				debug: true,
				rules: {
					username: {
						required: true,
						minlength: 4,
						maxlength: 20,
						alphanumeric: true
					},
					password: {
						required: true,
						minlength: 4,
						maxlength: 20
					}
				},
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: 'signIn',
						method: 'post',
						asyn: false,
						success: function(data) {
							if (data === 'success') {
								window.location.href = 'main';
							} else {
								$('#loginMessage').text('用户名或密码错误！');
								$('#loginMessage').removeClass('hidden');
							}
						}
					})
					//$(form).ajaxSubmit();
				},
				unhighlight: function(element, errorClass, validClass) {
					$(element).parent().removeClass('has-error').addClass('has-success').addClass('has-feedback');
					$(element).parent().children('.glyphicon').remove();
					$(element).parent().append('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
					$(element).removeClass(errorClass);
					$(element).addClass(validClass);
					$(element).popover('hide');
				},
				errorPlacement: function(label, element) {
					$(element).parent().removeClass('has-success').addClass('has-error').addClass('has-feedback');
					$(element).parent().children('.glyphicon').remove();
					$(element).parent().append('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
					$(element).popover({
						placement: 'right',
						//content: $(label).text(),
						template: '<div class="popover" role="tooltip" style="white-space: nowrap; max-width: none;"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>',
						trigger: 'manual'
					});
					$(element).attr('data-content', $(label).text());
					$(element).popover('show');
				}
			});
			
		});
	});
});