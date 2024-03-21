package mia.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author sxy
 * @since 2022/7/11
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ResultException extends RuntimeException {

    private final Integer code;
    private final String msg;

    public ResultException(ResultEnum resultEnum) {
        code = resultEnum.getCode();
        msg = resultEnum.getMsg();
    }

    public ResultException(ResultEnum resultEnum, String message) {
        code = resultEnum.getCode();
        msg = message;
    }
}
