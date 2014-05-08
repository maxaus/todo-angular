'use strict';

//The global constants are defined here
TodoListApp.controller('MainController', ['$scope', '$rootScope', '$resource',
	function ($scope, $rootScope, $resource) {
		$resource('api/session/isLoggedIn').get({}, function (response) {
			$rootScope.isLoggedIn = response.isLoggedIn;
			console.log("$rootScope.isLoggedIn ", $rootScope.isLoggedIn);
		}, handleTheError);

		$rootScope.logout = function () {
			$resource('api/session/logout').save({}, function (response) {
				$rootScope.isLoggedIn = false;
			}, handleTheError);

		}
	}
]);