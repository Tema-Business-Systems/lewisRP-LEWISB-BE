package com.transport.ReportsApis.Entity;

import java.io.Serializable;
import java.util.Objects;

public class PodProductsId implements Serializable {

    private String deliveryNum;
    private String product;

    public PodProductsId() {}

    public PodProductsId(String deliveryNum, String product) {
        this.deliveryNum = deliveryNum;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PodProductsId)) return false;
        PodProductsId that = (PodProductsId) o;
        return Objects.equals(deliveryNum, that.deliveryNum) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryNum, product);
    }
}
