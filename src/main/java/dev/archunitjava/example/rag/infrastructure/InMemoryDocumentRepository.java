package dev.archunitjava.example.rag.infrastructure;

import dev.archunitjava.example.rag.domain.Document;
import dev.archunitjava.example.rag.domain.DocumentRepository;
import java.util.List;

public final class InMemoryDocumentRepository implements DocumentRepository {
    private final List<Document> documents;

    public InMemoryDocumentRepository(List<Document> documents) {
        this.documents = List.copyOf(documents);
    }

    @Override
    public List<Document> findNearest(List<Double> embedding, int limit) {
        return documents.stream().limit(limit).toList();
    }

    public int size() {
        return documents.size();
    }
}

