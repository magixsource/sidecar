package gl.linpeng.ci.model;

import java.util.List;

/**
 * Sidecar stage model
 *
 * @author lin.peng
 * @since v1alpha
 */
public class SidecarStage {
    private String name;
    private List runtime;
    private List script;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getRuntime() {
        return runtime;
    }

    public void setRuntime(List runtime) {
        this.runtime = runtime;
    }

    public List getScript() {
        return script;
    }

    public void setScript(List script) {
        this.script = script;
    }
}
