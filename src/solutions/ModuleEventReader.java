package modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModuleEventReader {

    public static List<ModuleEvent> read(InputStream in) throws IOException {
        List<ModuleEvent> moduleEvents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;

            line = reader.readLine();
            if (line == null) {
                return moduleEvents;
            }
            String[] headerFields = line.split("\t");
            if (headerFields.length != 5 ||
                    !headerFields[0].equals("Name") ||
                    !headerFields[1].equals("ECTS") ||
                    !headerFields[2].equals("Module group") ||
                    !headerFields[3].equals("Module class") ||
                    !headerFields[4].equals("Semester")) {
                throw new IOException("Invalid format");
            }

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length != 5) {
                    throw new IOException("Invalid format");
                }

                String name = fields[0];
                int ects = Integer.parseInt(fields[1]);
                String group = fields[2];
                String moduleClass = fields[3];
                int semester = Integer.parseInt(fields[4]);

                Module module = new Module(name, ects, group);
                ModuleEvent moduleEvent = new ModuleEvent(module, moduleClass, semester);
                moduleEvents.add(moduleEvent);
            }
        }catch (Exception e) {
            throw new IOException("Invalid format");
        }
        return moduleEvents;
    }





}

