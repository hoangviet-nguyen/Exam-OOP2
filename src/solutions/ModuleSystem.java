package modules;

import java.util.*;
import java.util.stream.Collectors;

public class ModuleSystem {

    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleSystem(List<Module> modules) {
        this.modules.addAll(modules);
    }

    public List<Module> getModules() {
        return modules;
    }


    public void sort() {
        Collections.sort(modules, (m1, m2) -> {
            int x = m2.getModuleGroup().compareTo(m1.getModuleGroup());
            if (x == 0) {
                x = Integer.compare(m2.getEcts(), m1.getEcts());
            }
            if (m1.getEcts() == m2.getEcts()) {
                x = m1.getName().compareTo(m2.getName());
            }
            return x;
        });
        Comparator<Module> comparator = Comparator.comparing(Module::getModuleGroup).reversed().thenComparing(Module::getEcts).reversed();
           modules.sort(comparator);
    }
}
