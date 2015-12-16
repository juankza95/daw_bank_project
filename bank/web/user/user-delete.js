UserDeleteController.$inject = ['userService','$scope','$routeParams','$location'];
function UserDeleteController(userService,$scope,$routeParams,$location){
    var response = userService.detail($routeParams.userID).success(function (data, status, headers, config) {
        $scope.user = data;
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
    $scope.delete = function(){
        response = userService.delete($routeParams.userID).success(function (data, status, headers, config) {
        alert("Delete successful.");
        $location.path("/user/list");
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
        
    };
}
app.controller("UserDeleteController",UserDeleteController);