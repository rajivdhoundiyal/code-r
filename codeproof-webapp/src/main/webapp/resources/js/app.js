var app = angular.module("login", [ 'ngResource', 'ui.router',
		'pascalprecht.translate', 'ui.bootstrap', 'ui.bootstrap.tpls',
		'ui.bootstrap.modal' ]);

app.service('UserService', function() {

	var user = '';

	this.addUser = function(user) {
		this.user = user;
	};

	this.getUser = function() {
		return this.user;
	};
});

app.factory('FileFactory', function() {

	var files = {};

	return {
		setFiles : function(data) {
			this.files = data;
		},
		getFiles : function() {
			return this.files;
		}
	}
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
		url : "/success",
		templateUrl : 'login/success',
		controller : 'successController'
	}).state('success.files', {
		views : {
			"success" : {
				url : "/fileDetails",
				templateUrl : "fileDetails.html",
				controller : 'fileController'
			}
		}
	}).state('successq', {
		url : "/successq",
		templateUrl : 'login/success',
		controller : 'loginController'
	});

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