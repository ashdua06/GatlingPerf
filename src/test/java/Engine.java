import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

public class Engine {

    public static void main(String[] args) {
        GatlingPropertiesBuilder props = new GatlingPropertiesBuilder()
                .simulationClass("test.java.com.airtel.performanceTests.shop.shopTestSimulation")
                .resourcesDirectory(IDEPathHelper.mavenResourcesDirectory.toString())
                .resultsDirectory(IDEPathHelper.resultsDirectory.toString())
                .binariesDirectory(IDEPathHelper.mavenBinariesDirectory.toString());
        Gatling.fromMap(props.build());
    }
}
