function ControllerService(angularModule) {
	this.registerController = function(controllerName, functionDetail) {
		angularModule.controller(controllerName, functionDetail);
	}

};

var controllerService = new ControllerService(app);

controllerService.registerController("welcomeController", function($scope,
		$state, UserService) {

});

controllerService.registerController("fileController", function($scope, $state, UserService, 
		FileFactory, FileService) {

	$scope.files = FileFactory.getFiles();

	var sort = new SortUtil('fileName');
	$scope.sort = sort.sort;

	$scope.selectedColumn = sort.selectedColumn;
	$scope.changeSorting = sort.changeSorting;

	$scope.collapseState = false;

	$scope.onExpand = function(reviewCode, fullPath) {
		$scope.user = (UserService.getUser() === 'undefined') ? 'rajiv' : UserService.getUser().userName;
		$scope.reviewcode = reviewCode;
		$scope.fullpath = fullPath;
		console.log(reviewCode + ' : ' + fullPath + ' : ' + $scope.user);

		FileService.getFileContent({
			username : $scope.user,
			reviewcode : $scope.reviewcode}, {
			fullpath : $scope.fullpath
		}).$promise.then(function(data) {
			console.log(data);
			//FileFactory.setFiles(data);
			//$state.transitionTo('success.files');
		});

	}

});

controllerService.registerController("loginController", function($scope,
		$state, UserService, LoginService) {

	$scope.user = {
		userName : '',
		password : ''
	};

	$scope.UserService = UserService;

	$scope.validate = function() {
		LoginService.validate($scope.user).$promise.then(function(data) {
			$scope.UserService.addUser(data);
			$state.transitionTo('success');
		});
	};

	$scope.submitForm = function(isValid) {
		// check to make sure the form is completely valid
		if (isValid) {
			alert('our form is amazing');
		}

	};
});

controllerService.registerController("successController", function($scope,
		$modal, $log, $rootScope, $state, UserService, FileFactory,
		DashboardService) {
	$scope.user = (UserService.getUser() === 'undefined' || UserService
			.getUser() === undefined) ? 'rajiv' : UserService.getUser();

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
		DashboardService.getReviews({
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

	$scope.getFileDetails = function(reviewCode) {
		$scope.reviewCode = reviewCode;
		DashboardService.getFilesByReviewCode({
			username : $scope.user,
			reviewcode : $scope.reviewCode
		}).$promise.then(function(data) {
			FileFactory.setFiles(data);
			$state.transitionTo('success.files');
		});
	};

});

var CreateReviewController = function($scope, $modalInstance, data,
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

controllerService.registerController("dashboardController", function($scope,
		UserService) {
	$scope.user = UserService.getUser();

});

controllerService.registerController("adminController", function($scope,
		UserService) {
	$scope.user = UserService.getUser();
});