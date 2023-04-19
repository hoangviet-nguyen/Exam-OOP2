package tests;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class ModuleEventReaderTest {

    String regularFile = """
            Name\tECTS\tModule group\tModule class\tSemester
            oopI2\t3\tProgrammierung\tIbb1\t2
            pro2I\t6\tProjekte\tIa\t2
            apm\t3\tVertiefungen\tIw\t6
            """;

    String incompleteFile = """
            Name\tECTS\tModule group\tModule class\tSemester
            oopI2\t3\tProgrammierung\tIbb1\t2
            pro2I\t6\tProjekte\tIa
            """;

    String invalidFile = """
            Name\tECTS\tModule group\tModule class\tSemester
            oopI2\t3\tProgrammierung\tIbb1\t2
            pro2I\t6\tProjekte\tIa\tZwei
            """;

    @Test
    void read() throws IOException {
        var bytes = new ByteArrayInputStream(regularFile.getBytes(UTF_8));

        var moduleEvents = ModuleEventReader.read(bytes);

        assertNotNull(moduleEvents);
        assertEquals(3, moduleEvents.size());

        assertEquals(new Module("oopI2", 3, "Programmierung"), moduleEvents.get(0).getModule());
        assertEquals("Ibb1", moduleEvents.get(0).getModuleClass());
        assertEquals(2, moduleEvents.get(0).getSemester());

        assertEquals(new Module("pro2I", 6, "Projekte"), moduleEvents.get(1).getModule());
        assertEquals("Ia", moduleEvents.get(1).getModuleClass());
        assertEquals(2, moduleEvents.get(1).getSemester());

        assertEquals(new Module("apm", 3, "Vertiefungen"), moduleEvents.get(2).getModule());
        assertEquals("Iw", moduleEvents.get(2).getModuleClass());
        assertEquals(6, moduleEvents.get(2).getSemester());
    }

    @Test
    void readEmpty() throws IOException {
        var moduleEvents = ModuleEventReader.read(new EmptyStream());

        assertNotNull(moduleEvents);
        assertEquals(0, moduleEvents.size());
    }

    @Test
    void streamClosed() throws IOException {
        var stream = new EmptyStream();

        ModuleEventReader.read(stream);

        assertTrue(stream.closed);
    }

    @Test
    void streamClosedAfterException() {
        var stream = new ThrowingStream();

        assertThrows(IOException.class, () -> {
            ModuleEventReader.read(stream);
        });
        assertTrue(stream.closed);
    }

    @Test
    void readIncomplete() {
        var bytes = new ByteArrayInputStream(incompleteFile.getBytes(UTF_8));

        assertThrows(IOException.class, () -> {
            ModuleEventReader.read(bytes);
        });
    }

    @Test
    void readInvalid() {
        var bytes = new ByteArrayInputStream(invalidFile.getBytes(UTF_8));

        assertThrows(IOException.class, () -> {
            ModuleEventReader.read(bytes);
        });
    }

    static class EmptyStream extends InputStream {
        boolean closed;

        public int read() {
            return -1;
        }

        public void close() {
            closed = true;
        }
    }

    static class ThrowingStream extends InputStream {
        boolean closed;

        public int read() throws IOException {
            throw new IOException();
        }

        public void close() {
            closed = true;
        }
    }
}
