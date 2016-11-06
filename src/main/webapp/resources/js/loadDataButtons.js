'use strict';

Ext.require([ 'Ext.Button' ]);

var startPage = 1; // the paging bar start page after a data reloading

var employeesApiUrl = '/api/getEmployees';
var apiDepartmentIdParam = 'departmentId';

Ext.onReady(function () {

    Ext.create('Ext.Button', {
        text: 'All departments',
        renderTo: 'buttons',
        handler: function () {
            reloadEmployees();
        }
    });

    Ext.create('Ext.Button', {
        text: 'Department A',
        renderTo: 'buttons',
        handler: function () {
            reloadEmployeesByDepartment("1");
        }
    });

    Ext.create('Ext.Button', {
        text: 'Department B',
        renderTo: 'buttons',
        handler: function () {
            reloadEmployeesByDepartment("2");
        }
    });
});

function reloadEmployees() {
    gridStore.proxy.url = employeesApiUrl;
    // we don't want to ask pages which aren't existing anymore
    gridStore.load({params: { page: startPage, start: 0, limit: itemsPerPage}});
    gridStore.currentPage = startPage;
    pagingBar.update();
}

function reloadEmployeesByDepartment(departmentId) {
    gridStore.proxy.url = employeesApiUrl + '?' + apiDepartmentIdParam + '=' + departmentId;
    gridStore.load({params: { page: startPage, start: 0, limit: itemsPerPage}});
    gridStore.currentPage = startPage;
    pagingBar.update();
}