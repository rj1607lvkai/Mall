package com.lk.dao;


import com.lk.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface UserMapper {
	

	
	@Select("select * from mmall_user where username = #{username} and password = #{password}")
	User selectLogin(@Param("username") String username, @Param("password") String password);

	@Select("select * from mmall_user where id = #{id}")
	User selectById(@Param("id") int id);


	@Select("select count(1) from mmall_user where username = #{username}")
	int checkUsername(@Param("username") String username);

	@Select("select count(*) from mmall_user where email = #{email}")
	int checkEmail(@Param("email") String email);

	@Select("select count(1) from mmall_user where email = #{email} and id != #{id}")
	int checkEmailById(@Param("id") int id,@Param("email") String email);

	@Insert("insert into mmall_user(username, password, email, phone, question, answer, role, create_time,  update_time) values(#{user.username},#{user.password},#{user.email},#{user.phone},#{user.question},#{user.answer},#{user.role},now(),now())")
	int insert(@Param("user") User user);

	@Update("update  mmall_user set password = #{password},update_time = now() where username = #{username}")
	int updatePasswordByUsername(@Param("username") String username,@Param("password") String password);

	@Update("update  mmall_user set email = #{updateUser.email},phone=#{updateUser.phone},update_time = now() where id = #{updateUser.id}")
	int updateUserInfo(@Param("updateUser") User updateUser);

	@Update("update  mmall_user set password = #{password},update_time = now() where id = #{id}")
	int updatePasswordById(@Param("id") int id,@Param("password") String password);

	@Select("select count(1) from mmall_user where id = #{id} and password = #{password}")
	int checkPassword(@Param("id") int id,@Param("password") String password);

    @Select("select question from mmall_user where username = #{username}")
    String selectQuestionByUsername(@Param("username") String username);

    @Select("select count(*) from mmall_user where username = #{username} and question = #{question} and answer = #{answer}")
    int checkAnswer(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);
}
