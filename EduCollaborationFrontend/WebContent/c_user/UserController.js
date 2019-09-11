myApp.controller('UserController', function($scope, $rootScope, $http, $location, $cookieStore) {
	
	$scope.user={'username':'', 'password':'', 'name':'', 'emailId':'', 'role':'', 'status':'', 'isOnline':''};
	
	$scope.register = function() {
		
		console.log('~~~~~~Registration Details~~~~~~');
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/registerUser', $scope.user)
		.then(function(response){
			console.log('registered');
			$location.path("/login");
		},
		function(errorresponse) {
			alert('Error Occured');
		});
	}
	
	$scope.loginCheck = function() {
		
		console.log('~~~~~~Login Information~~~~~~');
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/checkUser', $scope.user)
		.then(function(response){
			$scope.user1 = response.data;
			$rootScope.currentUser = response.data;
			console.log('Root Scope Data'+$rootScope.currentUser);
			
			$cookieStore.put('userDetail', response.data);
			
			$location.path('/userHome');
		},
		function(errorresponse) {
			alert('Error Occured');
		});
	}
	
	$scope.logout=function() {
		console.log('~~~~~~Logout Information~~~~~~');
		$cookieStore.remove('userDetail');
		delete $rootScope.currentUser;
		alert("User has Logged Out");
		$location.path("/login");
	}
});