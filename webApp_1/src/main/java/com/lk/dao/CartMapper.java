package com.lk.dao;

import com.lk.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartMapper {
    @Results({@Result(property = "userId",column = "user_id"),@Result(property = "productId",column = "product_id")})
    @Select("select * from mmall_cart where user_id = #{userId} and product_id = #{productId}")
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId,@Param("productId") Integer productId);

    @Results({@Result(property = "userId",column = "user_id"),@Result(property = "productId",column = "product_id")})
    @Insert("insert into mmall_cart(user_id,product_id,quantity,checked,create_time,update_time) values(#{cart.userId},#{cart.productId},#{cart.quantity},#{cart.checked},now(),now()) ")
    int insert(@Param("cart") Cart cart);

    @Update("update mmall_cart set quantity = #{cart.quantity},update_time = now() where user_id = #{cart.userId} and product_id = #{cart.productId}")
    int updateByQuantity(@Param("cart") Cart cart);

    @Results({@Result(property = "userId",column = "user_id"),@Result(property = "productId",column = "product_id")})
    @Select("select * from mmall_cart where user_id = #{userId}")
    List<Cart> selectCartByUserId(@Param("userId") Integer userId);

    @Delete("<script>delete from mmall_cart where user_id = #{userId} and product_id in <foreach item='item' open='(' separator=',' close=')' collection='#{productIdList}'>#{item} </foreach></script>")
    int deleteByUserIdProductIds(@Param("userId") Integer userId,@Param("productIdList") List<String> productIdList);

    @Select("select count(*) from mmall_cart where checked = 0 and user_id = #{userId}")
    int selectCartProductCheckedStatusByUserId(@Param("userId") Integer userId);

    @Update("<script> update mmall_cart set checked = #{checked},update_time = now() where user_id = #{userId} <when test='#{productId} != null'> and product_id = #{productId} </when> </script>")
    int checkeOrUncheckedProductId(@Param("userId") Integer userId,@Param("checked") Integer checked,@Param("productId") Integer productId);

    @Select("select sum(quantity) from mmall_cart where user_id = #{userId}")
    Integer selectCartProductCount(@Param("userId") Integer userId);
}
