package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * CMS (Content Management System) Page Object Model - V1
 * Encapsulates all CMS-related API endpoints (Pages, Sliders, Slides)
 */
public class CmsPage extends ApiClient {

    private static final String CMS_PAGES_ENDPOINT = "/cms/pages";
    private static final String CMS_SLIDERS_ENDPOINT = "/cms/sliders";
    private static final String CMS_SLIDER_SLIDES_ENDPOINT = "/cms/sliders/{slider_id}/slides";
    private static final String CMS_SLIDE_BY_ID_ENDPOINT = "/cms/sliders/{slider_id}/slides/{slide_id}";

    // CMS Pages
    public Response listCmsPages() {
        return withoutAuth()
                .get(CMS_PAGES_ENDPOINT);
    }

    // Sliders
    public Response getSliders() {
        return withoutAuth()
                .get(CMS_SLIDERS_ENDPOINT);
    }

    public Response getSliderSlides(String sliderId) {
        Map<String, Object> pathParams = Map.of("slider_id", sliderId);
        return withoutAuth()
                .withPathParams(pathParams)
                .get(CMS_SLIDER_SLIDES_ENDPOINT);
    }

    // Slides
    public Response getSlide(String sliderId, String slideId) {
        Map<String, Object> pathParams = Map.of("slider_id", sliderId, "slide_id", slideId);
        return withoutAuth()
                .withPathParams(pathParams)
                .get(CMS_SLIDE_BY_ID_ENDPOINT);
    }
}
