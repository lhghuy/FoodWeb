package com.example.FoodHKD.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TableDetails")
public class TableDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableDetailId;

    @ManyToOne
    @JoinColumn(name = "tableID")
    @JsonBackReference
    private TableEntity table;

    @ManyToOne
    @JoinColumn(name = "foodID")
    private FoodItem foodItem;

    private Integer quantity;
    private BigDecimal totalPrice;

    public Integer getTableDetailId() {
        return tableDetailId;
    }

    public void setTableDetailId(Integer tableDetailId) {
        this.tableDetailId = tableDetailId;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
