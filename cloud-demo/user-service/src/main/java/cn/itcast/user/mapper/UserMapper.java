package cn.itcast.user.mapper;

import cn.itcast.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用mapper，内置了增删改查方法
 */
public interface UserMapper extends Mapper<User> {
}
