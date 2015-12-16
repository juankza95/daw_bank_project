app.config(['$routeProvider', function ($routeProvider) {
      $routeProvider.when('/user/list', {
            templateUrl: "./user/user-list.html",
            controller: "UserListController"
        });
         $routeProvider.when('/user/detail/:userID', {
            templateUrl: "./user/user-detail.html",
            controller: "UserDetailController"
        });
        $routeProvider.when('/user/insert', {
            templateUrl: "./user/user-insert.html",
            controller: "UserInsertController"
        });
          $routeProvider.when('/user/delete/:userID', {
            templateUrl: "./user/user-delete.html",
            controller: "UserDeleteController"
        });
         $routeProvider.when('/user/update/:userID', {
            templateUrl: "./user/user-update.html",
            controller: "UserUpdateController"
        });
    }]);