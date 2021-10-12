/*<![CDATA[*/
parent.ueditorPath =/*[[@{/}]]*/ '';
/*]]>*/
var accessToken = window.sessionStorage.accessToken;
console.log(accessToken);

function skipLogin(code, message) {
    if (code == 201 || code == 202 || code == 204 || code == 205 || code == 206) {
        //跳转登入页
        layer.msg(message);
        location.href = parent.ueditorPath + "/login";
    }
    layer.msg(message);
}