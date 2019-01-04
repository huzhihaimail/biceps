/**
 * 表格显示列
 */
var showColumns = [
    {
        checkbox: true, width: "2%"
    }
    , {
        title: "序号",
        field: "id",
        width: "3%",
        align: "center",
        formatter: function (value, row, index) { // 设置列序号值，index从0开始
            return index + 1;
        }
    }
    , {
        field: "name",
        title: "角色名称",
        width: "20%",
        sortable: true,
        sortName: "name" // sortName的值，需配置和数据库保持一致
    }
    , {
        field: "nameCn",
        title: "角色中文名称",
        width: "10%",
    }
    // , {
    //     field: "status",
    //     title: "状态",
    //     width: "5%",
    //     formatter: function (value, row, index) {
    //         if (value == '0') {
    //             return "<span class='label label-success'>启用</span>";
    //         } else {
    //             return "<span class='label label-danger'>停用</span>";
    //         }
    //     }
    // }
    , {
        field: "createDate",
        title: "创建时间",
        width: "20%",
        formatter: function (value, row, index) {
            return new moment(value).format('YYYY-MM-DD HH:mm:ss');
        }
    }
    , {
        field: "updateDate",
        title: "最近修改时间",
        width: "20%",
        formatter: function (value, row, index) {
            return new moment(value).format('YYYY-MM-DD HH:mm:ss');
        }
    }
    /*, {menu/queryAllMenuInsert
        field: "operate",
        title: "操作",
        width: "15%",
        formatter: function () {
            return '<a class="btn btn-success btn-sm" @click="save"><i class="fa fa-floppy-o"></i></a>\n' +
                '<a class="btn btn-warning btn-sm" @click="update"><i class="fa fa-pencil-square-o"></i></a>\n' +
                '<a class="btn btn-danger btn-sm" @click="del"><i class="fa fa-trash"></i></a>';
        }
    }*/
];


// 通用表格对象
var bsTable = new BootStrapTable();
// 如果有特殊表格需要处理，此处可以覆写覆写自己的表格属性 BootStrapTable.prototype.initBootstrapTable = function (columns, url, queryOpt) {}

var setting = {
    view: {
        selectedMulti: false,
        showIcon: true
    },
    check: {
        enable: true,
        //样式设置为checkbox 复选框 也可以设置为单选框 radioButton
        chkStyle:'checkbox',
        //Y-勾选节点
        //N-取消勾选
        //p-parent 是否关联父节点
        //s-sun 是否关联子节点
        chkboxType:{
            'Y':'ps',
            'N':'s'
        }
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        }
    },
    edit: {
        enable: false
    }
};

var ztree;

