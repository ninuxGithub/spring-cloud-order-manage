package com.ninuxgithub.api.service.impl;

import com.ninuxgithub.api.service.ApiModelService;
import com.ninuxgithub.manager.ModelInfoManager;
import com.ninuxgithub.model.ModelInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by lorne on 2017/11/13
 */
@Service
public class ApiModelServiceImpl implements ApiModelService {


    @Override
    public List<ModelInfo> onlines() {
        return ModelInfoManager.getInstance().getOnlines();
    }


}
