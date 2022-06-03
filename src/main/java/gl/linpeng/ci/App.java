package gl.linpeng.ci;

import gl.linpeng.ci.model.CIModel;
import gl.linpeng.ci.model.SidecarConfiguration;
import gl.linpeng.ci.provider.CIProvider;
import gl.linpeng.ci.provider.ProviderManager;
import gl.linpeng.ci.validate.SidecarChecker;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Yaml yaml = new Yaml(new Constructor(CIModel.class));
        CIModel ciModel = yaml.load(App.class.getClassLoader().getResourceAsStream("CI-Specification.yaml"));
        String content = generateFile(ciModel);
        System.out.println(content);
    }

    private static String generateFile(CIModel ciModel) {
        // check supported
        SidecarChecker.checkSupported(ciModel);

        // generate content begin
        String content = generate(ciModel);
        return content;
    }

    private static String generate(CIModel ciModel) {
        ProviderManager providerManager = new ProviderManager();
        String providerName = ciModel.getProvider();
        CIProvider provider = providerManager.get(providerName);

        Map<String, Object> targetCIMap = new HashMap<>();

        Yaml configYaml = new Yaml(new Constructor(SidecarConfiguration.class));
        SidecarConfiguration config = configYaml.load(App.class.getClassLoader().getResourceAsStream("sidecar-config.yml"));

        // generate header
        provider.generateHeader(ciModel, config, targetCIMap);
        // generate before
        provider.generateBefore(ciModel, config, targetCIMap);
        // generate stages
        provider.generateStages(ciModel, config, targetCIMap);

        // render to content
        return new Yaml().dump(targetCIMap);
    }

}
