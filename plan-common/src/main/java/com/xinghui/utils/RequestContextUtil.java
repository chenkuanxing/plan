package com.xinghui.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestContextUtil {

    private static final String CTX_USER_ID = "userId";
    private static final String CTX_NAME_CN = "nameCn";
    private static final String CTX_USER_AVATAR = "avatar";
    private static final String CTX_USER_INFO = "userInfo";
    private static final String CTX_TOKAN = "token";
    private static final String CTX_TERMINAL = "terminal";

    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

    private static ObjectMapper om = new ObjectMapper();

    private static void setContext(String contextAttribute, Object value) {
        Map<String, Object> map = context.get();
        if (map == null) {
            map = new HashMap<>();
            context.set(map);
        }

        map.put(contextAttribute, value);
    }

    private static Object get(String contextAttribute) {
        Map<String, Object> map = context.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            context.set(map);
        }
        return map.get(contextAttribute);
    }

    public static void setUserInfo(UserInfo user) throws JsonProcessingException, UnsupportedEncodingException {
        setContext(CTX_USER_INFO, user);
        setContext(CTX_USER_ID, user.getId());
        setContext(CTX_NAME_CN, URLDecoder.decode(user.getNameCn(), "UTF-8"));
        setContext(CTX_USER_AVATAR, user.getAvatar());
        setContext(CTX_TOKAN, user.getToken());
        setContext(CTX_TERMINAL, user.getTerminal());
    }

    public static void clear() {
        context.remove();
    }

    public static UserInfo userInfo() {
        return (UserInfo) get(CTX_USER_INFO);
    }

    public static Long userId() {
        return (Long) get(CTX_USER_ID);
    }

    public static String nameCn() {
        return (String) get(CTX_NAME_CN);
    }

    public static String userAvatar() {
        return (String) get(CTX_USER_AVATAR);
    }

    public static String token() {
        return (String) get(CTX_TOKAN);
    }

    public static String terminal() {
        return (String) get(CTX_TERMINAL);
    }

}
