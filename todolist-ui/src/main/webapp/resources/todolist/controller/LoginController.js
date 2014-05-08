'use strict';

TodoListApp.controller('LoginController', ['$scope', '$rootScope', '$resource', '$location',
	function ($scope, $rootScope, $resource, $location) {

		if ($rootScope.isLoggedIn) {
			$location.path("/");
		}

		$scope.login = function (account) {
			$resource('api/session/login').save(account, function (response) {
					$rootScope.isLoggedIn = response.hasLoggedIn;
					if ($rootScope.isLoggedIn) {
						$location.path("/");
					}
				}, function onError(response, arg2) {
					var error = response.data ? response.data.error : {};
					if (error.name == 'BadCredentialsException') {
						alert("User credentials are invalid");
					}
				}
			);
		};
	}
]);