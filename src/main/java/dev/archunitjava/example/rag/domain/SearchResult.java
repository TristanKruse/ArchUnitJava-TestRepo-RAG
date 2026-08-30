package dev.archunitjava.example.rag.domain;

import java.util.List;

public record SearchResult(String answer, List<Document> sources) {
    public SearchResult {
        sources = List.copyOf(sources);
    }
}

