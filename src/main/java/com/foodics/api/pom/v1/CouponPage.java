package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Coupon Page Object Model - V1
 * Encapsulates all coupon-related API endpoints
 */
public class CouponPage extends ApiClient {

    private static final String COUPONS_ENDPOINT = "/coupons";
    private static final String DIGITAL_COUPONS_ENDPOINT = "/digital-coupons";

    public Response listCoupons() {
        return withoutAuth()
                .get(COUPONS_ENDPOINT);
    }

    public Response createDigitalCoupon(Object couponData) {
        return withoutAuth()
                .withBody(couponData)
                .post(DIGITAL_COUPONS_ENDPOINT);
    }
}
