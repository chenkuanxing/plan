/*<![CDATA[*/
parent.ueditorPath =/*[[@{/}]]*/ '';
/*]]>*/
var accessToken = window.sessionStorage.accessToken;

function skipLogin(code, message) {
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.msg(message, {icon: 5});
        if (code == 201 || code == 202 || code == 204 || code == 205 || code == 206) {
            //跳转登入页
            window.parent.location.href = parent.ueditorPath + "/login";
        }
    });

}