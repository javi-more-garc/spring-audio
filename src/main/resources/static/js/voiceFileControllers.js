var app = angular.module('voiceFileControllers', []);

app.controller('voiceFileController', function($scope, voiceFileFactory, $http) {

	$scope.voiceFiles = {};

	$scope.getAll = function() {
		voiceFileFactory.getAll().success(function(data) {
			// TODO pagination
			$scope.voiceFiles = data.content;

		}).error(function(error) {
			// TODO handle errors
			// notifications.showError($translate.instant('GLOBAL_GET_AUDIENCES_ERROR'));
		});
	};
	
});