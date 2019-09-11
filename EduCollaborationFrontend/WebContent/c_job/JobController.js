myApp.controller('JobController', function($scope, $rootScope, $http, $location, $cookieStore) {
	
	$scope.job={'jobId':0, 'designation':'', 'jobDesc':'', 'companyName':'', 'skills':'', 'ctc':0, 'experienceYear':0, 'lastDateToApply':''};
	
	$scope.jobs;
	
	$scope.publishJob = function() {
		
		console.log('~~~~~~Publishing the Job~~~~~~');
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/publishJob', $scope.job)
		.then(function(response){
			alert('Job Published');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function showAllJobs() {
		
		console.log('~~~~~~Show All Jobs Method~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/showAllJobs')
		.then(function(response) {
			$scope.jobs=response.data;
			console.log($scope.jobs);
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.deleteJob = function(jobId) {
		
		console.log('~~~~~~I am Deleting the Job~~~~~~');
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/deleteJob/'+jobId)
		.then(function(response){
			showAllJobs();
			alert('Job Deleted');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	function jobDetail() {
		
		$http.get('http://localhost:8092/EduCollaborationMiddleware/getJob/'+$rootScope.jobId1)
		.then(function(response) {
			$scope.job=response.data;
		},
			function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	$scope.editJob = function(jobId) {
		
		console.log('~~~~~~I am Editing the Job~~~~~~');
		
		$rootScope.jobId1=jobId;
		$location.path('/updateJob');
	}
	
	$scope.updateJob = function() {
		
		console.log('~~~~~~Updating the Job~~~~~~');
		
		$http.post('http://localhost:8092/EduCollaborationMiddleware/updateJob', $scope.job)
		.then(function(response){
			alert('Job Updateded');
			$location.path('/showJob');
		},
		function(errorresponse) {
//			alert('Error Occured');
		});
	}
	
	jobDetail();
	showAllJobs();
});