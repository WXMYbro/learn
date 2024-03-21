package mia.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sxy
 * @since 2022/7/11
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 通用状态码
     */
    NOT_FOUND(404, "服务器资源异常，路径不存在"),
    SUCCESS(90000, "成功"),
    ERROR(10001, "请求失败"),
    BIND_ERROR(10002, "参数校验错误"),
    SAVE_ERROR(10003, "保存失败！"),
    UPDATE_ERROR(10004, "修改失败！"),
    REMOVE_ERROR(10005, "删除失败！"),
    DUPLICATE_ERROR(10006,"已有重复数据"),
    DATA_PROTECT(20001, "数据保护！"),
    PROCESSING_UNFINISHED(20002, "上个操作未完成"),
    NEED_MODIFY_PASSWORD(30000, "登录前需要修改密码！"),


    NOT_LOGIN(50000, "登录过期，请重新登录"),


    MISSING_COOKIE_PEER(50010, "缺少PEER参数"),
    MISSING_COOKIE_TIME(50011, "缺少时间戳参数"),
    MISSING_COOKIE_NONCE(50012, "缺少NONCE参数"),
    MISSING_COOKIE_SIGN(50013, "缺少SIGN参数"),
    ERROR_COOKIE_TIME(50021, "无效的时间"),
    ERROR_COOKIE_NONCE(50022, "无效的NONCE"),
    ERROR_COOKIE_SIGN(50023, "无效的SIGN"),
    PEER_BEAN_NOT_EXIST(50031, "PEER对象不存在"),

    ;
    private final Integer code;
    private final String msg;
}
