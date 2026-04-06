package com.transport.ReportsApis.Entity;

import java.io.Serializable;
import java.util.Objects;

public class PodLineItemsId implements Serializable {

    private String document;
    private String productId;

    public PodLineItemsId() {}

    public PodLineItemsId(String document, String productId) {
        this.document = document;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PodLineItemsId)) return false;
        PodLineItemsId that = (PodLineItemsId) o;
        return Objects.equals(document, that.document) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, productId);
    }
}
