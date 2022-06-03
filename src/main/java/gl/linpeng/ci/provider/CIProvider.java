package gl.linpeng.ci.provider;

import gl.linpeng.ci.model.CIModel;
import gl.linpeng.ci.model.SidecarConfiguration;

import java.util.Map;

/**
 * CI provider interface
 *
 * @author lin.peng
 * @since v1alpha
 */
public interface CIProvider {
    /**
     * generate header of ci file
     *
     * @param ciModel sidecar ci model
     * @param config  sidecar config map
     * @param map     target ci model map
     */
    void generateHeader(CIModel ciModel, SidecarConfiguration config, Map<String, Object> map);

    /**
     * generate before of ci file
     *
     * @param ciModel sidecar ci model
     * @param config  sidecar config map
     * @param map     target ci model map
     */
    void generateBefore(CIModel ciModel, SidecarConfiguration config, Map<String, Object> map);

    /**
     * generate stages of ci file
     *
     * @param ciModel sidecar ci model
     * @param config  sidecar config map
     * @param map     target ci model map
     */
    void generateStages(CIModel ciModel, SidecarConfiguration config, Map<String, Object> map);
}
