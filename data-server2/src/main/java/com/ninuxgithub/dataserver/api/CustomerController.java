package com.ninuxgithub.dataserver.api;


import com.ninuxgithub.dataserver.model.Customer;
import com.ninuxgithub.dataserver.service.CustomerService;
import com.ninuxgithub.dataserver.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {


    @Autowired
    CustomerService customerService;


    /**
     * 前端用户登录
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/api/registry", method = RequestMethod.POST)
    public Map<String, Object> registry(@RequestBody Customer customer) {
        Map<String, Object> resultMap = new HashMap<>();
        if (null != customer) {
            if (StringUtils.isBlank(customer.getUserName())) {
                resultMap.put("userName", "登录用户名称不能为空");
            } else if (StringUtils.isBlank(customer.getPassword())) {
                resultMap.put("password", "登录密码不能为空");
            } else if (StringUtils.isBlank(customer.getRepassword())) {
                resultMap.put("password", "登录重复密码不能为空");
            } else if (StringUtils.isBlank(customer.getPhoneNum())) {
                resultMap.put("phoneNum", "电话号码不能为空");
            } else if (!customer.getPassword().equals(customer.getRepassword())) {
                resultMap.put("password", "重复输入密码不一致");
            }

            Customer customerByUserName = customerService.findCustomerByUserName(customer.getUserName());
            if (null != customerByUserName) {
                resultMap.put("userName", "该用户名称已存在");
            }

            if (resultMap.size() == 0) {
                customer.setPassword(Md5Util.getMD5(customer.getPassword()));
                Customer reposCustomer = customerService.saveCustomer(customer);
                reposCustomer.setPassword(null);
                resultMap.put("loginCustomer", reposCustomer);
            }

        }
        return resultMap;

    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody Customer customer) {
        Map<String, Object> resultMap = new HashMap<>();
        System.out.println(customer);
        if (customer.getUserName() == null) {
            return null;
        }
        if (null != customer) {
            if (StringUtils.isBlank(customer.getUserName())) {
                resultMap.put("userName", "登录用户名称不能为空");
            } else if (StringUtils.isBlank(customer.getPassword())) {
                resultMap.put("password", "登录密码不能为空");
            }
            Customer customerByUserName = customerService.findCustomerByUserName(customer.getUserName());
            if (null == customerByUserName) {
                resultMap.put("userName", "该用户不存在");
            }
            if (!Md5Util.getMD5(customer.getPassword()).equals(customerByUserName.getPassword())) {
                resultMap.put("password", "密码不匹配");
            }

            if (resultMap.size() == 0) {
                customerByUserName.setPassword(null);
                resultMap.put("loginCustomer", customerByUserName);
            }

        }
        return resultMap;

    }


}
