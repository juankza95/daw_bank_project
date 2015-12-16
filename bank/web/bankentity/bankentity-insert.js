BankEntityInsertController.$inject = ['$scope', '$location', 'bankEntityService'];
function BankEntityInsertController($scope, $location, bankEntityService) {
    $('#datepicker').datepicker({dateFormat: 'yy-mm-dd'});
    $scope.bankEntity = {};

    $scope.insert = function () {
     //   $scope.bankEntity.creationDate = new Date($scope.bankEntity.creationDate);
        
        var response = bankEntityService.insert($scope.bankEntity);
        response.success(function (data, status, headers, config) {
            alert("Insert successful");
            $location.path('/bankentity/list');
            
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
