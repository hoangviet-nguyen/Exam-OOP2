package tests;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleSystemTest {

    ArrayList<Module> variousModules = new ArrayList<>(List.of(
            new Module("webeC", 3, "Ergänzungen"),
            new Module("oopI1", 3, "Programmierung"),
            new Module("oopI2", 3, "Programmierung"),
            new Module("pro2I", 6, "Projekte"),
            new Module("agrh", 2, "Kommunikation"),
            new Module("cose", 6, "Ergänzungen"),
            new Module("blch", 3, "Ergänzungen"),
            new Module("Bachelor-Thesis", 12, "Projekte")));

    ArrayList<Module> programmingModules = new ArrayList<>(List.of(
            new Module("oopI1", 3, "Programmierung"),
            new Module("oopI2", 3, "Programmierung"),
            new Module("cpib", 3, "Programmierung"),
            new Module("prcpp", 3, "Programmierung"),
            new Module("algd2", 3, "Programmierung"),
            new Module("algd1", 3, "Programmierung")));

    @Test
    void sortModuleGroup() {
        var system = new ModuleSystem(variousModules);

        system.sort();

        assertEquals("Ergänzungen", system.getModules().get(0).getModuleGroup());
        assertEquals("Ergänzungen", system.getModules().get(1).getModuleGroup());
        assertEquals("Ergänzungen", system.getModules().get(2).getModuleGroup());
        assertEquals("Kommunikation", system.getModules().get(3).getModuleGroup());
        assertEquals("Programmierung", system.getModules().get(4).getModuleGroup());
        assertEquals("Programmierung", system.getModules().get(5).getModuleGroup());
        assertEquals("Projekte", system.getModules().get(6).getModuleGroup());
        assertEquals("Projekte", system.getModules().get(7).getModuleGroup());
    }

    @Test
    void sortModuleGroupEcts() {
        var system = new ModuleSystem(variousModules);

        system.sort();

        assertEquals("Ergänzungen", system.getModules().get(0).getModuleGroup());
        assertEquals(6, system.getModules().get(0).getEcts());
        assertEquals("Ergänzungen", system.getModules().get(1).getModuleGroup());
        assertEquals(3, system.getModules().get(1).getEcts());
        assertEquals("Ergänzungen", system.getModules().get(2).getModuleGroup());
        assertEquals(3, system.getModules().get(2).getEcts());
        assertEquals(2, system.getModules().get(3).getEcts());
        assertEquals("Programmierung", system.getModules().get(4).getModuleGroup());
        assertEquals(3, system.getModules().get(4).getEcts());
        assertEquals("Programmierung", system.getModules().get(5).getModuleGroup());
        assertEquals(3, system.getModules().get(5).getEcts());
        assertEquals("Projekte", system.getModules().get(6).getModuleGroup());
        assertEquals(12, system.getModules().get(6).getEcts());
        assertEquals("Projekte", system.getModules().get(7).getModuleGroup());
        assertEquals(6, system.getModules().get(7).getEcts());
    }

    @Test
    void sortName() {
        var system = new ModuleSystem(programmingModules);

        system.sort();

        assertEquals("algd1", system.getModules().get(0).getName());
        assertEquals("algd2", system.getModules().get(1).getName());
        assertEquals("cpib", system.getModules().get(2).getName());
        assertEquals("oopI1", system.getModules().get(3).getName());
        assertEquals("oopI2", system.getModules().get(4).getName());
        assertEquals("prcpp", system.getModules().get(5).getName());
    }
}
