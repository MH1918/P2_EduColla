myApp.controller('BlogCommentController', function($scope, $rootScope, $http, $location) {
	
	$scope.blog={'blogId':0, 'blogName':'', 'blogContent':'', 'username':'', 'createDate':'', 'status':'', 'likes':0, 'dislikes':0};
	
	$scope.blogComments;
	
	$scope.blogComment={'blogCommentId':0, 'blogId':0, 'blogCommentText':'', 'blogCommentDate':'', 'username':''};
	
	$scope.addComment = function() {
		
		console.log('~~~~~~I am in Add Blog Comment~~~~~~');
		
		$scope.blogComment.username=$rootScope.currentUser.username;
		$scope.blogComment.blogId=$rootScope.blogId;
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/addBlogComment', $scope.blogComment)
		.then(function(response){
			showBlog();
			showBlogComments();
			alert('Comment Added');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function showBlog() {
		
		console.log('~~~~~~Show Blog Info~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/getBlog/'+$rootScope.blogId)
		.then(function(response) {
			$scope.blog=response.data;
			console.log($scope.blog);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.deleteComment = function(blogCommentId) {
		
		console.log('~~~~~~I am in Delete Comment~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteBlogComment/'+blogCommentId)
		.then(function(response){
			showBlog();
			showBlogComments();
			alert('Comment Deleted');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showBlog();
	
	function showBlogComments() {
		
		console.log('~~~~~~Show Blog Comment Info~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/listBlogComments/'+$rootScope.blogId)
		.then(function(response) {
			$scope.blogComments=response.data;
			console.log($scope.blogComments);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showBlogComments();
});