package com.zhiyuan.provider;

import com.zhiyuan.api.ProviderServiceInterface;
import com.zhiyuan.api.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class ProviderService implements ProviderServiceInterface {

    public User getUser() {
        return new User("志远");
    }
}
