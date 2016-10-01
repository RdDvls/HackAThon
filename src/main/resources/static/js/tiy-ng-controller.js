angular.module("HackAThon",[])
    .controller("HackAThonController",  function($scope,$http){
        console.log("Testing...");
            $scope.newUser = {};

             $scope.user;
            $scope.register = function(firstName, lastName, email, password){
                console.log("About to insert an user");
                $http.post("/register.json/?firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&password=" + password)
                //$http.post("/newUser.json/?firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&password=" + password)
                    .then(
                    function success(response){
                        console.log("Adding data to scope..");
                        console.log(response.data);
                         },
                    function failure(response){
                        console.log("Encountered an error");
                    });
        };
    });