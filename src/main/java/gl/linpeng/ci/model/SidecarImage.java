package gl.linpeng.ci.model;

import java.util.List;

/**
 * Sidecar image model
 *
 * @author lin.peng
 * @since v1alpha
 */
public class SidecarImage {
    private String name;
    private List runtime;

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
}
