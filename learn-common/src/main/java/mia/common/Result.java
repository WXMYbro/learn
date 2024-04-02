package mia.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 统一返回对象中，Code码、Info描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;

    private String code;

    private String msg;

    private String info;

    private Object data;


    /**
     * 成功返回结果
     */
    public static Result success() {
        return new Result("",
                ResultEnum.SUCCESS.getMsg(),
                null,null);
    }

    /**
     * 成功返回结果
     *
     * @param data 数据内容
     */
    public static Result success(Object data) {
        return new Result("",
                ResultEnum.SUCCESS.getMsg(),null,data);
    }

    public static Result buildResult(String code, String info) {
        return new Result(code, info);
    }

    public static Result buildResult(Constants.ResponseCode code, String info) {
        return new Result(code.getCode(), info);
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info) {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
