package gl.linpeng.ci.validate;

import gl.linpeng.ci.enums.AppVersionEnum;
import gl.linpeng.ci.enums.ProviderEnum;
import gl.linpeng.ci.enums.RuntimeEnum;
import gl.linpeng.ci.model.CIModel;

/**
 * Sidecar checker
 * <ul>
 * <li>version check</li>
 * <li>provider check</li>
 * <li>runtime check</li>
 * <ul/>
 *
 * @author lin.peng
 * @since v1alpha
 */
public class SidecarChecker {
    /**
     * Check CI file is valid
     *
     * @param ciModel
     */
    public static void checkSupported(CIModel ciModel) {
        // version check
        checkVersionSupported(ciModel);
        // provider supported
        checkProviderSupported(ciModel);
        // runtime supported
        checkRuntimeSupported(ciModel);
    }

    private static void checkRuntimeSupported(CIModel ciModel) {
        boolean isContains = false;
        String ciModelRuntime = ciModel.getRuntime().split("-")[0];
        for (RuntimeEnum runtime : RuntimeEnum.values()) {
            if (runtime.toString().equalsIgnoreCase(ciModelRuntime)) {
                isContains = true;
                break;
            }
        }

        if (!isContains) {
            throw new UnsupportedOperationException("Unsupported runtime " + ciModelRuntime);
        }
    }

    private static void checkProviderSupported(CIModel ciModel) {
        boolean isContains = false;
        String ciModelProvider = ciModel.getProvider();
        for (ProviderEnum provider : ProviderEnum.values()) {
            if (provider.toString().equalsIgnoreCase(ciModelProvider)) {
                isContains = true;
                break;
            }
        }

        if (!isContains) {
            throw new UnsupportedOperationException("Unsupported provider " + ciModelProvider);
        }
    }

    private static void checkVersionSupported(CIModel ciModel) {
        boolean isContains = false;
        String ciModelApiVersion = ciModel.getApiVersion();
        for (AppVersionEnum version : AppVersionEnum.values()) {
            if (version.toString().equalsIgnoreCase(ciModelApiVersion)) {
                isContains = true;
                break;
            }
        }

        if (!isContains) {
            throw new UnsupportedOperationException("Unsupported appVersion " + ciModelApiVersion);
        }
    }
}
