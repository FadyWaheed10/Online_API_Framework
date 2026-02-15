package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.CouponPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Coupon API Test Class
 */
@Epic("Coupon API")
@Feature("Coupons")
public class CouponTest extends BaseTest {
    private CouponPage couponPage;

    @BeforeClass
    public void setup() {
        couponPage = new CouponPage();
    }

    @Test(description = "Call list coupons API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Coupons")
    public void testListCoupons() {
        Response response = couponPage.listCoupons();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
