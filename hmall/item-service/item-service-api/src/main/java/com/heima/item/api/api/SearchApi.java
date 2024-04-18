package com.heima.item.api.api;

import com.heima.item.api.ItemBaseApi;
import com.heima.item.api.request.ItemPageQueryQO;
import com.heima.item.api.response.ItemResp;
import com.hmall.common.domain.PageDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

public interface SearchApi extends ItemBaseApi {

    String SEARCH_BASE_URL="search";

    @GetMapping(SEARCH_BASE_URL+"/list")
    PageDTO<ItemResp> search(ItemPageQueryQO query);

}
