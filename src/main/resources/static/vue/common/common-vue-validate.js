// 自定义指令,在文本框的值变化时执行校验
Vue.directive('validate', {
    componentUpdated: function (el, binding, vnode) {

        // 获取验证类型
        var validateType = binding.value;

        // 获取文本框的值
        var inputValue = el.value;

      /*  // 新增页面
        if (inputValue == "" || inputValue == null || inputValue == undefined) {
            return;
        }

        // 不校验
        if (!validateType || validateType == "ignore") {
            return;
        }

        // 验证数值
        if (validateType == "number") {

            // 校验数字通过
            if (phoneValid(validateType)) {
                //el.parentNode.parentNode.className = "form-group has-success"
                return;
            }

            //el.parentNode.parentNode.className = "form-group has-error";
            return;
        }*/
    }
});




