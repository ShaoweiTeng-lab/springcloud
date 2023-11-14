package org.example.dao.mapper;



import org.apache.ibatis.annotations.*;
import org.example.dao.pojo.Order;

@Mapper
public interface OrderMapper {
    @Results({
            @Result(property = "userId",column = "user_id")
    })
    @Select("select  * from  tb_order where  id =#{id}")
    Order getById(Long id);
}
