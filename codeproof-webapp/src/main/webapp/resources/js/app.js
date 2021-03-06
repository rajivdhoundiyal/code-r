var app = angular.module("login", ['ngResource', 'ui.router', 'pascalprecht.translate', 'ui.bootstrap', 'ui.bootstrap.tpls', 'ui.bootstrap.modal', 'ngSanitize']);

app.service('AppContext', function() {

	var user = '';
	var selectedReview = '';
	var files;

	this.addUser = function(user) {
		this.user = user;
	};

	this.getUser = function() {
		return this.user;
	};

	this.setSelectedReview = function(selectedReview) {
		this.selectedReview = selectedReview;
	};

	this.getSelectedReview = function() {
		return this.selectedReview;
	};

	this.setFiles = function(data) {
		this.files = data;
	};

	this.getFiles = function() {
		return this.files;
	}
});

app.service('ServiceLocater', function($injector) {

	var service = '';

	this.locate = function(service) {
		try {
			return $injector.get(service)
		} catch (e) {
			console.log('Error locating service...')
		}
	};
});

/*
 * app.config(function($routeProvider) { $routeProvider.when('/', { templateUrl :
 * 'index.html', controller : 'welcomeController' }).when('/login/success', {
 * templateUrl : 'login/success', controller : 'successController'
 * }).when('/success', { templateUrl : 'login/success', controller :
 * 'loginController' }).when('/admin', { templateUrl : 'admin.html', controller :
 * 'adminController' }).when('/dashboard/files', { templateUrl :
 * 'fileDetails.html', controller : 'fileController' }).otherwise({ redirectTo :
 * '/' }); });
 */

app.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("/");

	$stateProvider.state('/', {
		url : '/',
		templateUrl : 'index.html',
		controller : 'welcomeController'
	}).state('success', {
		
		views : {
			'' : {
				url : "/success",
				templateUrl : 'login/success',
			},
			"header@success" : {
				templateUrl : "navigate/headerpanel",
				controller : 'successController'
			},
			"content@success" : {
				templateUrl : "navigate/reviewdetails",
				controller : 'successController'
			}
		}
	}).state('success.files', {
		views : {
			"success" : {
				url : "/fileDetails",
				templateUrl : "fileDetails.html",
				controller : 'fileController'
			}
		}
	});

});

var Modal = function(scope, modal, templateUrl, controller, windowClass, size) {
	this.templateUrl = templateUrl;
	this.controller = controller;
	this.windowClass = windowClass;
	this.scope = scope;
	this.modal = modal;

	this.open = function(modalData) {
		modal.data = (modalData === undefined) ? reviewModel : modalData;

		modalInstance = modal.open({
			templateUrl : templateUrl,
			controller : controller,
			windowClass : windowClass,
			backdrop : false,
			size : 'sm',
			resolve : {
				data : function() {
					return modal.data;
				}
			}
		});
	};
};

var MesageModal = function(modal, title, messageTypeClass, message) {
	this.modal = modal;
	this.title = (title === undefined) ? messageTypeClass + "!!!" : title;
	this.messageTypeClass = messageTypeClass;
	this.message = message;

	this.open = function(modalData) {
		modal.data = (modalData === undefined) ? reviewModel : modalData;

		modalInstance = modal.open({
			template : '<div class="row"><div class="col-md-12"><div class="title_bar"><label class="textWhiteMedium bold" ng-bind=" \'' + this.title + '\' | translate "></label></div></div></div>' + '<div class="row"><div class="col-md-12"><div class="inner_container_pad_8px"><label class="text-danger h6" ng-bind=" \'' + this.message + '\' | translate "></label></div></div></div>' + '<div class="row"><div class="col-sm-offset-4 col-md-8"><div class="inner_container"><input type="button" name="add" style="margin-right: 10px;" class="button_type_medium" title="{{ \'TT_OK\' | translate}}" value="{{ \'CA_OK\' | translate }}" ng-click="ok()"></div></div></div>',
			controller : function($scope, $modalInstance) {
				$scope.ok = function() {
					$modalInstance.dismiss('cancel');
				}
			},
			windowClass : 'app-modal-window-review',
			backdrop : false,
			size : 'sm',
			resolve : {
				data : function() {
					return modal.data;
				}
			}
		});
	};
};

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
var Convertor = function() {

	this.pack = function pack(bytes) {
		var chars = [];
		for (var i = 0, n = bytes.length; i < n; ) {
			chars.push(((bytes[i++] & 0xff) << 8) | (bytes[i++] & 0xff));
		}
		return String.fromCharCode.apply(null, chars);
	}

	this.unpack = function unpack(str) {
		var bytes = [];
		for (var i = 0; i < str.length; i++) {
			var char = str.charCodeAt(i);
			bytes.push(char >>> 8);
			bytes.push(char & 0xFF);
		}
		return bytes;
	}
}
var Marker = function() {
	this.markChanges = function(data, numClass, classNoChange, classAdd, classDelete) {
		var fileData = data.split('\n');
		var val = '';
		var NUM_CSS = "float: left; width: auto;";
		var DIV_CONTAINER = "<div class='side_by_side'>";
		var CLOSING_DIV = "</div>";
		var start = new Date();
		$.each(fileData, function(index, value) {
			var ind = index + 1;
			if (value.indexOf('-') == 0) {
				val += DIV_CONTAINER + "<div class='" + numClass + "' ng-click='addReviewComments('" + ind + "')'><label>" + ind + ".</label></div> <div class='div_right'><label class='" + classDelete + " text_align_center'>" + value.replace("/\r/g", "").replace("/\n/g", "") + "</label></div>" + CLOSING_DIV;
			} else if (value.indexOf('+') == 0) {
				val += DIV_CONTAINER + "<div class='" + numClass + "' ng-click='addReviewComments('" + ind + "')'><label>" + ind + ".</label></div> <div class='div_right'><label class='" + classAdd + " text_align_center'>" + value.replace("/\r/g", "").replace("/\n/g", "") + "</label></div>" + CLOSING_DIV;
			} else {
				val += DIV_CONTAINER + "<div class='" + numClass + "' ng-click='addReviewComments('" + ind + "')'><label>" + ind + ".</label></div> <div class='div_right'><label class='" + classNoChange + " text_align_center'>" + value.replace("/\r/g", "").replace("/\n/g", "") + "</label></div>" + CLOSING_DIV;
			}
		});
		var end = new Date();
		return val;
	}
}
var Spinner = function() {
	this.getSpinner = function() {
		return "<div id='floatingBarsG'><div class='blockG' id='rotateG_01'></div><div class='blockG' id='rotateG_02'></div><div class='blockG' id='rotateG_03'></div><div class='blockG' id='rotateG_04'></div><div class='blockG' id='rotateG_05'></div><div class='blockG' id='rotateG_06'></div><div class='blockG' id='rotateG_07'></div><div class='blockG' id='rotateG_08'></div></div>"
	}
}

