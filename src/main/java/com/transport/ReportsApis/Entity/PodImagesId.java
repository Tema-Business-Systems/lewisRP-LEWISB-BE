package com.transport.ReportsApis.Entity;

import java.io.Serializable;
import java.util.Objects;

public class PodImagesId implements Serializable {

    private String document;
    private String contentUrl;

    public PodImagesId() {}

    public PodImagesId(String document, String contentUrl) {
        this.document = document;
        this.contentUrl = contentUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PodImagesId)) return false;
        PodImagesId that = (PodImagesId) o;
        return Objects.equals(document, that.document) &&
                Objects.equals(contentUrl, that.contentUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, contentUrl);
    }
}
