package dev.archunitjava.example.rag.api;

import java.util.List;

public record SearchResponse(String answer, List<String> sourceIds) {
    public SearchResponse {
        sourceIds = List.copyOf(sourceIds);
    }
}

