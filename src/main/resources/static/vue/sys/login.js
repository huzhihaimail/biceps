var vm = new Vue({
    el: '#' + VUE_EL,

    data: {
        userName: "admin" // 定义用户
        , pwd: "123456" // 定义密码
        , rememberMe: false // 记住我
        , message: ""
    },
    methods: {
        reset: function () {
            vm.userName = ""
                , vm.pwd = ""
        }

        // 登陆
        , login: function () {
            $.ajax({
                type: "POST"
                /* ,contentType:"application/json"*/
                , url: APP_NAME + "/login"
                , data: {userName: vm.userName, password: vm.pwd, rememberMe: vm.rememberMe}
                , success: function (r) {
                    // 异常提示
                    if (r.code != "") {
                        vm.message = r.msg;
                        return;
                    }
                    // 跳转到首页
                    window.location.href = APP_NAME + "/html/sys/index.html";
                }
                , error: function (r) {
                    vm.message = "请求异常";
                }
            });
        }
    }
});

