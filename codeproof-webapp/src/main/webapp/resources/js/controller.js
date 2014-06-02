
function ControllerService(angularModule) {
	this.registerController = function(controllerName, functionDetail) {
		angularModule.controller(controllerName, functionDetail);
	}
	
};

var controllerService = new ControllerService(app);

controllerService.registerController("welcomeController", function($scope, $http, $location,
		userService) {

});

controllerService.registerController("loginController", function($scope, $http, $location,
		userService, LoginService) {

	$scope.user = {
		userName : '',
		password : ''
	};

	$scope.userService = userService;

	$scope.validate = function() {
		LoginService.validate($scope.user).$promise.then(function(data) {
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


controllerService.registerController("successController", function($scope, $route, $http, $modal,
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


var CreateReviewController = function($scope, $modalInstance, $http, data,
		ReviewService) {

	$scope.review = angular.copy(data);

	$scope.add = function() {
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

	$scope.reviewCreation = function(isValid) {
		if (isValid) {
			$scope.add();
		} else {
			console.log("Validation failed while adding review....");
		}

	}

};

controllerService.registerController("dashboardController", function($scope, $route, $http,
		userService) {
	$scope.user = userService.getUser();

});

controllerService.registerController("adminController", function($scope, $route, userService) {
	$scope.user = userService.getUser();
});