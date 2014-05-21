var app = angular.module("login", [ 'ngRoute', 'pascalprecht.translate', 'ui.bootstrap', 'ui.bootstrap.tpls', 'ui.bootstrap.modal' ]);

app.service('userService', function() {

	var user = '';

	this.addUser = function(user) {
		this.user = user;
	};
	
	this.getUser = function() {
		return this.user;
	};
});

app.controller("welcomeController", function($scope, $http, $location,
		userService) {
	
});

app.controller("loginController", function($scope, $http, $location,
		userService) {

	$scope.user = {
		userName : '',
		password : ''
	};

	$scope.userService = userService;

	$scope.validate = function() {
		$http.post('login/validate', $scope.user).success(function(data) {
			$scope.userService.addUser(data);
			$location.path('/login/success');
		});
	}
});

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'index.html',
		controller : 'welcomeController'
	}).when('/login/success', {
		templateUrl : 'login/success',
		controller : 'successController'
	}).when('/success', {
		templateUrl : 'login/success',
		controller : 'loginController'
	}).when('/admin', {
		templateUrl : 'admin.html',
		controller : 'adminController'
	}).otherwise({
		redirectTo : '/'
	});
});

app.controller("successController", function($scope, $route, $http, $modal, $log, userService) {
	$scope.user = (userService.getUser() === 'undefined' || userService.getUser() === undefined) ? 'rajiv' : userService.getUser();
	
	$scope.reviews = "";
	$scope.init = function() {
		$http.get('dashboard/get/reviews/'+$scope.user).success(function(data) {
			$scope.reviews = data;
		});
	};
	
	$scope.items = ['item1', 'item2', 'item3'];

  $scope.open = function (size) {

    var modalInstance = $modal.open({
      templateUrl: 'myModalContent.html',
    controller: ModalInstanceCtrl,
      size: size,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });
  };
});

var ModalInstanceCtrl = function ($scope, $modalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.ok = function () {
    $modalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};

app.controller("dashboardController", function($scope, $route, $http, userService) {
	$scope.user = userService.getUser();
   
});


app.controller("adminController", function($scope, $route, userService) {
	$scope.user = userService.getUser();
	
	
});
