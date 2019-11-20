package com.bbg.purchaseplan.service.demo;

import com.bbg.purchaseplan.bo.request.demo.TestPageRequest;
import com.bbg.purchaseplan.bo.response.demo.TestResponse;
import com.bbg.purchaseplan.common.PageLayuiInfo;

import java.util.List;

public interface TestService {
    String test();

    List<TestResponse> query() throws Exception;

    PageLayuiInfo page(TestPageRequest request) throws Exception;
}
