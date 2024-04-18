package com.heima.pay.api;

import com.heima.pay.PayBaseApi;
import com.heima.pay.request.PayApplyQO;
import com.heima.pay.request.PayOrderFormQO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PayApi extends PayBaseApi {

    String PAY_BASE_URL="/pay";

    @PostMapping(PAY_BASE_URL)
    String applyPayOrder(@RequestBody PayApplyQO applyDTO);

    @PostMapping(PAY_BASE_URL+"{id}")
    void tryPayOrderByBalance(@PathVariable("id") Long id, @RequestBody PayOrderFormQO payOrderFormDTO);

}
