var app = angular.module("login", [ 'ngResource', 'ngRoute',
		'pascalprecht.translate', 'ui.bootstrap', 'ui.bootstrap.tpls',
		'ui.bootstrap.modal' ]);

reviewModel =  {
		"files" : null,
		"reviewCode" : null,
		"reviewDescription" : null,
		"reviewId" : null,
		"reviewName" : null,
		"reviewStatus" : null,
		"reviewer" : null,
		"reviewers" : null
	};
		
app.factory('GetReviewService', function($resource) {
	return $resource('dashboard/reviews/:username', {
		username : '@username'
	}, {
		getReviews : {
			method : 'GET',
			isArray : true
		}
	})
});

app.factory('ReviewService', function($resource) {
	return $resource('review/:id', {
		id : '@id'
	}, {
		saveReview : {
			method : 'POST'
		},
		getReview : {
			method : 'GET'
		},
		updateReview : {
			method : 'PUT',
			params : {
				id : '@id'
			}
		},
		deleteReview : {
			method : 'DELETE',
			params : {
				id : '@id'
			}
		}
	})
});

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
	};

	$scope.submitForm = function(isValid) {

		// check to make sure the form is completely valid
		if (isValid) {
			alert('our form is amazing');
		}

	};
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

app.controller("successController", function($scope, $route, $http, $modal,
		$log, userService, GetReviewService) {
	$scope.user = (userService.getUser() === 'undefined' || userService
			.getUser() === undefined) ? 'rajiv' : userService.getUser();

	$scope.reviews = "";

	$scope.reviewStatus = [ {
		"id" : "-1",
		"name" : "All"
	}, {
		"id" : "0",
		"name" : "Completed"
	}, {
		"id" : "1",
		"name" : "In-Progress"
	}, {
		"id" : "2",
		"name" : "Not Picked"
	} ];

	$scope.selectedOption = $scope.reviewStatus[0];

	$scope.init = function() {
		GetReviewService.getReviews({
			username : $scope.user
		}).$promise.then(function(data) {
			$scope.reviews = data;
		});
	};

	$scope.items = [ 'item1', 'item2', 'item3' ];

	var model = new Modal($scope, $modal, 'createReviewView.html',
			CreateReviewController, 'app-modal-window');

	$scope.open = model.open;

	var sort = new SortUtil('reviewCode');
	$scope.sort = sort.sort;

	$scope.selectedColumn = sort.selectedColumn;
	$scope.changeSorting = sort.changeSorting;

});

var Modal = function(scope, modal, templateUrl, controller, windowClass) {
	this.templateUrl = templateUrl;
	this.controller = controller;
	this.windowClass = windowClass;
	this.scope = scope;
	this.modal = modal;


	this.open = function(modalData) {

		scope.data = (modalData === undefined) ? reviewModel : modalData;

		modalInstance = modal.open({
			templateUrl : templateUrl,
			controller : controller,
			windowClass : windowClass,
			resolve : {
				data : function() {
					return scope.data;
				}
			}
		});
	};
};

var CreateReviewController = function($scope, $modalInstance, $http, data,
		ReviewService) {

	$scope.review = angular.copy(data);

	$scope.add = function(review) {
		angular.copy($scope.review, data);
		ReviewService.saveReview($scope.review).$promise.then(function() {
			$modalInstance.close();
		}, function(reason) {
			console.log('Failed : ' + reason)
		}, function(update) {
			alert('Got notification: ' + update);
		})
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

app.controller("dashboardController", function($scope, $route, $http,
		userService) {
	$scope.user = userService.getUser();

});

app.controller("adminController", function($scope, $route, userService) {
	$scope.user = userService.getUser();
});

var SortUtil = function SortableTableUtil(defaultSortColumn) {
	this.column = defaultSortColumn;
	this.sort = {
		column : this.column,
		descending : false
	};

	this.selectedColumn = function(column) {
		return column == this.sort.column && 'sort-' + this.sort.descending;
	};

	this.changeSorting = function(column) {
		var sort = this.sort;
		if (sort.column == column) {
			sort.descending = !sort.descending;
		} else {
			sort.column = column;
			sort.descending = false;
		}
	};
}