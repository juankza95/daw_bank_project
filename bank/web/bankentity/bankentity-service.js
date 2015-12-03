function BankEntityService($http){
    this.list = function(){
          var response = $http(config = {
            method: 'GET',
            url: '/bank_api/api/bankentity'
        });
        return response;
    };
    
    this.detail = function(bankEntityID){
        var response = $http({
            method: 'GET',
            url: '/bank_api/api/bankentity/' + bankEntityID
        });
        return response;
    };
    this.insert = function(bankEntity){
        var response = $http({
            method: 'POST',
            url: '/bank_api/api/bankentity/',
            data: bankEntity
            
        });
        return response;
    };
    this.delete = function(bankEntityID){
        var response = $http({
           method: 'DELETE',
           url: '/bank_api/api/bankentity/' + bankEntityID
        });
        return response;
    };
    this.update = function(bankEntity){
        var response = $http({
           method: 'PUT',
           url: '/bank_api/api/bankentity/' + bankEntity.bankEntityID,
           data: bankEntity
        });
        return response;
    };
}
BankEntityService.$inject = ['$http'];
app.service("bankEntityService",BankEntityService);