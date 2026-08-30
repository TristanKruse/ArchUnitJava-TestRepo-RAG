package dev.archunitjava.example.rag.api;

import dev.archunitjava.example.rag.application.RagSearchService;

public final class SearchController {
    private final RagSearchService search;

    public SearchController(RagSearchService search) {
        this.search = search;
    }

    public SearchResponse search(String question) {
        var result = search.answer(question);
        return new SearchResponse(
                result.answer(), result.sources().stream().map(source -> source.id()).toList());
    }
}

