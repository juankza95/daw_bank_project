UserDetailController.$inject = ['userService', '$scope', '$routeParams']
function UserDetailController(userService, $scope, $routeParams) {
    var response;
    response = userService.detail($routeParams.userID);


    response.success(function (data, status, headers, config) {
        $scope.user = data;
    }).error(function (data, status, headers, config) {
        alert("Detail HTTP request failed. Status: " + status);
        console.log(data);
    });
}
app.controller("UserDetailController", UserDetailController);