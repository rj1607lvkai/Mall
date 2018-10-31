package com.lk.dao;

import com.lk.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapper {
    @Insert("insert into mmall_category(parent_id,name,status,create_time,update_time) values(#{category.parentId},#{category.name},1,now(),now())")
    int insert(@Param("category") Category category);

    @Update("update mmall_category set name = #{category.name},update_time = now() where id = #{category.id}")
    int updateCategory(@Param("category") Category category);

    @Select("select * from mmall_category where parent_id = #{parentId}")
    List<Category> getCategoryChildrenByParentId(@Param("parentId") Integer parentId);

    @Select("select * from  mmall_category where id = #{id}")
    Category selectById(@Param("id") Integer id);


}