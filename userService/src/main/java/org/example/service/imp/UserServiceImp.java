package org.example.service.imp;



import org.example.dao.mapper.UserMapper;
import org.example.dao.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public User getById(Long id)  {

        return  userMapper.getById(id) ;
    }

}
