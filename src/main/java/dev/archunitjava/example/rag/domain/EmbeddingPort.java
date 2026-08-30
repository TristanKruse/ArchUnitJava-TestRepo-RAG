package dev.archunitjava.example.rag.domain;

import java.util.List;

public interface EmbeddingPort {
    List<Double> embed(String text);
}

