package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Menu Page Object Model - V1
 * Encapsulates all menu-related API endpoints
 */
public class MenuPage extends ApiClient {

    private static final String MENUS_ENDPOINT = "/menus";
    private static final String MENU_ITEMS_ENDPOINT = "/menus/{menuId}/items";

    public Response getMenus() {
        return withoutAuth()
                .get(MENUS_ENDPOINT);
    }

    public Response getMenuItems(String menuId, Map<String, Object> queryParams) {
        Map<String, Object> pathParams = Map.of("menuId", menuId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withQueryParams(queryParams)
                .get(MENU_ITEMS_ENDPOINT);
    }

    public Response getMenuItems(String menuId) {
        return getMenuItems(menuId, null);
    }
}
