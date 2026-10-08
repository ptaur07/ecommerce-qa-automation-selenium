package hooks;

import drivers.DriverFactory;
import utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks {

    @Before
    public void setUp() {

        DriverFactory.initDriver(
                ConfigReader.get("browser"));
    }

    @After
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}