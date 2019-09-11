myApp.controller('FriendController', function($scope, $rootScope, $http, $location, $cookieStore) {
	
	$scope.friend = {'friendId':0, 'username':'', 'friendUsername':'', 'status':''};
	
	$scope.friendList;
	
	$scope.pendingFriendList;
	
	$scope.suggestedFriendList;
	
	function showFriendList() {
		
		console.log('~~~~~~ShowFriendList Method~~~~~~');
		
		username = $rootScope.currentUser.username;
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/showFriendList/'+username)
		.then(function(response) {
			$scope.friendList=response.data;
			console.log($scope.friendList);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showFriendList();
	
	function showPendingFriendList() {
		
		console.log('~~~~~~ShowPendingFriendList Method~~~~~~');
		
		username = $rootScope.currentUser.username;
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/showPendingFriendList/'+username)
		.then(function(response) {
			$scope.pendingFriendList=response.data;
			console.log($scope.pendingFriendList);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showPendingFriendList();
	
	function showSuggestedFriendList() {
		
		console.log('~~~~~~ShowSuggestedFriendList Method~~~~~~');
		
		username = $rootScope.currentUser.username;
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/showSuggestedFriendList/'+username)
		.then(function(response) {
			$scope.suggestedFriendList=response.data;
			console.log($scope.suggestedFriendList);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	showSuggestedFriendList();
	
	$scope.sendFriendRequest = function(username) {
		
		console.log('~~~~~~Sending Friend Request~~~~~~');
		
		$scope.friend.username = $rootScope.currentUser.username;
		$scope.friend.friendUsername = username;
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/sendFriendRequest', $scope.friend)
		.then(function(response) {
			console.log('~~~~~~Friend Request Sent~~~~~~');
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
			alert('Friend Request Sent');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.accept = function(friendId) {
		
		console.log('~~~~~~Accepting Friend Request~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/acceptFriendRequest/'+friendId)
		.then(function(response) {
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
			alert('Friend Request Accepted');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.reject = function(friendId) {
		
		console.log("~~~~~~Rejecting Friend's Request~~~~~~");
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteFriendRequest/'+friendId)
		.then(function(response) {
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
			alert('Friend Request Rejected');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.unfriend = function(friendId) {
		
		console.log('~~~~~~Unfriending a Friend~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteFriendRequest/'+friendId)
		.then(function(response) {
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
			alert('Friend is Unfriended');
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
});