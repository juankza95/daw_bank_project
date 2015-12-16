
UserListController.$inject = ['userService', '$scope'];
function UserListController(userService, $scope) {
    var response;
    response = userService.list();
    response.success(function (data, status, headers, config) {
        $scope.users = data;
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
}
app.controller("UserListController", UserListController);