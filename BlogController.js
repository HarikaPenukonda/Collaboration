/**
 * 
 */

'use strict';

app.controller('BlogController',[
	'$scope',
	'BlogService',
	'$location',
	'$rootScope',
	function($scope,BlogService,$location,$rootScope){
	var self = this;
	self.blog={
			blog_id : '',
			title : '',
			description : '',
			user_id : '',
			dateTime : '',
			status : '',
			reason : '',
			errorMessage :''
			};
	self.blogs = [];
	
	
	self.getSelectedBlog = getBlog
	
	
	function getBlog (blog_id){
		console.log("getting blog: "+blog_id)
		BlogService.getBlog(id)
		.then(
				function(d){
					self.blog = d;
					$location.path("/view_blog");
					
				},
				function(errResponse){
					console.error('Error while fetching Blogs');
				}
			);
	};
		
	self.fetchAllBlogs = function(){
		BlogService.fetchAllBlogs()
		.then(
		function(d){
			self.blogs = d;
		},		
		function(errResponse){
			console.error('Error while fetching the data');
			
		});
	};
	
	self.createBlog = function(blog){
		BlogService.createBlog(blog)
		.then(
		self.fetchAllBlogs,		
		function(errResponse){
			console.error('Error while creating the data');
			
		});
	};
	
	self.updateBlog = function(blog,blog_id){
		BlogService.updateBlog(blog,blog_id)
		.then(
				self.fetchAllBlogs,		
				function(errResponse){
			console.error('Error while updating the data');
			
		});
	};
	self.deleteBlog = function(id){
		BlogService.deleteBlog(id)
		.then(
				self.fetchAllBlogs,		
				function(errResponse){
			console.error('Error while fetching the data');
			
		});
	};
	
	self.fetchAllBlogs();
	self.submit = function(){
		if(self.blog.id===null){
			console.log('Saving New Blog',self.blog);
			self.blog.user_ID = $rootScope.currentUser.id
			self.createBlog(self.blog);
		}else
			{
			console.log('Saving New Blog',self.blog);
			self.createBlog(self.blog);
			}
		self.reset();
		};
}
	]);