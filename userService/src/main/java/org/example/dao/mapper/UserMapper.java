package org.example.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.dao.pojo.Order;
import org.example.dao.pojo.User;

@Mapper
public interface UserMapper {

    @Select("select  * from  tb_user where  id =#{id}")
    User getById(Long id);
}
