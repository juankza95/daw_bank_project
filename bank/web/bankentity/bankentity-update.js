BankEntityUpdateController.$inject = ['$scope', 'bankEntityService', '$routeParams'];
function BankEntityUpdateController($scope, bankEntityService, $routeParams) {
    response = bankEntityService.detail($routeParams.bankEntityID).success(function (data, status, headers, config) {
        $scope.bankEntity = data;
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
        console.log(data);
    });
    $scope.update = function () {
        response = bankEntityService.update($scope.bankEntity).success(function (data, status, headers, config) {
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
app.controller("BankEntityUpdateController", BankEntityUpdateController);