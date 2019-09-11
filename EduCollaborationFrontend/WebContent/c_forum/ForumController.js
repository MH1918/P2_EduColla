myApp.controller('ForumController', function($scope, $rootScope, $http, $location, $cookieStore) {
	
	$scope.forum = {'forumId':0, 'forumName':'', 'forumContent':'', 'username':'', 'createDate':0, 'status':''};
	
	$scope.forumData;
	
	$scope.addForum = function() {
		
		console.log('~~~~~~I am Adding a Forum~~~~~~');
		$scope.forum.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8092/EduCollaborationMiddleware/addForum', $scope.forum)
		.then(function(response){
			alert('Forum Added');
			$scope.forum.forumName='';
			$scope.forum.forumContent='';
			$location.path('/addForum');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function listForum() {
		
		console.log('~~~~~~List Forum Method~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/showAllForums')
		.then(function(response) {
			$scope.forumData=response.data;
			console.log($scope.forumData);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.approve = function(forumId) {
		console.log('~~~~~~Approving the Forum~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/approveForum/'+forumId)
		.then(function(response) {
			listForum();
			alert('Forum is Approved');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.reject = function(forumId) {
		console.log('~~~~~~Rejecting the Forum~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/rejectForum/'+forumId)
		.then(function(response) {
			listForum();
			alert('Forum is Rejected');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.deleteForum = function(forumId) {
		
		console.log('~~~~~~I am Deleting this Forum~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteForum/'+forumId)
		.then(function(response){
			listForum();
			alert('Forum Deleted');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function forumDetail() {
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/getForum/'+$rootScope.forumId1)
		.then(function(response) {
			$scope.forum=response.data;
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.editForum = function(forumId) {
		
		console.log('~~~~~~I am Editing this Forum~~~~~~');
		
		$rootScope.forumId1=forumId;
		$location.path('/updateForum');
	}
	
	$scope.updateForum = function() {
		
		console.log('~~~~~~Updating the Forum~~~~~~');
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/updateForum', $scope.forum)
		.then(function(response){
			alert('Forum Updateded');
			$location.path('/showForum');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	forumDetail();
	
	$scope.showComment=function(forumId) {
		console.log('~~~~~~Showing Comments~~~~~~');
		$rootScope.forumId=forumId;
		$location.path('/forumComment');
	}
	
	listForum();
});