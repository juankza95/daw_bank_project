UserUpdateController.$inject = ['$scope','userService','$routeParams'];
function UserUpdateController($scope,userService,$routeParams){
       response = userService.detail($routeParams.userID).success(function (data, status, headers, config) {
        $scope.user = data;
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
        console.log(data);
    });
    $scope.update = function () {
        response = userService.update($scope.user).success(function (data, status, headers, config) {
            alert("Update succeeded.");

        }).error(function (data, status, headers, config) {
            if (status === 400) {
                $scope.errors = data;
            } else {
                alert("HTTP request failed. Status: " + status);
            }
            console.log(data);
        });
    }
}
app.controller("UserUpdateController",UserUpdateController);
