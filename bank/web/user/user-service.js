function UserService($http){
    this.list = function(){
        var response = $http(config = {
            method: 'GET',
            url: '/bank_api/api/user'
        });
        return response;
    };
    this.detail = function(userID){
       var response = $http({
            method: 'GET',
            url: '/bank_api/api/user/' + userID
        });
        return response;
    };
    this.insert = function(user){
          var response = $http({
            method: 'POST',
            url: '/bank_api/api/user/',
            data: user
            
        });
        return response;
    };
     this.delete = function(userID){
        var response = $http({
           method: 'DELETE',
           url: '/bank_api/api/user/' + userID
        });
        return response;
    };
    this.update = function(user){
        var response = $http({
           method: 'PUT',
           url: '/bank_api/api/user/' + user.userID,
           data: user
        });
        return response;
    };
}
UserService.$inject = ['$http'];
app.service("userService",UserService);