'use strict';


TodoListApp.controller('RegisterController', ['$scope', '$rootScope', '$resource', '$location',
	function ($scope, $rootScope, $resource, $location) {

		if ($rootScope.isLoggedIn) {
			$location.path("/");
		}

		$scope.register = function (account, registrationForm) {

			if (!registrationForm.$valid) {
				alert("Please check all required fields");
				return;
			}
			if (account.password != account.repeatedPassword) {
				alert("Password fields don't match");
				return;
			}
			if (account.password && account.password.length < 3) {
				alert("Password must have at least 3 characters");
				return;
			}

			$resource('api/session/register').save(account, function (response, header) {
				$rootScope.isLoggedIn = response.hasLoggedIn;
				if ($rootScope.isLoggedIn) {
					$location.path("/");
				}
			}, handleTheError);

		};

	}
]);