function ControllerService(angularModule) {
	this.registerController = function(controllerName, functionDetail) {
		angularModule.controller(controllerName, functionDetail);
	}
};

var controllerService = new ControllerService(app);

var TitleController = function($scope, $state) {

	$scope.isBack = false;

	$scope.init = function(isBack) {
		$scope.isBack = isBack;
	}

	$scope.back = function() {
		if ($scope.isBack) {
			$state.transitionTo('success');
		}
	}

}

var LoaderController = function($scope) {

	$scope.isVisible = false;

}

var TableController = function($scope, ServiceLocater, UserService) {
	$scope.user = (UserService.getUser() === 'undefined' || UserService
			.getUser() === undefined) ? 'rajiv' : UserService.getUser();

	var sortColumn;
	
	var spinner = new Spinner();
	$scope.spinner = spinner.getSpinner();

	$scope.init = function(sortColumn, service, params) {
		this.sortColum = sortColumn;
		var service = ServiceLocater.locate(service);
		if (params === undefined || params === 'undefined') {
			params = {
				username : $scope.user
			};
		} else {
			params.username = $scope.user
		}
		service.get(params).$promise.then(function(data) {
			$scope.data = data;
		}).catch(function(){
			$scope.data = [];
		});
	}

	var sort = new SortUtil(sortColumn);

	$scope.sort = sort.sort;

	$scope.selectedColumn = sort.selectedColumn;
	$scope.changeSorting = sort.changeSorting;
};

var ReviewTableController = function($scope, ServiceLocater, UserService) {
	TableController.call(this, $scope, ServiceLocater, UserService);
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
}

controllerService.registerController("welcomeController", function($scope,
		$state, UserService) {

});

controllerService.registerController("fileController", function($scope, $state,
		UserService, FileFactory, FileService) {

	$scope.files = FileFactory.getFiles();

	var sort = new SortUtil('fileName');
	$scope.sort = sort.sort;
	
	$scope.selectedColumn = sort.selectedColumn;
	$scope.changeSorting = sort.changeSorting;

	$scope.collapseState = false;
	$scope.data;

	$scope.onExpand = function(reviewCode, name, isCollapsed, showLoader) {
		if (!isCollapsed) {
			var spinner = new Spinner();
			$scope.data = spinner.getSpinner();
			
			$scope.user = (UserService.getUser() === 'undefined' || UserService
					.getUser() === undefined) ? 'rajiv'
					: UserService.getUser().userName;
			$scope.reviewcode = reviewCode;
			$scope.name = name;

			FileService.getFileContent({
				username : $scope.user,
				reviewcode : $scope.reviewcode
			}, {
				name : $scope.name
			}).$promise.then(function(data) {
				var convertor = new Convertor();
				hljs.configure({
					tabReplace : '<span class="indent">\t</span>',
					useBR : true
				});
				var hText = hljs.highlightAuto(data.contentValue).value;
				var marker = new Marker();
				var markedText = marker.markChanges(hText, 'num_tick',
						'no_changes', 'added_changes', 'deleted_changes');
				showLoader = false;
				$scope.data = markedText;
			}).catch(function(){
				showLoader = false;
				$scope.data = [];
			});
		}
		
	};

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

	$scope.items = [ 'item1', 'item2', 'item3' ];

	var model = new Modal($scope, $modal, 'createReviewView.html',
			CreateReviewController, 'app-modal-window');

	$scope.open = model.open;

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

controllerService.registerController("titleController", TitleController);
controllerService.registerController("tableController", TableController);
controllerService.registerController("loaderController", LoaderController);

controllerService.registerController("reviewTableController",
		ReviewTableController);

controllerService.registerController("dashboardController", function($scope,
		UserService) {
	$scope.user = UserService.getUser();

});

controllerService.registerController("adminController", function($scope,
		UserService) {
	$scope.user = UserService.getUser();
});