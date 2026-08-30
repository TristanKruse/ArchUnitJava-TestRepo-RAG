package dev.archunitjava.example.rag.application;

import dev.archunitjava.example.rag.domain.Document;
import dev.archunitjava.example.rag.domain.DocumentRepository;
import dev.archunitjava.example.rag.domain.EmbeddingPort;
import dev.archunitjava.example.rag.domain.SearchResult;
import java.util.List;

public final class RagSearchService {
    private final DocumentRepository documents;
    private final EmbeddingPort embeddings;

    public RagSearchService(DocumentRepository documents, EmbeddingPort embeddings) {
        this.documents = documents;
        this.embeddings = embeddings;
    }

    public SearchResult answer(String question) {
        List<Document> sources = documents.findNearest(embeddings.embed(question), 3);
        String evidence = sources.isEmpty() ? "No relevant document" : sources.getFirst().content();
        return new SearchResult("Answer grounded in: " + evidence, sources);
    }
}

