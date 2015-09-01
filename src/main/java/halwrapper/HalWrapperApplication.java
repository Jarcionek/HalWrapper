package halwrapper;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HalWrapperApplication extends Application<HalWrapperConfiguration> {

    @Override
    public void initialize(Bootstrap<HalWrapperConfiguration> halWrapperConfigurationBootstrap) { }

    @Override
    public void run(HalWrapperConfiguration halWrapperConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new HalWrapperResource());
    }

}
