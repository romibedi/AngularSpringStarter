angular.module('ngBoilerplate.account', [ 'ui.router', 'ngResource' ])

.config(["$stateProvider", function($stateProvider) {
	$stateProvider.state('login', {
		url : '/login',
		views : {
			'main' : {
				templateUrl : 'account/login.tpl.html',
				controller : 'LoginCtrl'
			}
		},
		data : {
			pageTitle : 'Login'
		}
	})

	.state('register', {
		url : '/register',
		views : {
			'main' : {
				templateUrl : 'account/register.tpl.html',
				controller : 'RegisterCtrl'
			}
		},
		data : {
			pageTitle : 'register'
		}
	});
}])

.factory('sessionService', function() {
	var session = {};

	session.login = function(data) {
		localStorage.setItem("session", data);
	};

	session.logout = function() {
		localStorage.removeItem("session");
	};
	session.isLoggedin = function() {
		return localStorage.getItem("session") !== null;
	};

	return session;
})

.factory('accountService', ["$resource", function($resource) {
	var service = {};
	service.register = function(account, success, failure) {
		var Account = $resource("/basic-hateos/rest/accounts");
		Account.save({}, account, success, failure);
	};

	service.userExists = function(account, success, failure){
		var Account = $resource("/basic-hateos/rest/accounts");
		var data = Account.get({name:account.name}, function(){
			var accounts = data.accounts;
			if (accounts.length !== 0 ){
				success(accounts[0]);
			}else{
				failure();
			}
		},
		failure);
	};
	
	return service;
}])

.controller("LoginCtrl", ["$scope", "sessionService", "$state", "accountService", function($scope, sessionService, $state, accountService) {
	$scope.login = function() {
		accountService.userExists($scope.account, function(account){
			sessionService.login(account);
			$state.go("home");	
		},function(){
			alert("error logging in");
		});
		
		
	};

}])

.controller("RegisterCtrl",
		["$scope", "sessionService", "$state", "accountService", function($scope, sessionService, $state, accountService) {
			$scope.register = function() {
				accountService.register($scope.account, function(returnedData) {
					sessionService.login(returnedData);
					$state.go("home");
				}, function() {
					alert("error registering user");
				});

				
		
			};

		}]);
