app.config(['$routeProvider', function ($routeProvider) {
      $routeProvider.when('/bankentity/list', {
            templateUrl: "./bankentity/bankentity-list.html",
            controller: "BankEntityListController"
        });
        $routeProvider.when('/bankentity/detail/:bankEntityID', {
            templateUrl: "./bankentity/bankentity-detail.html",
            controller: "BankEntityDetailController"
        });
         $routeProvider.when('/bankentity/insert', {
            templateUrl: "./bankentity/bankentity-insert.html",
            controller: "BankEntityInsertController"
        });
         $routeProvider.when('/bankentity/delete/:bankEntityID', {
            templateUrl: "./bankentity/bankentity-delete.html",
            controller: "BankEntityDeleteController"
        });
         $routeProvider.when('/bankentity/update/:bankEntityID', {
            templateUrl: "./bankentity/bankentity-update.html",
            controller: "BankEntityUpdateController"
        });
        
    }]);