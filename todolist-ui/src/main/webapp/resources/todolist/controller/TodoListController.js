'use strict';


TodoListApp.controller('TodoListController', ['$scope', '$rootScope', '$resource', '$location',
	function ($scope, $rootScope, $resource, $location) {

		$scope.sortDir = "DESC";

		if (!$rootScope.isLoggedIn) {
			return;
		}

		function updateList(sortField, sortDir) {
			var params = {};
			params.sortField = sortField ? sortField : "CREATED_DATE";
			params.sortDir = $scope.sortDir;
			$resource('api/note/list').get(params, function (response) {
				for (var i = 0; i < response.list.length; i++) {
					response.list[i].dueDate = new Date(response.list[i].dueDate);
				}
				$scope.list = response.list;
			}, handleTheError);
		}

		$scope.sortList = function(column) {
			updateList(column);
			if ($scope.sortDir == "DESC") {
				$scope.sortDir = "ASC";
			} else {
				$scope.sortDir = "DESC";
			}
		};

		$scope.create = function (todo, createForm) {

			if (createForm.$invalid) {
				alert("Please check all required fields");
				return;
			}

			$resource('api/note/add').save(todo, function (response, header) {
				updateList();

			}, handleTheError);
		};

		$scope.updateTodo = function (todo, updateForm, index) {
			todo.dueDate = document.getElementById("dueDate_" + index).value;

			if (updateForm.$invalid) {
				alert("Please check all required fields");
				return;
			}

			$resource('api/note/update/' + todo.id).save(todo, function (response, header) {
				updateList();

			}, handleTheError);

		};

		$scope.deleteTodo = function (todo) {
			$("#deleteConfirm").dialog({
				resizable: false,
				modal: true,
				title: "Modal",
				height: 150,
				width: 400,
				buttons: {
					"Yes": function () {
						$(this).dialog('close');
						$resource('api/note/delete/' + todo.id).delete({}, function (response, header) {
							console.log("delete");
							updateList();
						}, function (err) {
							console.log("err", err);
							if (err && err.data.error.name == "EmptyResultDataAccessException") {
								alert("Please reload the page");
							} else {
								handleTheError(err);
							}
						});
					},
					"No": function () {
						$(this).dialog('close');
						return;
					}
				}
			});
		};

		updateList();
	}
]);