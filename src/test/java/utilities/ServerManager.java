package utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ServerManager {
    private static final Logger LOGGER = LogManager.getLogger(ServerManager.class);
    AppiumDriverLocalService server;

    public void startAppiumServer() {
        int port_number = Integer.parseInt(ReadConfigFiles.getPropertyValues("appiumServerPort"));
        String appium_node_path = ReadConfigFiles.getPropertyValues("appiumNodePath");
        String appium_js_path = ReadConfigFiles.getPropertyValues("appiumJsPath");
        String server_log = "logs" + File.separator + ReadConfigFiles.getPropertyValues("appiumServerLog");

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingPort(port_number);
        serviceBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
        serviceBuilder.usingDriverExecutable(new File(appium_node_path));
        serviceBuilder.withAppiumJS(new File(appium_js_path));
        serviceBuilder.withLogFile(new File(server_log));

        server = AppiumDriverLocalService.buildService(serviceBuilder);
        if (!server.isRunning()) {
            server.start();
        }
        LOGGER.debug("Starting Appium Server---------");
        if (server == null || !server.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started!!!");
        }
        LOGGER.info("Appium Server Started");

        server.clearOutPutStreams();
    }

    public void stopAppiumServer() {
        LOGGER.debug("Stopping Appium Server...........");
        if (server.isRunning()) {
            server.stop();
        }
        LOGGER.info("Appium Server Stopped");
    }
}
