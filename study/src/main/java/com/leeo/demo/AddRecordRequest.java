package com.leeo.demo;

/**
 * Created by lijun on 2016/6/12.
 */
public class AddRecordRequest {
    private Long id;
    private String name;
    private String mobile;
    private String startDate;
    private String endDate;
    private Boolean valid;
    private String orderItemId;
    private String orderId;
    private String type;
    private String productId;
    private String customerId;
    private String status;
    private String code;
    private String idType;
    private String idNo;
    private String quantity;

    public AddRecordRequest() {
    }

    public AddRecordRequest(Long id, String name, String mobile, String startDate, String endDate, Boolean valid, String orderItemId, String orderId, String type, String productId, String customerId, String idType, String idNo, String quantity) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.startDate = startDate;
        this.endDate = endDate;
        this.valid = valid;
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.type = type;
        this.productId = productId;
        this.customerId = customerId;
        this.idType = idType;
        this.idNo = idNo;
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
