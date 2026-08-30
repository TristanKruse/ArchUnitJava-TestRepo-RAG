package dev.archunitjava.example.rag.infrastructure;

import dev.archunitjava.example.rag.api.SearchResponse;

/** Deliberate violation: an infrastructure adapter depends on an API response DTO. */
public final class LeakyAuditAdapter {
    public String message(SearchResponse response) {
        return "answered from " + response.sourceIds().size() + " source(s)";
    }
}

