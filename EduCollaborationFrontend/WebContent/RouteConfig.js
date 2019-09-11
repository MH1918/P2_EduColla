var myApp = angular.module('myApp', ['ngRoute', 'ngCookies']);

myApp.config(function($routeProvider) {
	$routeProvider.when("/login",{templateUrl:"c_user/Login.html"})
	.when("/register",{templateUrl:"c_user/Register.html"})
	.when("/aboutUs",{templateUrl:"c_user/AboutUs.html"})
	.when("/contactUs",{templateUrl:"c_user/ContactUs.html"})
	.when("/addBlog",{templateUrl:"c_blog/AddBlog.html"})
	.when("/showBlog",{templateUrl:"c_blog/ShowBlog.html"})
	.when("/adminBlog",{templateUrl:"c_blog/AdminBlog.html"})
	.when("/blogComment",{templateUrl:"c_blog/BlogComment.html"})
	.when("/updateBlog",{templateUrl:"c_blog/UpdateBlog.html"})
	.when("/publishJob",{templateUrl:"c_job/PublishJob.html"})
	.when("/showJob",{templateUrl:"c_job/ShowJob.html"})
	.when("/manageJob",{templateUrl:"c_job/ManageJob.html"})
	.when("/updateJob",{templateUrl:"c_job/UpdateJob.html"})
	.when("/profileUpdate",{templateUrl:"c_user/ProfilePicture.html"})
	.when("/friend",{templateUrl:"c_friend/Friend.html"})
	.when("/addForum",{templateUrl:"c_forum/AddForum.html"})
	.when("/showForum",{templateUrl:"c_forum/ShowForum.html"})
	.when("/updateForum",{templateUrl:"c_forum/UpdateForum.html"})
	.when("/adminForum",{templateUrl:"c_forum/AdminForum.html"})
	.when("/forumComment",{templateUrl:"c_forum/ForumComment.html"})
	.when("/chat",{templateUrl:"c_chat/Chat.html"})
});

myApp.run(function($rootScope, $cookieStore) {
	if($rootScope.currentUser==undefined) {
		$rootScope.currentUser=$cookieStore.get('userDetail');
	}
});