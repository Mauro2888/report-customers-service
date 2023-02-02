package report.config;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(ApplicationLifeCycle.class);

    void onStart(@Observes StartupEvent startupEvent){
        LOGGER.info(" Powered by Quarkus");
        LOGGER.info("The application Number is starting with profile " + ConfigUtils.getProfiles());
    }

    void onStop(@Observes ShutdownEvent shutdownEvent){
        LOGGER.info("ShutDown application");
    }
}
