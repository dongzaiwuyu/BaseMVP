package com.don.basemvp.model;

import com.don.basemvp.bean.TestBean;
import com.don.basemvp.http.HttpServiceFactory;
import com.don.basemvp.http.TestService;

public class TestModel extends BaseModel<TestService, TestBean>{

    @Override
    public TestService getApi() {
        return HttpServiceFactory.getHttpService(TestService.class);
    }

    @Override
    public TestBean getBean() {
        return null;
    }
}
