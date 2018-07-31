/**
 * 重写alert
 * @param msg
 * @param callback
 */
window.alert = function (msg, callback) {
    layer.alert(msg, {
        skin: 'layui-layer-lan'
        , closeBtn: 0
    }, function (index) {
        layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
}

/**
 * 重写confirm式样框
 * @param msg
 * @param callback
 */
window.confirm = function (msg, callback) {
    layer.confirm(msg
        , {
            skin: 'layui-layer-lan'
            , closeBtn: 0
            , btn: ['确定', '取消']
        }
        , function () {//确定事件
            if (typeof(callback) === "function") {
                callback("ok");
            }
        });
}
