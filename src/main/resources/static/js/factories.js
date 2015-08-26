var app = angular.module("factories", []);

// factories

// 
// voice files

app.factory("voiceFileFactory", [ "$http", "$rootScope", function($http, $rootScope) {

	var dataFactory = {};

	var url = "/api/files";

	dataFactory.getAll = function() {
		return $http.get(url);
	};

	return dataFactory;
} ]);
