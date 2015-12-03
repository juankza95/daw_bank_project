BankEntityDeleteController.$inject = ['$scope','$routeParams','bankEntityService','$location'];
function BankEntityDeleteController($scope,$routeParams,bankEntityService,$location){
    response = bankEntityService.detail($routeParams.bankEntityID).success(function (data, status, headers, config) {
        $scope.bankEntity = data;
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
    $scope.delete = function(){
        response = bankEntityService.delete($routeParams.bankEntityID).success(function (data, status, headers, config) {
        alert("Delete successful.");
        $location.path("/bankentity/list");
    }).error(function (data, status, headers, config) {
        alert("HTTP request failed. Status: " + status);
    });
        
    }
}
app.controller("BankEntityDeleteController",BankEntityDeleteController);