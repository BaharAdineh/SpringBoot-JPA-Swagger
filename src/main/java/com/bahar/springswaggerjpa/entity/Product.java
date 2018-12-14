package com.bahar.springswaggerjpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by IntelliJ IDEA.
 * User: B_Adineh
 * Date: 12/14/2018
 * Time: 1:25 PM
 * To change this template use File | Settings | File and Code Templates.
 */
@Entity
@Table(name = "tblProduct")
@ApiModel(description="All details about the Product. ")
public class Product {

    @ApiModelProperty(notes = "The database generated product ID")
    private long id;

    @ApiModelProperty(notes = "The product Name")
    private String  Name;

    @ApiModelProperty(notes = "The product Type")
    private String Type;

    @ApiModelProperty(notes = "The product Price")
    private int Price;

    public Product() {

    }

    public Product(String name, String type, int price) {
        Name = name;
        Type = type;
        Price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }


    @Override
    public String toString() {
        return super.toString();
       /* return "Product [id=" + id +
                ",  Name=" + Name +
                ", Type=" + Type +
                ", " + "Price=" +Price
                + "]";*/
    }
}