BankEntityUpdateController.$inject = ['$scope', '$location', 'bankEntityService', '$routeParams'];
function BankEntityUpdateController($scope, $location, bankEntityService, $routeParams) {
    $('#datepicker').datepicker({dateFormat: 'yy-mm-dd'});
    var response = bankEntityService.detail($routeParams.bankEntityID).success(function (data, status, headers, config) {
        $scope.bankEntity = data;
        var creationDate = new Date(data.creationDate);
        $scope.bankEntity.creationDate = creationDate.getFullYear() + "-" + (creationDate.getMonth()+1) + "-" + creationDate.getDate();
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
    $scope.update = function () {
       var response = bankEntityService.update($scope.bankEntity).success(function (data, status, headers, config) {
            alert("Update successful.");
            $location.path('/bankentity/detail/' + $scope.bankEntity.bankEntityID);
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