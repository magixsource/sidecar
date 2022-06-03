package gl.linpeng.ci.model;

import java.util.List;
import java.util.Map;

/**
 * CI model
 *
 * @author lin.peng
 * @since v1alpha
 */
public class CIModel {
    // core
    private String apiVersion;

    // application name
    private String name;
    private String description;
    private String provider;
    private String runtime;

    private List<CIStage> stages;

    // optional
    private List<String> before;
    private List<Map> variables;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<CIStage> getStages() {
        return stages;
    }

    public void setStages(List<CIStage> stages) {
        this.stages = stages;
    }

    public List<String> getBefore() {
        return before;
    }

    public void setBefore(List<String> before) {
        this.before = before;
    }

    public List<Map> getVariables() {
        return variables;
    }

    public void setVariables(List<Map> variables) {
        this.variables = variables;
    }
}
