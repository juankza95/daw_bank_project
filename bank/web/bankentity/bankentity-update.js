BankEntityUpdateController.$inject = ['$scope','bankEntityService','$routeParams'];
function BankEntityUpdateController($scope,bankEntityService,$routeParams){
    response = bankEntityService.detail($routeParams.bankEntityID).success(function (data, status, headers, config) {
            $scope.bankEntity = data;
        }).error(function (data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
            console.log(data);
        });
        $scope.update = function(){
            response = bankEntityService.update($scope.bankEntity).success(function (data, status, headers, config) {
            alert("TURN DOWN FOR WHAT. PIPOPIPOPI");
            
        }).error(function (data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
            console.log(data);
        });
        }
    
    
}
app.controller("BankEntityUpdateController",BankEntityUpdateController);