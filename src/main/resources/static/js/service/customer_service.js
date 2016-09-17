'use strict';

angular.module('myApp').factory('CustomerService', ['$http', '$q', function($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:9999/customer/';

    var factory = {
        getAllCustomers: getAllCustomers,
        createCustomer: createCustomer,
        updateCustomer: updateCustomer,
        deleteCustomer: deleteCustomer
    };

    return factory;

    function getAllCustomers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while fetching Customers');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createCustomer(customer) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, customer)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while creating Customer');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateCustomer(customer, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI + id, customer)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while updating Customer');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteCustomer(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + id)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while deleting Customer');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);