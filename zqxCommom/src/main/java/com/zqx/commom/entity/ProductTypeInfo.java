package com.zqx.commom.entity;

import lombok.Data;

@Data
public class ProductTypeInfo {
    private int id;
    private String producttypeName;
    private String producttypeDescription;
    private int parentId;
    private int grade;
}
