UserInsertController.$inject = ['userService', '$scope'];
function UserInsertController(userService, $scope) {
    $scope.user = {};

    $scope.insert = function () {
        var response = userService.insert($scope.user);
        response.success(function (data, status, headers, config) {
            alert("OK");
        }).error(function (data, status, headers, config) {
            if (status === 400) {
                $scope.errors = data;
            } else {
                alert("Ha fallado la petición HTTP. Estado HTTP: " + status);
            }
        });
    };

}
app.controller("UserInsertController", UserInsertController);