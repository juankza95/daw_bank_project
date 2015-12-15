BankEntityInsertController.$inject = ['$scope', 'bankEntityService'];
function BankEntityInsertController($scope, bankEntityService) {
    $scope.bankEntity = {};

    $scope.insert = function () {
        $scope.bankEntity.creationDate = new Date($scope.bankEntity.creationDate);
        
        var response = bankEntityService.insert($scope.bankEntity);
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
app.controller("BankEntityInsertController", BankEntityInsertController);
