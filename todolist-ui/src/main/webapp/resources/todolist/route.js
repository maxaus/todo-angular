'use strict';

TodoListApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	$routeProvider.when('/login', {
		templateUrl: '/view/login.html',
		controller: "LoginController"
	});
	$routeProvider.when('/register', {
		templateUrl: '/view/register.html',
		controller: "RegisterController"
	});
	$routeProvider.when('/', {
		templateUrl: 'view/list.html',
		controller: 'TodoListController',
		locals: function ($scope) {
			console.log("locals");
		}
	});

	$locationProvider.html5Mode(true);
}]);