// 定义vue实例
var vm = new Vue({
    el: "#" + VUE_EL
    , data: {

        /* 定义bootstrap-table表格参数 */
        queryOption: {}
        , columns: showColumns

        /* 定义页面操作参数 */
        , show: true// 切换页面中的查询和新建（编辑）页面
        , showPwd: true // 显示修改密码框
        , errorMessage: null // 异常信息
        , title: null // 标题
        , vueQueryParam: { // 查询参数
            keyword: null,
        }
        , model: {} //实体对象(用于新建、修改页面)
        , roles: [] // 加载角色列表对象

        // 定义模块名称
        , moduleName: "role"
        // ztree的JSON树
        , menuJSON: {}

    }
    // 定义方法
    , methods: {

        // 点击“查询”按钮事件
        query: function () {
            vm.reload();
        }

        // 点击“新增”按钮
        , save: function (event) {
            // 1. 隐藏表格，显示添加页面
            vm.show = false;
            vm.errorMessage = null;

            // 2. 设置标题
            vm.title = PAGE_INSERT_TITLE;
            // 3. 清空表单数据
            vm.model = {
                menuIdList: new Array()
            };

            //4.加载树控件
            // vm.loadTree('menuTree', 'add');
            vm.loadTreeMenu('add');
        }

        // 点击“确定”按钮
        , commit: function (el) {

            // 校验表单
            if (!vm.model.name || vm.model.name.trim() == "") {
                vm.errorMessage = "请输入角色名";
                return;
            }
            // 角色中文名
            if (!vm.model.nameCn|| vm.model.nameCn.trim() == "") {
                vm.errorMessage = "请输入角色中文名";
                return;
            }

            //获取选择的菜单
            var nodes = ztree.getCheckedNodes(true);
            if (nodes == null || nodes == "") {
                vm.errorMessage = "请选择菜单";
                return;
            }

            // 执行新增操作
            if (vm.model.id == null) {
                vm.doSave();
                return;
            }

            // 执行修改操作
            vm.doUpdate();
        }

        // 执行保存操作
        , doSave: function () {

            //获取选择的菜单
            var nodes = ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            vm.model.menuIdList = menuIdList;

            // 2. 入库
            $.ajax({
                type: "POST",
                url: APP_NAME + "/sys/" + vm.moduleName + "/insert",
                contentType: "application/json",
                data: JSON.stringify(vm.model),
                success: function (r) {
                    if (r.code === 0) {
                        alert(PAGE_OPERATOR_SUCCESS, function (index) {
                            vm.reload();
                        });
                    } else if (r.code) {
                        alert(r.msg);
                    } else {
                        alert(r.msg);
                    }
                }
            });

            // 清除查询条件
            vm.queryOption.keyword = "";
        }

        // 显示修改页面
        , update: function () {

            // 隐藏密码框
            vm.showPwd = false;
            vm.errorMessage = null;

            // 获取所选择选择数据行的ID（可能选择多行）
            var ids = bsTable.getMultiRowIds();

            // 校验只能选择一行
            if (ids.length != 1) {
                alert(PAGE_SELECT_ONE);
                return;
            }

            $.get(APP_NAME + "/sys/" + vm.moduleName + "/" + ids[0], function (r) {
                vm.show = false;
                vm.title = PAGE_UPDATE_TITLE;
                vm.model = r.model;
            });

            //5.加载树控件
            // vm.loadTree('menuTree', 'update');
            vm.loadTreeMenu('update');
        }

        // 执行修改操作
        , doUpdate: function () {

            //获取选择的菜单
            var nodes = ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            vm.model.menuIdList = menuIdList;

            // 执行修改
            $.ajax({
                type: "POST",
                url: APP_NAME + "/sys/" + vm.moduleName + "/update",
                contentType: "application/json",
                data: JSON.stringify(vm.model),
                success: function (r) {
                    if (r.code === 0) {
                        alert(PAGE_OPERATOR_SUCCESS, function (index) {
                            vm.reload();
                        });
                    } else if (r.code) {
                        alert(r.msg);
                    } else {
                        alert(r.msg);
                    }
                }
            });
        }

        // 点击“删除”按钮
        , del: function (event) {

            // 获取选择记录ID
            var ids = bsTable.getMultiRowIds();

            // 校验未选择任何一行
            if (ids == null || ids.length <= 0) {
                alert(PAGE_SELECT_ONE);
                return;
            }

            confirm(PAGE_ARE_YOU_SURE_DEL, function () {
                $.ajax({
                    type: "POST",
                    url: APP_NAME + "/sys/" + vm.moduleName + "/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert(PAGE_OPERATOR_SUCCESS, function (index) {
                                vm.reload();
                            });
                        } else if (r.code) {
                            alert(r.msg);
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        }

        // 重新加载(ok)
        , reload: function () {

            // 展示查询列表
            vm.show = true;

            // 查询条件
            var queryOpt = {
                'keyword': vm.vueQueryParam.keyword == null ? "" : vm.vueQueryParam.keyword.trim(),
            };

            vm.queryOption = queryOpt;

            // 刷新表格数据
            bsTable.createBootStrapTable(showColumns, APP_NAME + "/sys/" + vm.moduleName + "/list?rnd=" + Math.random(), vm.queryOption);
        }
        , loadTreeMenu: function (type) {
            function getMenuJson(url, data) {
                var zNodes;
                var role = {id: data};
                $.ajax({
                    url: url,
                    dataType: 'JSON',
                    type: 'POST',
                    data: role,
                    async: false,
                    success: function (data, status) {
                        var nodes = JSON.stringify(data.model);
                        zNodes = eval(nodes);
                    }
                });
                return zNodes;
            }

            if (type == 'add') {
                var data = null;
                ztree = $.fn.zTree.init($("#menuTree"), setting, getMenuJson(APP_NAME + "/sys/menu/queryAllMenuInsert", data));
                //展开所有节点
                ztree.expandAll(true);
            } else if (type == 'update') {
                var ids = bsTable.getMultiRowIds();
                var data = ids[0];
                ztree = $.fn.zTree.init($("#menuTree"), setting, getMenuJson(APP_NAME + "/sys/menu/queryAllMenuUpdate", data));
                //展开所有节点
                ztree.expandAll(true);
            }

        }

        // 获取ztree的JSON数据
        , loadTree: function (id, type) {

            if (type == 'add') {
                // 加载menuJson
                vm.getMenuJson();

                ztree = $.fn.zTree.init($("#" + id), setting, vm.menuJSON);
                ztree.expandAll(true);
            } else if (type == 'update') {

                var ids = bsTable.getMultiRowIds();
                var selectedId = ids[0];
                // 加载menuJson
                vm.getMenuJsonById(selectedId);
                vm.getMenuJson();
                ztree = $.fn.zTree.init($("#" + id), setting, vm.menuJSON);
                ztree.expandAll(true);
            }

        }

        // 加载角色列表
        , getMenuJson: function () {
            $.ajax({
                url: APP_NAME + "/sys/menu/queryAllMenus",
                dataType: 'JSON',
                type: 'POST',
                async: false,
                success: function (data, status) {
                    var nodes = JSON.stringify(data);
                    vm.menuJSON = eval(nodes);
                }
            });
        }
        , getMenuJsonById: function (id) {
            $.ajax({
                url: APP_NAME + "/sys/roleMenu/" + id,
                dataType: 'JSON',
                type: 'POST',
                async: false,
                success: function (data, status) {
                    var nodes = JSON.stringify(data.model);
                    vm.menuJSON = eval(nodes);
                }
            });
        }

    }
});

/**
 * 页面初始化执行
 */
$(function () {

    // 创建BootStrapTable
    bsTable.createBootStrapTable(vm.columns, APP_NAME + "/sys/" + vm.moduleName + "/list?rnd=" + Math.random(), vm.queryOption)
});




