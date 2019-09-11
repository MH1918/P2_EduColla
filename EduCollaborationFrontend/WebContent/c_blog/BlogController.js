myApp.controller('BlogController', function($scope, $rootScope, $http, $location, $cookieStore) {
	
	$scope.blog={'blogId':0, 'blogName':'', 'blogContent':'', 'username':'', 'createDate':'', 'status':'', 'likes':0, 'dislikes':0};
	
	$scope.blogData;
	
	$scope.addBlog = function() {
		
		console.log('~~~~~~I am in Add Blog~~~~~~');
		$scope.blog.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8092/EduCollaborationMiddleware/addBlog', $scope.blog)
		.then(function(response){
			alert('Blog Added');
			$scope.blog.blogName='';
			$scope.blog.blogContent='';
			$location.path('/addBlog');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function listBlog() {
		
		console.log('~~~~~~List Blog Method~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/showAllBlogs')
		.then(function(response) {
			$scope.blogData=response.data;
			console.log($scope.blogData);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.incrementLikes = function(blogId) {
		console.log('~~~~~~Incrementing Likes~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/incrementLikes/'+blogId)
		.then(function(response) {
			listBlog();
			alert('Like+1');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.incrementDislikes = function(blogId) {
		console.log('~~~~~~Incrementing Dislikes~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/incrementDislikes/'+blogId)
		.then(function(response) {
			listBlog();
			alert('Dislike+1');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.approve = function(blogId) {
		console.log('~~~~~~Approving the Blog~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/approveBlog/'+blogId)
		.then(function(response) {
			listBlog();
			alert('Blog is Approved');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.reject = function(blogId) {
		console.log('~~~~~~Rejecting the Blog~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/rejectBlog/'+blogId)
		.then(function(response) {
			listBlog();
			alert('Blog is Rejected');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.deleteBlog = function(blogId) {
		
		console.log('~~~~~~I am in Delete Blog~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteBlog/'+blogId)
		.then(function(response){
			listBlog();
			alert('Blog Deleted');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function blogDetail() {
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/getBlog/'+$rootScope.blogId1)
		.then(function(response) {
			$scope.blog=response.data;
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.editBlog = function(blogId) {
		
		console.log('~~~~~~I am Editing this Blog~~~~~~');
		
		$rootScope.blogId1=blogId;
		$location.path('/updateBlog');
	}
	
	$scope.updateBlog = function() {
		
		console.log('~~~~~~Updating the Blog~~~~~~');
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/updateBlog', $scope.blog)
		.then(function(response){
			alert('Blog Updateded');
			$location.path('/showBlog');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	blogDetail();
	
	$scope.showComment=function(blogId) {
		console.log('~~~~~~Showing Comments~~~~~~');
		$rootScope.blogId=blogId;
		$location.path('/blogComment');
	}
	
	listBlog();
});