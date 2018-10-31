package com.lk.dao;


import com.lk.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {
    @Results({@Result(property = "categoryId",column = "category_id"),@Result(property = "mainImage",column = "main_image"),@Result(property = "subImages",column = "sub_images"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Select("select * from  mmall_product where id = #{id}")
    Product selectById(@Param("id") Integer id);

    @Results({@Result(property = "categoryId",column = "category_id"),@Result(property = "mainImage",column = "main_image"),@Result(property = "subImages",column = "sub_images"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Update("update mmall_product set category_id = #{product.categoryId},name=#{product.name},subtitle=#{product.subtitle},main_image=#{product.mainImage},sub_images=#{product.subImages},detail=#{product.detail},price=#{product.price},stock=#{product.stock},status=1,update_time=now() where id=#{product.id}")
    int updateById(@Param("product") Product product);

    @Results({@Result(property = "categoryId",column = "category_id"),@Result(property = "mainImage",column = "main_image"),@Result(property = "subImages",column = "sub_images"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Insert("insert into mmall_product(category_id,name,subtitle,main_image,sub_images,detail,price,stock,status,create_time,update_time) values(#{product.categoryId},#{product.name},#{product.subtitle},#{product.mainImage},#{product.subImages},#{product.detail},#{product.price},#{product.stock},1,now(),now())")
    int insertProduct(@Param("product") Product product);

    @Update("update mmall_product set status = #{status} where id = #{productId}")
    int updateByStatus(@Param("productId") Integer productId,@Param("status") Integer status);

    @Results({@Result(property = "categoryId",column = "category_id"),@Result(property = "mainImage",column = "main_image"),@Result(property = "subImages",column = "sub_images"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Select("select * from mmall_product order by id")
    List<Product> selectList();

    @Results({@Result(property = "categoryId",column = "category_id"),@Result(property = "mainImage",column = "main_image"),@Result(property = "subImages",column = "sub_images"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Select("select * from mmall_product where name like #{productName} or id = #{productId} order by id")
    List<Product> selectByNameAndProductId(@Param("productName") String productName,@Param("productId") Integer productId);

    @Results({@Result(property = "categoryId",column = "category_id"),@Result(property = "mainImage",column = "main_image"),@Result(property = "subImages",column = "sub_images"),@Result(property = "createTime",column = "create_time"),@Result(property = "updateTime",column = "update_time")})
    @Select("<script>select * from mmall_product where name like #{productName} or category_id in <foreach item='item' open='(' separator=',' close=')' collection='#{categoryIdList}'> #{item} </foreach></script>")
    List<Product> selectByNameAndCategoryIds(@Param("productName") String productName,@Param("categoryIdList") List<Integer> categoryIdList);
}




