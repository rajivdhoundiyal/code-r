var app = angular.module("login", [ 'ngResource', 'ngRoute',
		'pascalprecht.translate', 'ui.bootstrap', 'ui.bootstrap.tpls',
		'ui.bootstrap.modal' ]);

app.service('userService', function() {

	var user = '';

	this.addUser = function(user) {
		this.user = user;
	};

	this.getUser = function() {
		return this.user;
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