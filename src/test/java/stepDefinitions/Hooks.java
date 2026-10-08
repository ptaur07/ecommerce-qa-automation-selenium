package stepDefinitions;

import Base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest {

    @Before
    public void setUpCucumber() {
        initializeDriver();
    }

    

	@After
    public void tearDownCucumber() {
        closeDriver();
    }

}