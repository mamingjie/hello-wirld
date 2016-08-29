requirejs.config({
	baseUrl: '/resources/js',
	shim: {
		'bootstrap': ['jquery']
	},
	paths: {
		'jquery': 'jquery/jquery-3.1.0',
		'bootstrap': 'bootstrap-3.3.7/js/bootstrap',
		'jquery.validate': 'jquery.validation/jquery.validate',
		'jquery.validate.additional.methods': 'jquery.validation/additional-methods',
		'jquery.form': 'jquery/jquery.form'
	}
});
