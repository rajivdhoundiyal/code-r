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

restUrlFactory.registerService('DashboardService', "", "", {
	get : {
		method : 'GET',
		url : 'dashboard/reviews/:username',
		username : '@username',
		isArray : true
	},
	getReviews : {
		method : 'GET',
		url : 'dashboard/reviews/:username',
		username : '@username',
		isArray : true
	},
	getFilesByReviewCode : {
		method : 'GET',
		url : 'dashboard/reviews/:username/:reviewcode',
		username : '@username',
		reviewcode : '@reviewcode',
		isArray : true
	}
});

restUrlFactory.registerService('FileService', "", "", {
	getFileContent : {
		method : 'POST',
		url : 'file/:username/:reviewcode',
		username : '@username',
		reviewcode : '@reviewcode',
		isArray : false
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

restUrlFactory.registerService('ReviewCommentService', "comment/:id", {
	id : '@id'
}, {
	saveReview : {
		method : 'POST'
	},
	getReview : {
		method : 'GET',
		params : {
			id : '@id'
		}
	},
	updateReview : {
		method : 'PUT'
	},
	deleteReview : {
		method : 'DELETE'
	},
	getReviews : {
		method : 'GET',
		url : 'comment',
		params : {
			reviewcodeid : '@reivewcodeid',
			filename : '@filename'
		},
		isArray : true
	}
});

restUrlFactory.registerService('BlogEntryService', "blog/:id", {
	id : '@id'
}, {
	saveBlog : {
		method : 'POST'
	},
	getBlog : {
		method : 'GET'
	},
	updateBlog : {
		method : 'PUT',
		params : {
			id : '@id'
		}
	},
	deleteBlog : {
		method : 'DELETE',
		params : {
			id : '@id'
		}
	},
	getBlogsByReviewCodeAndFileName : {
		method : 'GET',
		url : 'blog/file/:reivewcodeid',
		reviewcodeid : '@reivewcodeid',
		params : {
			filename : '@filename'
		},
		isArray : true
	},
	getBlogsByReviewCode : {
		method : 'GET',
		url : 'blog/review/:reivewcodeid',
		reivewcodeid : '@reivewcodeid',
		isArray : true
	}
});
