(function() {
    'use strict';

    angular
        .module('assignment2App')
        .controller('AnimalDialogController', AnimalDialogController);

    AnimalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Animal'];

    function AnimalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Animal) {
        var vm = this;

        vm.animal = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.animal.id !== null) {
                Animal.update(vm.animal, onSaveSuccess, onSaveError);
            } else {
                Animal.save(vm.animal, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assignment2App:animalUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
