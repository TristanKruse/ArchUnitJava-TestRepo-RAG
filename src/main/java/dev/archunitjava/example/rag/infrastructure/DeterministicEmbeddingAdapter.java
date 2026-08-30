package dev.archunitjava.example.rag.infrastructure;

import dev.archunitjava.example.rag.domain.EmbeddingPort;
import java.util.List;

public final class DeterministicEmbeddingAdapter implements EmbeddingPort {
    @Override
    public List<Double> embed(String text) {
        return List.of((double) text.length(), (double) Math.floorMod(text.hashCode(), 997));
    }
}

