(function() {
    'use strict';

    angular
        .module('assignment2App')
        .controller('AnimalController', AnimalController);

    AnimalController.$inject = ['$scope', '$state', 'Animal'];

    function AnimalController ($scope, $state, Animal) {
        var vm = this;

        vm.animals = [];

        loadAll();

        function loadAll() {
            Animal.query(function(result) {
                vm.animals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
