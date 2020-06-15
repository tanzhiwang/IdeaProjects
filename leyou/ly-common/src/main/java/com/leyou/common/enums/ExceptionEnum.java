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
    CATEGORY_NOT_FUND(404,"商品分类没查到"),
    BRAND_NOT_FOUND(404,"品牌不存在"),

    ;
    private int code;
    private String msg;
}
