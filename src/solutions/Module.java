package modules;

import static java.util.Objects.requireNonNull;

/**
 * Ein Modul, wie z. B. oopI2 an der Hochschule für Technik.
 * DIESE KLASSE DÜRFEN SIE NICHT ÄNDERN.
 */
public class Module {

    private final String name;
    private final int ects;
    private final String moduleGroup;

    public Module(String name, int ects, String moduleGroup) {
        this.name = requireNonNull(name);
        this.ects = ects;
        this.moduleGroup = requireNonNull(moduleGroup);
    }

    public String getName() {
        return name;
    }

    public int getEcts() {
        return ects;
    }

    public String getModuleGroup() {
        return moduleGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Module module)) {
            return false;
        } else if (ects != module.ects) {
            return false;
        } else if (!name.equals(module.name)) {
            return false;
        } else {
            return moduleGroup.equals(module.moduleGroup);
        }
    }

    @Override
    public String toString() {
       return name + " (" + ects + " ECTS), " + moduleGroup;
    }
}
