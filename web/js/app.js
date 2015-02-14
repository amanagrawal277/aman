var emp = angular.module('emp',['ngRoute']);

/* Wrtie your service here x*/

emp.config(function ($routeProvider) {
 $routeProvider.when('/', {
  templateUrl: "pages/Employee/employee.html",
  controller: 'employeeController'
 })
  .when('/project', {
   templateUrl: "pages/Project/viewProject.html",
   controller: 'projectController'
  })
  .when('/client', {
   templateUrl: "pages/Client/viewClient.html",
   controller: 'clientController'
  })  
  .when('/contact', {
   templateUrl: "pages/Contact/contact.html",
   controller: 'contactController'
  })  
  .otherwise({redirectTo: '/'});
});

emp.controller('employeeController',function($scope){
 $scope.employees = [{
    "id":"1",
    "name":"abc@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  },
  {
    "id":"2",
    "name":"amir@gmail.com",
  }];
});
emp.controller('projectController',function($scope){
 $scope.message = "hello world";
});
emp.controller('clientController',function($scope){
 $scope.message = "hello world";
});
emp.controller('contactController',function($scope){
 $scope.employees = "hello world";
});