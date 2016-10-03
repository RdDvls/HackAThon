angular.module('HackAThon', [])
   .controller('HackAThonController', function($scope, $http, $timeout) {
        console.log("Running...")
        $scope.register = function(email, firstName, lastName, password) {
            console.log("In register function in ng controller");

            //Make a container
            var newUser = {
                email: email,
                firstName: firstName,
                lastName: lastName,
                password: password,
            }
            console.log("Container we're about to send: " + newUser.email + " " + newUser.firstName + " " + newUser.lastName + " " + newUser.password);

            $http.post("/newUser.json", newUser)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.loginContainer = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.login = function(loginEmail, loginPassword) {
            console.log("In login function in ng controller");

            //Make a container
            var returningUser = {
                email: loginEmail,
                password: loginPassword
            }

            $http.post("/login.json", returningUser)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.loginContainerForLogin = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.createNewEvent = function(newEventName, newEventLocation, newEventDescription) {
             console.log("In createNewEvent function in ng controller");

             //Make a container
             var newEvent = {
                 name: newEventName,
                 location: newEventLocation,
                 description: newEventDescription,
             }

             $http.post("/addEvent.json", newEvent)
                 .then(
                     function successCallback(response) {
                         console.log(response.data);
                         console.log("Adding data to scope");
                         $scope.allEvents = response.data;
                     },
                     function errorCallback(response) {
                         console.log("Unable to get data...");
                     });
         };

        $scope.joinEvent = function(myUserId, eventIWantToJoinId) {
             console.log("In joinEvent function in ng controller");

             //Make a container
             var newUserEvent = {
                  userId: myUserId,
                  eventId: eventIWantToJoinId
             }

             $http.post("/joinEvent.json", newUserEvent)
                  .then(
                     function successCallback(response) {
                         console.log(response.data);
                         console.log("Adding data to scope");
                         // Returns a list of attendees
                         $scope.eventAttendees = response.data;
                     },
                     function errorCallback(response) {
                         console.log("Unable to get data...");
                     });
        };

        $scope.viewUserInfo = function(requesterUserId, requesteeUserId) {
             console.log("In viewUserInfo function in ng controller");

             //Make a container
             var idContainer = {
                  userId: requesterUserId,
                  friendId: requesteeUserId
             }

             $http.post("/viewUserInfo.json", idContainer)
                  .then(
                     function successCallback(response) {
                         console.log(response.data);
                         console.log("Adding data to scope");
                         // Returns container with error or user
                         $scope.friendContainer = response.data;
                     },
                     function errorCallback(response) {
                         console.log("Unable to get data...");
                     });
        };
$scope.testLogin = function() {
            console.log("In test login function in ng controller");

            //Make a container
                    $http.get("/testLogin.json?email=" + $scope.loginEmail + "&password=" + $scope.loginPassword + "&firstName=" + $scope.loginFirstName + "&lastName=" + $scope.loginLastName)
                    .then(
                        function success(response) {
                            console.log(response.data);
                            console.log("The testlogin button worked");

                            $scope.result = {};

                            alert("About to add solution on the scope");

                            $scope.result = response.data;


                        },
                        function error(response) {
                            console.log("unable to add info on the scope");
                        });
        };
var email = $scope.loginEmail;
var password = $scope.loginPassword;
var firstName = $scope.loginFirstName;
var lastName = $scope.loginLastName;
    console.log("Page loaded!");

    });














