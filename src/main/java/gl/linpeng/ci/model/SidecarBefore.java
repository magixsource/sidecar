package gl.linpeng.ci.model;

import java.util.List;

/**
 * Sidecar before model
 *
 * @author lin.peng
 * @since v1alpha
 */
public class SidecarBefore {
    private String name;
    private List script;
    private List runtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getScript() {
        return script;
    }

    public void setScript(List script) {
        this.script = script;
    }

    public List getRuntime() {
        return runtime;
    }

    public void setRuntime(List runtime) {
        this.runtime = runtime;
    }
}
