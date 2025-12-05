package com.unimib.worldnews25.model;

import java.util.Objects;

public class ArticleSource {
    private String id;
    private String name;

    public ArticleSource(String id, String name) {
        setId(id);
        setName(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleSource that = (ArticleSource) o;
        boolean e = Objects.equals(id, that.id) && Objects.equals(name, that.name);
        return e;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
