package dev.archunitjava.example.rag.domain;

import java.util.Objects;

public record Document(String id, String content) {
    public Document {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id must not be blank");
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("content must not be blank");
        }
        Objects.requireNonNull(content, "content");
    }
}

