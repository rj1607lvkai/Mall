package com.lk.dao;

import com.lk.pojo.Shipping;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShippingMapper {


    @Insert("insert into mmall_shipping(user_id,receiver_name,receiver_phone,receiver_mobile,receiver_province,receiver_city,receiver_district,receiver_address,receiver_zip,create_time,update_time) values(#{shipping.userId},#{shipping.receiverName},#{shipping.receiverPhone},#{shipping.receiverMobile},#{shipping.receiverProvince},#{shipping.receiverCity},#{shipping.receiverDistrict},#{shipping.receiverAddress},#{shipping.receiverZip},now(),now())")
    @Options(useGeneratedKeys=true,keyProperty = "shipping.id",keyColumn = "id")
    int insert(@Param("shipping") Shipping shipping);

    @Delete("delete from mmall_shipping where id = #{shippingId} and user_id = #{userId}")
    int deleteByShippingIdUserId(@Param("shippingId") Integer shippingId, @Param("userId") Integer userId);

    @Update("update mmall_shipping set receiver_name= #{shipping.receiverName},receiver_phone=#{shipping.receiverPhone},receiver_mobile=#{shipping.receiverMobile},receiver_province=#{shipping.receiverProvince},receiver_city=#{shipping.receiverCity},receiver_district=#{shipping.receiverDistrict},receiver_address=#{shipping.receiverAddress},receiver_zip=#{shipping.receiverZip},update_time=now() where user_id = #{shipping.userId}")
    int update(@Param("shipping") Shipping shipping);

    @Results({@Result(property = "userId",column = "user_id"),@Result(property = "receiverName",column = "receiver_name"),@Result(property = "receiverPhone",column = "receiver_phone"),@Result(property = "receiverMobile",column = "receiver_mobile"),@Result(property = "receiverProvince",column = "receiver_province"),@Result(property = "receiverCity",column = "receiver_city"),@Result(property = "receiverDistrict",column = "receiver_district"),@Result(property = "receiverAddress",column = "receiver_address"),@Result(property = "receiverZip",column = "receiver_zip"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Select("select * from mmall_shipping where user_id = #{userId}")
    List<Shipping> selectByUserId(@Param("userId") Integer userId);


    @Results({@Result(property = "userId",column = "user_id"),@Result(property = "receiverName",column = "receiver_name"),@Result(property = "receiverPhone",column = "receiver_phone"),@Result(property = "receiverMobile",column = "receiver_mobile"),@Result(property = "receiverProvince",column = "receiver_province"),@Result(property = "receiverCity",column = "receiver_city"),@Result(property = "receiverDistrict",column = "receiver_district"),@Result(property = "receiverAddress",column = "receiver_address"),@Result(property = "receiverZip",column = "receiver_zip"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Select("select * from mmall_shipping where id = #{shippingId} and user_id =#{userId}")
    Shipping selectByShippingIdUserId(@Param("shippingId") Integer shippingId,@Param("userId") Integer userId);
}
