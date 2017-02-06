(function() {
    'use strict';

    angular
        .module('assignment2App')
        .controller('AnimalDetailController', AnimalDetailController);

    AnimalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Animal'];

    function AnimalDetailController($scope, $rootScope, $stateParams, previousState, entity, Animal) {
        var vm = this;

        vm.animal = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assignment2App:animalUpdate', function(event, result) {
            vm.animal = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
