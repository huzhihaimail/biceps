var $table;


/**
 * 定义类
 */
function BootStrapTable() {
}

/**
 * 获取当前选择多行ID
 */
BootStrapTable.prototype.getMultiRowIds = function () {
    var ids = new Array();
    var rows = $table.bootstrapTable('getAllSelections');
    for (i = 0; i < rows.length; i++) {
        ids[i] = rows[i].id;
    }

    return ids;
}

/**
 * 取消所有选择
 */
BootStrapTable.prototype.uncheckAll = function () {
    return $table.bootstrapTable('uncheckAll');
}


/**
 * 销毁表格数据
 */
BootStrapTable.prototype.destroyBootStrapTable = function () {
    $('#' + TABLE_ID).bootstrapTable('destroy');
}


/**
 * 创建表格
 */
BootStrapTable.prototype.createBootStrapTable = function (columns, url, queryOpt) {

    // 销毁表格数据
    this.destroyBootStrapTable();
    // 初始化表格插件
    this.initBootstrapTable(columns, url, queryOpt);
}

/**
 * 加载表格
 * @param defineQueryParams 用户自定义查询参数（传递到后台）
 */
BootStrapTable.prototype.initBootstrapTable = function (columns, url, queryOpt) {
    $table = $('#' + TABLE_ID).bootstrapTable({
        columns: columns // 设置查询结果展示列
        , url: url // 请求服务端数据的URL地址
        , height: $(window).height() - 50 - 30
        , toolbar: '#toolbar'// 显示工具按钮
        , showRefresh: true // 显示刷新按钮
        , showPaginationSwitch: true // 显示和隐藏状态栏按钮
        , striped: true // 表格数据隔行换色
        , pagination: true // 显示分页按钮
        , paginationHAlign: "right"
        , pageNumber: PAGE_NUMBER
        , pageSize: TABLE_PAGE_SIZE
        , pageList: TABLE_PAGE_SIZE_LIST
        , sidePagination: "server" // 后台分页
        , paginationLoop: false // 分页不循环
        , clickToSelect: true // 点击选中一行
        , checkboxHeader: true // 显示和隐藏多选checkbox
        , sortable: true //隐藏和显示所有排序按钮
        , sortOrder: "asc" // 排序类型
        , showColumns: true
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        , queryParamsType: "undefined"
        , queryParams: function queryParams(params) {   //设置查询参数

            // 设置表格默认参数
            var bstParam = {
                pageNumber: params.pageNumber
                , pageSize: params.pageSize
                , sortName: params.sortName
                , sortOrder: params.sortOrder
                , searchText: params.searchText
            }

            if (!queryOpt) {
                return bstParam;
            }

            // 用户自定义查询参数
            for (var key in queryOpt) {
                bstParam[key] = queryOpt[key];
            }

            return bstParam;
        }
    });
}


