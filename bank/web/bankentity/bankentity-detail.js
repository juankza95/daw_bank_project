BankEntityDetailController.$inject = ['$routeParams','$scope','bankEntityService'];
function BankEntityDetailController($routeParams,$scope,bankEntityService){
    var response;
response = bankEntityService.detail($routeParams.bankEntityID);


    response.success(function (data, status, headers, config) {
        $scope.bankEntity = data;
        var creationDate = new Date(data.creationDate);
        $scope.bankEntity.creationDate = creationDate.getFullYear() + "-" + (creationDate.getMonth()+1) + "-" + creationDate.getDate();
    }).error(function (data, status, headers, config) {
        alert("Detail HTTP request failed. Status: " + status);
        console.log(data);
    });
    
}
app.controller("BankEntityDetailController",BankEntityDetailController);
