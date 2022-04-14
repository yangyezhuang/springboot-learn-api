package com.learn.mapper;

import com.learn.model.Course;
import com.learn.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 根据id查找用户（查看用户是否存在）
    @Select("select count(*) from user_tbl WHERE email=#{email};")
    int findUserByEmail(String email);


    // 用户注册
    @Insert("insert into user_tbl(uid,username,password,phoneNum,email,status) values(#{uid},#{username},#{password},#{phoneNum},#{email},1)")
    int addUser(int uid, String username, String password, String phoneNum, String email);


    // 用户登录
    @Select("select email,uid,username,password,role from user_tbl where email=#{email};")
    User selectByEmail(String email);


    // 获取用户权限角色
    @Select("select role from user_tbl where uid=#{uid};")
    User queryRole(int uid);


    // 查询所有用户
    @Select("select * from user_tbl;")
    List<User> allUsers();


    // 删除用户
    @Delete("delete from user_tbl where uid = #{uid}")
    int delUser(int uid);


    // 修改用户状态
    @Update("update user_tbl set status=#{status} where uid=#{userId}")
    int updateUserStatus(boolean status, int userId);


    // 查询用户收藏的课程（多表联合查询）
    @Select("select b.id,b.title,b.info,b.img from user_like_tbl a join course_tbl b on a.course_id=b.id where a.uid=#{uid};")
    List<Course> userCourses(int uid);

    // 查询用户收藏的课程总数
    @Select("select count(uid) from  user_like_tbl where uid=#{uid};")
    int courseTotal(int uid);


    // 检查用户是否有该课程
    @Select("select count(*) from user_like_tbl WHERE uid=#{uid} and course_id=#{course_id};")
    int isLike(int uid, int course_id);


    // 添加用户课程
    @Insert("insert into user_like_tbl(uid,course_id) values(#{uid},#{course_id});")
    int addUserCourse(int uid, int course_id);


    // 删除用户课程
    @Delete("delete from user_like_tbl where uid = #{uid} and course_id = #{course_id};")
    int delUserCourse(int uid, int course_id);


    // 查看用户信息
    @Select("select * from user_tbl where uid=#{uid};")
    User getUserInfo(int uid);


    // 修改密码
    @Update("update user_tbl set password=#{password} where username= #{username};")
    User updatePasswd(String username, String password);

}
