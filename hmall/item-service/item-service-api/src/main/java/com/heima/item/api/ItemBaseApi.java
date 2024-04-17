package com.heima.item.api;


import com.heima.feign.note.Api;

@Api(serviceName = "item-service")
public interface ItemBaseApi {

    public static final String ITEM_BASE_URL ="/items";

}
