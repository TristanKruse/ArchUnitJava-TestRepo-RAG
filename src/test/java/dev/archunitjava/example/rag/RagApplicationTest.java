package dev.archunitjava.example.rag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.archunitjava.example.rag.bootstrap.RagApplication;
import org.junit.jupiter.api.Test;

final class RagApplicationTest {
    @Test
    void answersUsingGroundedDocuments() {
        var response = RagApplication.create().search("Why architecture tests?");

        assertEquals("Answer grounded in: Architecture rules make boundaries executable.",
                response.answer());
        assertEquals(2, response.sourceIds().size());
        assertTrue(response.sourceIds().contains("architecture"));
    }
}

