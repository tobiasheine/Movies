import com.novoda.buildproperties.Entries
import org.gradle.api.GradleException
import org.gradle.api.Project

class ProjectEntries extends Entries {

    private final Project project

    static ProjectEntries from(Project project) {
        return new ProjectEntries(project)
    }

    private ProjectEntries(Project project) {
        this.project = project
    }

    @Override
    boolean contains(String key) {
        return project.hasProperty(key)
    }

    @Override
    protected Object getValueAt(String key) {
        if (project.hasProperty(key)) {
            return project.property(key)
        }
        throw new GradleException("No value defined for property '$key' via command line")
    }

    @Override
    File getParentFile() {
        return project.rootDir
    }

    @Override
    Enumeration<String> getKeys() {
        return Collections.enumeration(project.properties.keySet())
    }
}
