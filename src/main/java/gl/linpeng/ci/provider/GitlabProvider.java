package gl.linpeng.ci.provider;

import gl.linpeng.ci.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gitlab provider : to generate gitlab-domain only ci specification
 *
 * @author lin.peng
 * @since v1alpha
 */
public class GitlabProvider implements CIProvider {

    public static final String KEY_GITLAB_CI_BEFORE = "before_script";
    public static final String KEY_GITLAB_CI_IMAGE = "image";
    public static final String KEY_GITLAB_CI_STAGES = "stages";
    public static final String KEY_GITLAB_CI_STAGE = "stage";
    public static final String KEY_GITLAB_CI_ONLY = "only";
    public static final String KEY_GITLAB_CI_EXPECT = "expect";
    public static final String KEY_GITLAB_CI_SCRIPT = "script";

    @Override
    public void generateHeader(CIModel ciModel, SidecarConfiguration config, Map<String, Object> map) {
        String runtime = ciModel.getRuntime();
        String imageName = getImage(config, runtime).getName();
        map.put(KEY_GITLAB_CI_IMAGE, imageName);
    }

    @Override
    public void generateBefore(CIModel ciModel, SidecarConfiguration config, Map<String, Object> gitlabCI) {
        if (gitlabCI.get(KEY_GITLAB_CI_BEFORE) == null) {
            gitlabCI.put(KEY_GITLAB_CI_BEFORE, new ArrayList<>());
        }
        List list = (List) gitlabCI.get(KEY_GITLAB_CI_BEFORE);
        String runtime = ciModel.getRuntime();
        SidecarBefore before = getBefore(config, runtime);
        list.add(before.getName());

        List scripts = before.getScript();
        for (Object script : scripts) {
            String[] value = script.toString().split(":");
            gitlabCI.put(value[0], value[1]);
        }
    }

    @Override
    public void generateStages(CIModel ciModel, SidecarConfiguration config, Map<String, Object> gitlabCI) {
        List<CIStage> stages = ciModel.getStages();
        List<String> list = new ArrayList<>(stages.size());
        String runtime = ciModel.getRuntime();
        for (CIStage stage : stages) {
            list.add(stage.getStage());
            Map<String, Object> stageMap = new HashMap<>();
            stageMap.put(KEY_GITLAB_CI_STAGE, stage.getStage());

            SidecarStage sidecarStage = getStage(config, runtime, stage.getStage());
            stageMap.put(KEY_GITLAB_CI_SCRIPT, sidecarStage.getScript());

            if (stage.getOnly() != null && !stage.getOnly().isEmpty()) {
                stageMap.put(KEY_GITLAB_CI_ONLY, stage.getOnly());
            }
            if (stage.getExcept() != null && !stage.getExcept().isEmpty()) {
                stageMap.put(KEY_GITLAB_CI_EXPECT, stage.getExcept());
            }

            gitlabCI.put("sidecar-stage-" + stage.getStage(), stageMap);
        }

        gitlabCI.put(KEY_GITLAB_CI_STAGES, list);
    }

    private SidecarStage getStage(SidecarConfiguration config, String runtime, String stageName) {
        List<SidecarStage> sidecarStages = config.getStage();
        for (SidecarStage sidecarStage : sidecarStages) {
            // check stage match
            if (sidecarStage.getName().equalsIgnoreCase(stageName)) {
                List runtimeList = sidecarStage.getRuntime();
                for (Object supportedRuntime : runtimeList) {
                    if (supportedRuntime.toString().equalsIgnoreCase(runtime)) {
                        return sidecarStage;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Unsupported runtime " + runtime + " and " + stageName + ", Please contact sidecar admin.");
    }

    private SidecarImage getImage(SidecarConfiguration config, String runtime) {
        List<SidecarImage> images = config.getImages();
        for (SidecarImage image : images) {
            List runtimeList = image.getRuntime();
            for (Object supportedRuntime : runtimeList) {
                if (supportedRuntime.toString().equalsIgnoreCase(runtime)) {
                    return image;
                }
            }

        }
        throw new IllegalArgumentException("Unsupported runtime " + runtime + ", Please contact sidecar admin.");
    }

    private SidecarBefore getBefore(SidecarConfiguration config, String runtime) {
        List<SidecarBefore> beforeList = config.getBefore();
        for (SidecarBefore before : beforeList) {
            List runtimeList = before.getRuntime();
            for (Object supportedRuntime : runtimeList) {
                if (supportedRuntime.toString().equalsIgnoreCase(runtime)) {
                    return before;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported runtime " + runtime + ", Please contact sidecar admin.");
    }
}
