package cn.pwc.demo.service.Impl;

import cn.pwc.demo.dao.UserMapper;
import cn.pwc.demo.service.UserService;
import cn.pwc.demo.util.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author boom
 * @description ${DESCRIPTION}
 * @create 2017-05-14 16:11
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Override
    public Integer login(String name, String password, HttpSession httpSession) {
        Integer info = userMapper.login(name,password);
        if(info != null){
            Cache cache = ehcacheUtil.getCache("loginCache");
            cache.put(httpSession.getId(),httpSession);
        }else{
            info = -1;
        }
        return info;
    }
}
