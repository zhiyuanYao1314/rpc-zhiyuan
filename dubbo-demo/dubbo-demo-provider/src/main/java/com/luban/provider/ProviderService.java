package com.luban.provider;

import com.luban.api.ProviderServiceInterface;
import com.luban.api.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class ProviderService implements ProviderServiceInterface {

    public User getUser() {
        return new User("周瑜");
    }
}
