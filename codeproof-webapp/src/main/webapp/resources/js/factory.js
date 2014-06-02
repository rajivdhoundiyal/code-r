
function RestURLFactory(angularModule) {
	this.registerService = function(serviceName, url, params, methods) {
		angularModule.factory(serviceName, function($resource) {
			return $resource(url, params, methods)
		});
	}
};

var restUrlFactory = new RestURLFactory(app);

restUrlFactory.registerService('LoginService', "", {}, {
	validate : {
		url : 'login/validate',
		method : 'POST',
		isArray : false
	}
});

restUrlFactory.registerService('GetReviewService',
		"dashboard/reviews/:username", {
			username : '@username'
		}, {
			getReviews : {
				method : 'GET',
				isArray : true
			}
		});

restUrlFactory.registerService('ReviewService', "review/:id", {
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
});
