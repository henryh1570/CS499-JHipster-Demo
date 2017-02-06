(function() {
    'use strict';

    angular
        .module('assignment2App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('animal', {
            parent: 'entity',
            url: '/animal',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Animals'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/animal/animals.html',
                    controller: 'AnimalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('animal-detail', {
            parent: 'animal',
            url: '/animal/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Animal'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/animal/animal-detail.html',
                    controller: 'AnimalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Animal', function($stateParams, Animal) {
                    return Animal.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'animal',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('animal-detail.edit', {
            parent: 'animal-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/animal/animal-dialog.html',
                    controller: 'AnimalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Animal', function(Animal) {
                            return Animal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('animal.new', {
            parent: 'animal',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/animal/animal-dialog.html',
                    controller: 'AnimalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('animal', null, { reload: 'animal' });
                }, function() {
                    $state.go('animal');
                });
            }]
        })
        .state('animal.edit', {
            parent: 'animal',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/animal/animal-dialog.html',
                    controller: 'AnimalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Animal', function(Animal) {
                            return Animal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('animal', null, { reload: 'animal' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('animal.delete', {
            parent: 'animal',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/animal/animal-delete-dialog.html',
                    controller: 'AnimalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Animal', function(Animal) {
                            return Animal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('animal', null, { reload: 'animal' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
