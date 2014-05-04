var app = angular.module("login", [ 'ngRoute', 'pascalprecht.translate' ]);

app.service('soa', function() {

	var user = '';

	this.addUser = function(user) {
		this.user = user;
	};
	this.getUser = function() {
		return this.user;
	};
});

app.controller("loginController", function($scope, $http, $location, soa) {
	$scope.welcome = 'Welcome screen......';

	$scope.user = {
		userName : '',
		password : ''
	};

	$scope.soa = soa;

	$scope.validate = function() {
		$http.post('login/validate', $scope.user).success(function(data) {
			$scope.soa.addUser(data);
			$location.path('/success');
		});
	}
});

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'login.html',
		controller : 'loginController'
	}).when('/success', {
		templateUrl : 'success.html',
		controller : 'successController'
	}).when('/admin', {
		templateUrl : 'admin.html',
		controller : 'adminController'
	}).otherwise({
		redirectTo : '/login'
	});
});

app.controller("successController", function($scope, $route, soa) {
	$scope.user = soa.getUser();
});

app.controller("adminController", function($scope, $route, soa) {
	$scope.user = soa.getUser();
});
