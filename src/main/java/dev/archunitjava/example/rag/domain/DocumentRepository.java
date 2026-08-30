package dev.archunitjava.example.rag.domain;

import java.util.List;

public interface DocumentRepository {
    List<Document> findNearest(List<Double> embedding, int limit);
}

