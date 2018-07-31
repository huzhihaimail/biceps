var vm = new Vue({
    el: '#app',

    data: {
        userName: "admin" // 定义用户
        , pwd: "123456" // 定义密码
        , message: ""
    },
    methods: {

        // 登陆
        login: function () {
            $.ajax({
                type: "POST"
                , url: APP_NAME + "/login"
                , data: {userName: vm.userName, password: vm.pwd}
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

