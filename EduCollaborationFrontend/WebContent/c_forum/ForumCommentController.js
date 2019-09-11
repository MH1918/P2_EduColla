myApp.controller('ForumCommentController', function($scope, $rootScope, $http, $location) {
	
	$scope.forum = {'forumId':0, 'forumName':'', 'forumContent':'', 'username':'', 'createDate':0, 'status':''};
	
	$scope.forumComments;
	
	$scope.forumComment = {'forumCommentId':0, 'forumId':0, 'forumCommentText':'', 'forumCommentDate':0, 'username':''};
	
	$scope.addComment = function() {
		
		console.log('~~~~~~I am in Add Forum Comment~~~~~~');
		
		$scope.forumComment.username=$rootScope.currentUser.username;
		$scope.forumComment.forumId=$rootScope.forumId;
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/addForumComment', $scope.forumComment)
		.then(function(response){
			showForum();
			showForumComments();
			alert('Comment Added');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function showForum() {
		
		console.log('~~~~~~Show Forum Info~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/getForum/'+$rootScope.forumId)
		.then(function(response) {
			$scope.forum=response.data;
			console.log($scope.forum);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.deleteComment = function(forumCommentId) {
		
		console.log('~~~~~~I am in Delete Comment~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteForumComment/'+forumCommentId)
		.then(function(response){
			showForum();
			showForumComments();
			alert('Comment Deleted');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showForum();
	
	function showForumComments() {
		
		console.log('~~~~~~Show Forum Comment Info~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/listForumComments/'+$rootScope.forumId)
		.then(function(response) {
			$scope.forumComments=response.data;
			console.log($scope.forumComments);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showForumComments();
});