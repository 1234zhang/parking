package com.netplus.catpark.domain.model.exception;

/**
 * 错误码:5位,例XXXXX
 * 第1位:大业务:1系统,2用户,3订单,4支付
 * 第2位:子业务
 * 第3位:错误类型:0参数错误,1业务错误,2依赖服务错误,3系统错误
 * 第4-5位:具体错误码
 * <p>
 * Created by lgj on 16/5/31.
 */
public enum ErrorCode {
    // 10XXX:系统
    SYSTEM_PARAM_ERROR(10001, "参数错误"),
    SYSTEM_JSON_PARSE_ERROR(10002, "JSON解析错误"),
    SYSTEM_PARAM_ERROR_TOO_LONG(10003, "字段过长"),
    SYSTEM_PARAM_ERROR_IS_NULL(10004, "字段为空"),
    SYSTEM_PARAM_ERROR_VALID_DATA_RANGE(10005, "字段不在有效的范围内"),
    SYSTEM_STRING_TO_RESPONSE_ERROR(10006, "接口请求返回内容转为Response失败"),
    SYSTEM_FILE_FORMAT_ERROR(10007, "文件格式错误"),
    SYSTEM_THIRD_INVOKE_ERROR(10201, "请求第三方错误"),
    SYSTEM_ERROR(10301, "系统错误"),
    SYSTEM_SIG_ERROR(10302, "签名错误"),
    SYSTEM_APP_ID_ERROR(10303, "app-id 错误"),
    SYSTEM_ERROR_ACCESS_OBJECT_IN_LOCK(10304, "上锁失败"),
    SYSTEM_ERROR_LOCK_UNACQUIRED(10305, "redis上锁失败"),
    SYSTEM_ERROR_PARAM_ASSERT_FAILED(10306, "内部调用参数错误"),
    SYSTEM_PARAM_ERROR_NOT_EXISTS(10307, "记录不存在"),
    SYSTEM_PARAM_ERROR_ALREADY_EXISTS(10308, "记录重复"),

    // 20XXX:用户-账户
    USER_ACCOUNT_NO_LOGIN(20001, "未登录"),
    USER_ACCOUNT_TOKEN_EXPIRED(20002, "登录过期"),
    USER_ACCOUNT_MOBILE_UNBIND_ERROR(20003, "未绑定手机号"),
    USER_ACCOUNT_VERIFY_CODE_ERROR(20004, "验证码不正确或已过期"),
    USER_ACCOUNT_EMAIL_ERROR(20005, "请输入正确的邮箱"),
    USER_ACCOUNT_ADMIN_LOGIN_REQUIRED(20006, "需要使用管理员密码登录"),

    USER_ACCOUNT_PERMISSION_ERROR(20101, "用户权限错误"),
    USER_ACCOUNT_PASSWORD_ERROR(20104, "用户名或密码错误"),
    USER_ACCOUNT_EMPTY_FIELD(20105, "用户名或密码不能为空"),
    USER_ACCOUNT_NAME_DUPLICATE(20106, "用户名已经存在"),
    USER_ACCOUNT_NOT_EXISTS(20107, "该用户不存在"),
    USER_ACCOUNT_MOBILE_DUPLICATE(20108, "该手机号已经注册，请更换手机号"),
    USER_ACCOUNT_ID_CARD_DUPLICATE(20109, "身份证号已存在"),
    USER_ACCOUNT_VERIFY_CODE_FREQ_ERROR(20110, "一分钟后再次发送"),
    USER_ACCOUNT_STORAGE_ERROR(20111, "保存用户失败"),
    USER_ACCOUNT_CANNOT_LOGIN(20112, "无法登陆"),
    USER_ACCOUNT_CANNOT_HAS_BIND_MOBILE(20113, "该账户已经绑定过手机号"),
    USER_ACCOUNT_CANNOT_BIND_MOBILE(20114, "无法绑定该手机号"),
    USER_ACCOUNT_VERIFY_CODE_TIMES_ERROR(20115, "验证码请求次数太多，请稍后重试"),
    USER_ACCOUNT_VERIFY_CODE_SEND_ERROR(20201, "手机验证码发送错误"),
    USER_ACCOUNT_VERIFY_CODE_MOBILE_OR_CODE_IS_BLANK(20202, "手机号或验证码为空"),
    USER_ACCOUNT_VERIFY_CODE_CODE_IS_NOT_AVAILABLE(20202, "验证码不正确或已失效"),
    USER_ACCOUNT_TOKEN_INVALID(20203, "token无效"),
    USER_ACCOUNT_BIND_WX_ERROR(20204, "绑定微信失败"),
    USER_ACCOUNT_BIND_MOBILE_ERROR(20206, "绑定手机号失败"),
    USER_ACCOUNT_GET_TOKEN_FAILED(20207, "获取token失败"),
    PARTNER_SECRET_ERROR(20208, "partner secret错误"),

    // 21XXX:用户-用户
    USER_INFO_ID_CARD_ERROR(21001, "请输入正确身份证号码"),
    USER_INFO_NAME_ERROR(21002, "请输入正确的姓名"),
    USER_IN_BLACK_LIST(21201, "用户已被加入黑名单"),
    USER_IDCARD_NAME_NOT_MATCH(21202, "身份证和姓名不匹配"),
    USER_IDCARD_VERIFY_REQUEST_NOT_ALLOWD(21203, "请求被拒绝"),
    USER_IDCARD_VERIFY_REQUEST_RETRY_FIVE_MINUTES(21204, "请核对身份证，5分钟后再试一次"),
    USER_IDCARD_VERIFY_REQUEST_RETRY_TEN_MINUTES(21205, "请核对身份证，10分钟后再试一次"),
    USER_IDCARD_VERIFY_REQUEST_RETRY_ONE_DAY(21206, "您的操作太过频繁，请24小时后再试"),
    USER_IDCARD_VERIFY_REQUEST_QUOTA_OVERRUN(21207, "验证失败，请重试"),
    USER_INFO_ID_CARD_NAME_WAITING_FOR_VERIFIED(21208, "身份认证信息正等待验证,无法修改"),

    // 31XXX: 数据库操作错误
    SYSTEM_ERROR_NOTHING_SAVED(31001, "保存失败"),
    SYSTEM_ERROR_NOTHING_UPDATED(31002, "更新失败"),
    SYSTEM_ERROR_NOTHING_DELETED(31003, "删除失败"),
    // 41XXX: 行程操作错误
    TEAM_NOT_EXIST(41001, "行程不存在"),
    TEAM_HAS_SIMILAR(41002, "已有相似行程"),
    TEAM_HAS_FULL(41003, "该行程已满"),
    TEAM_CANCEL_FALSE(41004, "取消行程失败"),



    EXCEPTION_HAPPEN(99998, "SYSTEM_EXCEPTION"),

    DEFAULT(99999, "NULL");

    private int code;

    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}