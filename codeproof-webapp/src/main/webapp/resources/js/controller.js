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

var AddReviewCommentController = function($scope, $modalInstance, $modal, $rootScope, data, ReviewCommentService) {
	
	$scope.data = data;
	
	$scope.comment;
	
	$scope.errorType = {
    	isopen: false,
    	value: 'Text',
    	errors : ['Text', 'Logical', 'Design'],
		disabled : true
  	};

	$scope.commentType = {
    	isopen: false,
    	value: 'Comment',
    	comments : ['Comment', 'Error']
  	};
  	
	$scope.value = {
		reviewComment: '',
		errorType : '',
		commentType : $scope.commentType.value,
		reviewCodeId : data.reviewCode,
		fileName : data.fileName,
		lineNumber : data.lineNumber
	}
  	
  	$scope.disabled = true;
  	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.toggleCommentType = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.commentType.isopen = !$scope.commentType.isopen;
	};
	
	$scope.toggleErrorType = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.errorType.isopen = !$scope.errorType.isopen;
	};
	
	$scope.addComment = function() {
		ReviewCommentService.save($scope.value).$promise.then(function(data){
				$rootScope.$broadcast("REV_CMMNT_UPDT", $scope.data);
				$modalInstance.dismiss('cancel');
		}).catch(function(e){
			var message = new MesageModal($modal,'TT_ERROR','', 'MSG_ERROR_REVIEW_COMMENTS');
			message.open();
			console.log("Exception ... : " + e)
		});
	};
	
	$scope.isErrorType = function(value) {
		$scope.commentType.value=value;
		$scope.value.commentType = value;
		if('Error' === value) {
			$scope.errorType.disabled = false;
			$scope.value.errorType = $scope.errorType.value;
		} else {
			$scope.errorType.disabled = true;
			$scope.value.errorType = '';
		}
	};

	$scope.setErrorType = function(value) {
		$scope.errorType.value = value;
		$scope.value.errorType = value;
	};
}

var FileContentController = function($scope, $modal, $rootScope, UserService, FileService) {
	
	$scope.isCollapsed = true;
	$scope.showLoader = true;
	
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
				$scope.showLoader = false;
				$scope.data = hText.split("\n");
				var data = { reviewCode : $scope.reviewcode, fileName : $scope.name};
				console.log(data);
				$rootScope.$broadcast("REV_CMMNT_UPDT", data);
			}).catch(function(){
				$scope.showLoader = false;
				$scope.data = [];
			});
			
		}else {
			$scope.showLoader = true;
		}
		
	};
	$scope.addReviewComments = function(reviewCode, fileName, lineNumber) {
		var modal = new Modal($scope.$new, $modal, 'addReviewComment.html',
			AddReviewCommentController, 'app-modal-window-review', 'sm');
		var data = { reviewCode : reviewCode, fileName : fileName, lineNumber : lineNumber};
		return modal.open(data);
	};
};

var ShowReviewCommentController = function($scope, ReviewCommentService) {
	
	$scope.$on("REV_CMMNT_UPDT", function(scope, data) {
		if(data) {
			console.log(data.reviewCode + " : " + data.fileName);
			ReviewCommentService.getReviews({reviewcodeid : data.reviewCode, filename : data.fileName}).$promise
			.then(function(data){
				$scope.data = data;
			}).catch(function(e){
				var message = new MesageModal($modal,'TT_ERROR','', 'MSG_ERROR_REVIEW_COMMENTS');
				message.open();
				console.log("Exception ... : " + e)
			});
		}else{
			console.log('No Data');
		}
	});
	
	$scope.init = function() {
		
	}
	
};

controllerService.registerController("welcomeController", function($scope,
		$state, UserService) {

});

controllerService.registerController("fileController", function($scope, $state, $modal, 
		UserService, FileFactory, FileService) {

	$scope.files = FileFactory.getFiles();

	var sort = new SortUtil('fileName');
	$scope.sort = sort.sort;
	
	$scope.selectedColumn = sort.selectedColumn;
	$scope.changeSorting = sort.changeSorting;

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
controllerService.registerController("addReviewCommentController", AddReviewCommentController);
controllerService.registerController("fileContentController", FileContentController);
controllerService.registerController("showReviewCommentController", ShowReviewCommentController);


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