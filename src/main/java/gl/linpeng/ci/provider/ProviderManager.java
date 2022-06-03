package gl.linpeng.ci.provider;

import gl.linpeng.ci.enums.ProviderEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Ci provider manager
 *
 * @author lin.peng
 * @since v1alpha
 */
public class ProviderManager {
    private static final Map<String, CIProvider> register = new HashMap<>();

    public ProviderManager() {
        register.put(ProviderEnum.Gitlab.toString().toLowerCase(), new GitlabProvider());
    }

    /**
     * Get provider by name
     *
     * @param providerName supported provider name
     * @return Ci provider instance
     */
    public CIProvider get(String providerName) {
        return register.get(providerName.toLowerCase());
    }
}
