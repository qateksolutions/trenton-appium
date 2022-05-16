package automation_test;

import org.junit.Test;
import utilities.ServerManager;


public class TestDummyApp {
    ServerManager serverManager = new ServerManager();
    @Test
    public void run() {
        serverManager.startAppiumServer();
        serverManager.stopAppiumServer();
    }
}
