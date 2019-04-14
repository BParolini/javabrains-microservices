package org.bparolini.moviecatalogservice.models;

import java.util.List;

public class Catalog {

    private List<CatalogItem> items;

    public Catalog() { super(); }

    public Catalog(List<CatalogItem> items) {
        this.items = items;
    }

    public List<CatalogItem> getItems() {
        return items;
    }

    public void setItems(List<CatalogItem> items) {
        this.items = items;
    }
}
