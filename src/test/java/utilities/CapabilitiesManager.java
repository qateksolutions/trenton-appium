package utilities;

import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class CapabilitiesManager {
    private static final Logger LOGGER = LogManager.getLogger(CapabilitiesManager.class);

    public DesiredCapabilities getCaps() {
        // Reading values from the config files
        String deviceName = ReadConfigFiles.getPropertyValues("deviceName");
        String udId = ReadConfigFiles.getPropertyValues("udId");
        String androidAutomationName = ReadConfigFiles.getPropertyValues("androidAutomationName");
        String appPackage = ReadConfigFiles.getPropertyValues("androidAppPackage");
        String appActivity = ReadConfigFiles.getPropertyValues("androidAppActivity");
        String appName = ReadConfigFiles.getPropertyValues("androidAppName");
        String appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                File.separator + "resources" + File.separator + "apps" + File.separator + appName;

        LOGGER.debug("App Location is: " + appPath);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, androidAutomationName);
        caps.setCapability(MobileCapabilityType.UDID, udId);
        //caps.setCapability(MobileCapabilityType.APP, appPath);
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);

        return caps;
    }
}
