package modules;

import static java.util.Objects.requireNonNull;

/**
 * Ein Modulanlass, wie an der Hochschule für Technik.
 * DIESE KLASSE DÜRFEN SIE NICHT ÄNDERN.
 */
public class ModuleEvent {

    private final Module module;
    private final String moduleClass;
    private final int semester;

    public ModuleEvent(Module module, String moduleClass, int semester) {
        this.module = requireNonNull(module);
        this.moduleClass = requireNonNull(moduleClass);
        this.semester = semester;
    }

    public Module getModule() {
        return module;
    }

    public String getModuleClass() {
        return moduleClass;
    }

    public int getSemester() {
        return semester;
    }
}
