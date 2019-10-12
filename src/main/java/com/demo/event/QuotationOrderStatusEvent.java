package com.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author james
 * @date 2019/10/12
 */
public class QuotationOrderStatusEvent extends ApplicationEvent {

    private String orderNo;

    private Integer oldStatus;

    private Integer newStatus;

    public QuotationOrderStatusEvent(Object source) {
        super(source);
    }

    public QuotationOrderStatusEvent(String orderNo, Integer oldStatus, Integer newStatus) {
        super(orderNo);
        this.orderNo = orderNo;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "QuotationOrderStatusEvent{" +
                "orderNo='" + orderNo + '\'' +
                ", oldStatus=" + oldStatus +
                ", newStatus=" + newStatus +
                '}';
    }
}
