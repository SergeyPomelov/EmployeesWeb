'use strict';

Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.Msg.*']);

var timeoutInMs = 15000; // 15s

var totalWidthPx = 800;
var itemsPerPage = 20;

var grid;
var gridStore;
var pagingBar;

Ext.onReady(function () {

    Ext.define('employeeModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'int'},
            {name: 'name', type: 'string'},
            {name: 'departmentId', type: 'int'},
            {name: 'mail', type: 'string'},
            {name: 'phone', type: 'string'}
        ]
    });

    gridStore = Ext.create('Ext.data.Store', {
        autoLoad: false,
        pageSize: itemsPerPage,
        storeId: 'employeesStore',
        model: 'employeeModel',
        proxy: {
            type: 'ajax',
            timeout: timeoutInMs,       // db timeout is 10 sec
            noCache: false,             // read-only app's lifespan data viewed in modern browsers
            url: '/api/getEmployees',   // overriding in the button's click handlers
            reader: {
                type: 'json',
                model: 'employeeModel',
                rootProperty: 'employees',
                totalProperty: 'total',
                messageProperty: 'error'
            },
            afterRequest: function (request, success) {
                if (!success) { // for example, if back end is dead
                    Ext.Msg.alert('Unknown error', 'Possible, it\'s a data request timeout.');
                }
            }
        }
    });

    gridStore.load({
        params: {start: 0, limit: itemsPerPage},
        callback: function (records, operation, success) {
            if (!success) { // for example, if back end got a fatal error while processed this request
                Ext.Msg.alert('Error', operation.getError());
            }
        }
    });

    pagingBar = Ext.create('Ext.toolbar.Paging', {
        xtype: 'pagingtoolbar',
        id: 'pagingtoolbar',
        store: gridStore,
        dock: 'bottom',
        displayInfo: true,
        displayMsg: 'Displaying records {0} - {1} of {2}',
        emptyMsg: "No Data to display"
    });

    grid = Ext.create('Ext.grid.Panel', {
        id: 'gridId',
        store: gridStore,
        title: 'Employees',
        renderTo: 'grid',
        width: totalWidthPx,
        loadMask: true,
        stripeRows: true,
        collapsible: true,
        enableColumnMove: true,
        enableColumnResize: true,
        columns: [{
            header: 'Id',
            dataIndex: 'id',
            flex: 0.02,
            sortable: true,
            hideable: true
        }, {
            header: 'Name',
            dataIndex: 'name',
            flex: 0.7,
            sortable: true,
            hideable: false
        }, {
            header: 'Mail',
            dataIndex: 'mail',
            flex: 0.5,
            sortable: true,
            hideable: true,
            renderer: function (value) {
                return Ext.String.format('<a href="mailto:{0}">{1}</a>', value, value);
            }
        }, {
            header: 'Phone',
            dataIndex: 'phone',
            flex: 0.4,
            sortable: true,
            hideable: true
        }],

        dockedItems: [pagingBar]
    });
});

