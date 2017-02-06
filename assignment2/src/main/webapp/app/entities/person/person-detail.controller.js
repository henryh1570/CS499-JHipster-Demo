(function() {
    'use strict';

    angular
        .module('assignment2App')
        .controller('PersonDetailController', PersonDetailController);

    PersonDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Person', 'Animal', 'Item'];

    function PersonDetailController($scope, $rootScope, $stateParams, previousState, entity, Person, Animal, Item) {
        var vm = this;

        vm.person = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assignment2App:personUpdate', function(event, result) {
            vm.person = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
