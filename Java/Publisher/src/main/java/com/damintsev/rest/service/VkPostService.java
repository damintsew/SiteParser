package com.damintsev.rest.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
@Component
public abstract class VkPostService  {

    public Object loadConfig() {
        return new Object();
    }

    public List loadUnpostedData() {
        
        return new ArrayList<>();
    } 
}
