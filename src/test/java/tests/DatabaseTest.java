package tests;

import database.DatabaseUtil;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseTest {

    @Test
    public void verifyOrderStatus() {

        String status =
                DatabaseUtil.getOrderStatus(
                        "ORD1001");

        Assert.assertEquals(
                status,
                "CONFIRMED");
    }
}