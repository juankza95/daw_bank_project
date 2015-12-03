loginApp.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: "login.html"
        });
        $routeProvider.otherwise( {
            redirectTo:"/"
        });        
        
    }]);
