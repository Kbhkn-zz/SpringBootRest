'use strict';

angular.module('myApp').controller('CustomerController', ['$scope', 'CustomerService', function($scope, CustomerService) {
    var self = this;
    self.customer = {
        id: null,
        name: '',
        lastName: '',
        email: ''
    };
    self.customers = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    getAllCustomers();

    function getAllCustomers() {
        CustomerService.getAllCustomers()
            .then(
                function(d) {
                    self.customers = d;
                },
                function(errResponse) {
                    console.error('Error while fetching Customers');
                }
            );
    }

    function createCustomer(customer) {
        CustomerService.createCustomer(customer)
            .then(
                getAllCustomers,
                function(errResponse) {
                    console.error('Error while creating Customer');
                }
            );
    }

    function updateCustomer(customer, id) {
        CustomerService.updateCustomer(customer, id)
            .then(
                getAllCustomers,
                function(errResponse) {
                    console.error('Error while updating Customer');
                }
            );
    }

    function deleteCustomer(id) {
        CustomerService.deleteCustomer(id)
            .then(
                getAllCustomers,
                function(errResponse) {
                    console.error('Error while deleting Customer');
                }
            );
    }

    function submit() {
        if (self.customer.id === null) {
            createCustomer(self.customer);
        } else {
            updateCustomer(self.customer, self.customer.id);
        }
        reset();
    }

    function edit(id) {
        for (var i = 0; i < self.customers.length; i++) {
            if (self.customers[i].id === id) {
                self.customer = angular.copy(self.customers[i]);
                break;
            }
        }
    }

    function remove(id) {
        if (self.customer.id === id) {
            reset();
        }
        deleteCustomer(id);
    }


    function reset() {
        self.customer = {
            id: null,
            name: '',
            lastName: '',
            email: ''
        };
        $scope.registrationForm.$setPristine();
    }
}]);