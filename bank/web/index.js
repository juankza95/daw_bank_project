IndexController.$inject = ['$scope', '$location', '$http'];
function IndexController($scope, $location, $http) {
    $scope.login = function () {
        var response = $http({
            method: 'GET',
            url: '/bank_api/api/login'
        });
        response.success(function (data, status, headers, config) {
            location.href="./main.html";
        }).error(function (data, status, headers, config) {
            alert("Hubo un error al hacer login");
        });
    };

}
loginApp.controller("IndexController", IndexController);