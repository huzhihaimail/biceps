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
        field: "parentId",
        title: "父级菜单",
        width: "10%",
        sortable: true,
        sortName: "parent_id" // sortName的值，需配置和数据库保持一致
    }
    , {
        field: "name",
        title: "菜单名称",
        width: "20%",
        sortable: true,
        sortName: "name"
    }
    , {
        field: "url",
        title: "链接地址",
        width: "20%",
        sortable: true,
        sortName: "url"
    }
    , {
        field: "type",
        title: "菜单类型",
        width: "20%",
        sortable: true,
        formatter: function (value, row, index) { // 设置列序号值，index从0开始
            if (value == 0) {
                return "目录";
            }
            if (value == 1) {
                return "菜单";
            }
            if (value == 2) {
                return "按钮";
            }
        }
    }
    , {
        field: "icon",
        title: "图标",
        width: "15%",
        sortable: true,
        sortName: "icon"
    }

    , {
        field: "orderNum",
        title: "排序",
        width: "10%"

    }
    /*, {
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
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId"
        }
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
        , errorMessage: null // 异常信息
        , title: null // 标题
        , vueQueryParam: { // 查询参数
            keyword: null,
        }
        , model: { //实体对象(用于新建、修改页面)
            parentName: "",
            parentId: 0,
            type: 0,
        }
        , menu: {}
        // 定义模块名称
        , moduleName: "menu"
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

            //3.加载树控件
            vm.getMenuTree();

            // 4. 清空表单数据
            vm.model = {
                parentName: "",
                parentId: 0,
                type: 0,
            }
        }

        // 点击“确定”按钮
        , commit: function (el) {

            if (vm.model.type != 0 && vm.model.type != 1 && vm.model.type != 2) {
                vm.errorMessage = "请选择菜单类型！";
                return;
            }

            // 校验表单菜单名称
            if (vm.model.name.trim() == null || vm.model.name.trim() == "") {
                vm.errorMessage = "请输入菜单名称！";
                return;
            }

            // 校验表单父级菜单
            if (vm.model.parentId.trim() == null || vm.model.parentId.trim() == "") {
                vm.errorMessage = "请选择父级菜单！";
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
        }

        // 执行修改操作
        , doUpdate: function () {

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
        , getMenuTree: function () {
            function getMenuJson(url) {
                var zNodes;
                $.ajax({
                    url: url,
                    dataType: 'JSON',
                    type: 'POST',
                    async: false,
                    success: function (data, status) {
                        var nodes = JSON.stringify(data.model);
                        zNodes = eval(nodes);
                    }
                });
                return zNodes;
            }

            ztree = $.fn.zTree.init($("#menuTree"), setting, getMenuJson(APP_NAME + "/sys/" + vm.moduleName + "/queryMenu"));
            //展开所有节点
            ztree.expandAll(true);
        }
        , showMenuTree: function () {

            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.model.parentId = node[0].menuId;
                    vm.model.parentName = node[0].name;
                    layer.close(index);

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
    bsTable.createBootStrapTable(vm.columns, APP_NAME + "/sys/" + vm.moduleName + "/list?rnd=" + Math.random(), vm.queryOption);
});




