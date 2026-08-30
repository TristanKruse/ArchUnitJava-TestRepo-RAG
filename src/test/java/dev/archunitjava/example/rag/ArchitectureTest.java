package dev.archunitjava.example.rag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.archunitjava.cli.CliExitCode;
import dev.archunitjava.cli.CliRunner;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

final class ArchitectureTest {
    private static final Path ROOT = Path.of("").toAbsolutePath().normalize();

    @Test
    void healthyDomainAndApplicationBoundariesPass() {
        Invocation result = run("check", "archunitjava-healthy.properties", "--result-format", "json");

        assertEquals(CliExitCode.SUCCESS.code(), result.exit(), result.error());
        assertTrue(result.output().contains("\"status\":\"PASSED\""));
        assertTrue(result.error().isEmpty());
    }

    @Test
    void deliberateBoundaryViolationsAreDetectedWithConcreteTypes() {
        Invocation result = run(
                "check", "archunitjava-violations.properties", "--result-format", "json");

        assertEquals(CliExitCode.POLICY_VIOLATION.code(), result.exit(), result.error());
        assertTrue(result.output().contains("\"status\":\"FAILED\""));
        assertTrue(result.output().contains("API must not bypass the application layer"));
        assertTrue(result.output().contains("Infrastructure must not depend on delivery APIs"));
        assertTrue(result.output().contains("UnsafeSearchController"));
        assertTrue(result.output().contains("LeakyAuditAdapter"));
        assertTrue(result.error().isEmpty());
    }

    @Test
    void dependencyGraphCanBeRenderedForReview() {
        Invocation result = run(
                "graph", "archunitjava-healthy.properties", "--graph-format", "mermaid");

        assertEquals(CliExitCode.SUCCESS.code(), result.exit(), result.error());
        assertTrue(result.output().contains("flowchart LR"));
        assertTrue(result.output().contains("SearchController"));
        assertTrue(result.output().contains("RagSearchService"));
        assertTrue(result.error().isEmpty());
    }

    @Test
    void checkedInPoliciesValidateWithoutExecutingTargetCode() {
        Invocation result = run("validate-config", "archunitjava-healthy.properties");

        assertEquals(CliExitCode.SUCCESS.code(), result.exit(), result.error());
        assertTrue(result.output().contains("configuration valid"));
    }

    private static Invocation run(String command, String configuration, String... extra) {
        String[] arguments = new String[5 + extra.length];
        arguments[0] = command;
        arguments[1] = "--config";
        arguments[2] = ROOT.resolve(configuration).toString();
        arguments[3] = "--root";
        arguments[4] = ROOT.toString();
        System.arraycopy(extra, 0, arguments, 5, extra.length);
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        int exit = new CliRunner().run(arguments, output, error);
        return new Invocation(exit, output.toString(), error.toString());
    }

    private record Invocation(int exit, String output, String error) {}
}

