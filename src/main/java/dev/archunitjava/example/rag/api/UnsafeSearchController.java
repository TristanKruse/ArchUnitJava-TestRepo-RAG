package dev.archunitjava.example.rag.api;

import dev.archunitjava.example.rag.infrastructure.InMemoryDocumentRepository;

/** Deliberate violation: delivery code bypasses the application and domain boundaries. */
public final class UnsafeSearchController {
    private final InMemoryDocumentRepository documents;

    public UnsafeSearchController(InMemoryDocumentRepository documents) {
        this.documents = documents;
    }

    public int indexedDocuments() {
        return documents.size();
    }
}

