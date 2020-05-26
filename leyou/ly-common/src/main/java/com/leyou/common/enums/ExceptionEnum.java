package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//枚举是具有固定实例个数的类，提前创建好了实例
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400,"价格不能为空！"),//等同于public static final ...=new ...


    ;
    private int code;
    private String msg;
}
