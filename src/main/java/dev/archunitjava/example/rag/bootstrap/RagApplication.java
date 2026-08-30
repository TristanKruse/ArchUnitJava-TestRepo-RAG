package dev.archunitjava.example.rag.bootstrap;

import dev.archunitjava.example.rag.api.SearchController;
import dev.archunitjava.example.rag.application.RagSearchService;
import dev.archunitjava.example.rag.domain.Document;
import dev.archunitjava.example.rag.infrastructure.DeterministicEmbeddingAdapter;
import dev.archunitjava.example.rag.infrastructure.InMemoryDocumentRepository;
import java.util.List;

public final class RagApplication {
    private RagApplication() {}

    public static SearchController create() {
        var documents = new InMemoryDocumentRepository(List.of(
                new Document("architecture", "Architecture rules make boundaries executable."),
                new Document("retrieval", "Retrieval supplies grounded evidence.")));
        var search = new RagSearchService(documents, new DeterministicEmbeddingAdapter());
        return new SearchController(search);
    }
}

