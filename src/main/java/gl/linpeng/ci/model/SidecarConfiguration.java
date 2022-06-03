package gl.linpeng.ci.model;

import java.util.List;

/**
 * Sidecar configuration model
 *
 * @author lin.peng
 * @since v1alpha
 */
public class SidecarConfiguration {

    private List<SidecarImage> images;
    private List<SidecarBefore> before;
    private List<SidecarStage> stage;

    public List<SidecarImage> getImages() {
        return images;
    }

    public void setImages(List<SidecarImage> images) {
        this.images = images;
    }

    public List<SidecarBefore> getBefore() {
        return before;
    }

    public void setBefore(List<SidecarBefore> before) {
        this.before = before;
    }

    public List<SidecarStage> getStage() {
        return stage;
    }

    public void setStage(List<SidecarStage> stage) {
        this.stage = stage;
    }
}
