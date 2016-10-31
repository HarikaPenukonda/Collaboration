var app = angular.module('myApp',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/',{
		templateUrl : 'c_home/home.html',
		controller : 'HomeController'
	})
	
	.when('/job_opportunities',{
		templateUrl : 'c_job/job.html',
		controller : 'JobController'
	})
	
	.when('/manageUser',{
		templateUrl : 'c_admin/manage_users.html',
		controller : 'AdminController'
	})
	
	.when('/event',{
		templateUrl : 'c_event/event.html',
		controller : 'EventController'
	})
	.when('/about',{
		templateUrl : 'c_about/about.html',
		controller : 'AboutController'
	})
	.when('/login',{
		templateUrl : 'c_user/login.html',
		controller : 'UserController'
	})
	.when('/register',{
		templateUrl : 'c_user/register.html',
		controller : 'UserController'
	})
	.when('/create_blog',{
		templateUrl : 'c_blog/create_blog.html',
		controller : 'BlogController'
	})
	.when('/list_blog',{
		templateUrl : 'c_blog/list_blog.html',
		controller : 'BlogController'
	})
	.when('/view_blog',{
		templateUrl : 'c_blog/view_blog.html',
		controller : 'BlogController'
	})
	.when('/add_friend',{
		templateUrl : 'c_friend/add_friend.html',
		controller : 'FriendController'
	})
	.when('/search_friend',{
		templateUrl : 'c_friend/search_friend.html',
		controller : 'FriendController'
	})
	.when('/view_friend',{
		templateUrl : 'c_friend/view_friend.html',
		controller : 'FriendController'
	})
	.when('/create_forum',{
		templateUrl : 'c_forum/create_forum.html',
		controller : 'ForumController'
	})
	.when('/list_forum',{
		templateUrl : 'c_forum/list_forum.html',
		controller : 'ForumController'
	})
	.when('/view_forum',{
		templateUrl : 'c_forum/view_forum.html',
		controller : 'ForumController'
	})
	
	.otherwise({redirectTo: '/'});
	
	
})