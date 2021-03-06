package com.shop.order.api.enums;

public enum OrderStatuEnum {

    INIT(0,"初始"),
    PENDING_PAY(1,"待支付"),
    PENDING_DELIVER(2,"待发货"),
    DELIVERED(3,"已发货"),
    FINISH(5,"交易成功"),
    CLOSE(7,"交易关闭");

    private Integer value;

    private String name;

    OrderStatuEnum(Integer value,String name) {
        this.value=value;
        this.name=name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
