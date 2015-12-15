
BankEntityListController.$inject = ['$scope','bankEntityService'];
function BankEntityListController($scope,bankEntityService) {
    var response;
     response = bankEntityService.list();
    response.success(function (data, status, headers, config) {
        $scope.bankEntities = data;
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
}
app.controller("BankEntityListController", BankEntityListController);