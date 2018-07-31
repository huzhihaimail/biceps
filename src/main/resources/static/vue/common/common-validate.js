

//只能输入数字
function numValid(str) {
    return /^[0-9]*$/.test(str);
}

//数字字母下划线
function enNumValid(str) {
    return /^\w+$/.test(str);
}

//用于验证手机号
function phoneValid(str) {
    return /^1[3|4|5|7|8][0-9]\d{4,8}$/.test(str);
}

//用于验证公司邮箱
function emailValid(str) {
    return /@weshare.com.cn$/.test(str);
}

function userNameExistValid(){

}


