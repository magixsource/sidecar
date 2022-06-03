package gl.linpeng.ci.model;

import java.util.List;

/**
 * CI stage model
 *
 * @author lin.peng
 * @since v1alpha
 */
public class CIStage {
    // domain stage
    private String stage;
    private List<String> except;
    private List<String> only;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<String> getExcept() {
        return except;
    }

    public void setExcept(List<String> except) {
        this.except = except;
    }

    public List<String> getOnly() {
        return only;
    }

    public void setOnly(List<String> only) {
        this.only = only;
    }
}
