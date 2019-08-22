package com.netplus.catpark.domain.enums;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 22:13.
 */

public enum OrderStatusEnums {
    /**
     * 订单状态枚举
     */
    ORDER_FAIL(0,"订单失败"),

    ORDER_DOING(1,"订单正在进行中"),

    ORDER_FINISH_UNPAY(2,"停车结束但未付款"),

    ORDER_CANCEL(3,"订单取消"),

    ORDER_SUCCESS(4,"订单成功"),

    BOOK_PARKING_SPACE(5,"预定车位");

    private int orderStatus;
    private String message;

    private OrderStatusEnums(int orderStatus, String message){
        this.orderStatus = orderStatus;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getOrderStatus() {
        return orderStatus;
    }
}
