package com.kst.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.kst.common.base.service.impl.BaseService;
import com.kst.sys.api.entity.Role;
import com.kst.sys.api.entity.User;
import com.kst.sys.mapper.UserMapper;
import com.kst.sys.api.service.IUserService;
import com.kst.sys.utils.PasswordUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjf
 * @since 2017-10-31
 */
@Service("userService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<UserMapper, User> implements IUserService {

	/* 这里caching不能添加put 因为添加了总会执行该方法
	 * @see UserService#findUserByLoginName(java.lang.String)
	 */
	@Cacheable(value = "user", key = "'user_name_'+#name",unless = "#result == null")
	@Override
	public User findUserByLoginName(String name) {
		// TODO Auto-generated method stub
		Map<String,Object> map = Maps.newHashMap();
		map.put("loginName", name);
		User u = baseMapper.selectUserByMap(map);
		return u;
	}


	@Cacheable(value = "user",key="'user_id_'+T(String).valueOf(#id)",unless = "#result == null")
	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		return baseMapper.selectUserByMap(map);
	}

	@Override
	@Caching(put = {
			@CachePut(value = "user", key = "'user_id_'+T(String).valueOf(#result.id)",condition = "#result.id != null and #result.id != 0"),
			@CachePut(value = "user", key = "'user_name_'+#user.loginName", condition = "#user.loginName !=null and #user.loginName != ''"),
			@CachePut(value = "user", key = "'user_email_'+#user.email", condition = "#user.email != null and #user.email != ''"),
			@CachePut(value = "user", key = "'user_tel_'+#user.tel", condition = "#user.tel != null and #user.tel != ''")
	})
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public User saveUser(User user) {
		PasswordUtils.entryptPassword(user);
		user.setLocked(false);
		baseMapper.insert(user);
		//保存用户角色关系
		this.saveUserRoles(user.getId(),user.getRoleLists());
		return findUserById(user.getId());
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "user", key = "'user_id_'+T(String).valueOf(#user.id)",condition = "#user.id != null and #user.id != 0"),
			@CacheEvict(value = "user", key = "'user_name_'+#user.loginName", condition = "#user.loginName !=null and #user.loginName != ''"),
			@CacheEvict(value = "user", key = "'user_email_'+#user.email", condition = "#user.email != null and #user.email != ''"),
			@CacheEvict(value = "user", key = "'user_tel_'+#user.tel", condition = "#user.tel != null and #user.tel != ''" ),
	})
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public User updateUser(User user) {
		baseMapper.updateById(user);
		//先解除用户跟角色的关系
		this.dropUserRolesByUserId(user.getId());
		this.saveUserRoles(user.getId(),user.getRoleLists());
		return user;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void saveUserRoles(Long id, Set<Role> roleSet) {
		baseMapper.saveUserRoles(id,roleSet);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void dropUserRolesByUserId(Long id) {
		baseMapper.dropUserRolesByUserId(id);
	}

	@Override
	public int userCount(String key, String value) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq(key,value);
		int count = baseMapper.selectCount(wrapper);
		return count;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	@Caching(evict = {
			@CacheEvict(value = "user", key = "'user_id_'+T(String).valueOf(#user.id)",condition = "#user.id != null and #user.id != 0"),
			@CacheEvict(value = "user", key = "'user_name_'+#user.loginName", condition = "#user.loginName !=null and #user.loginName != ''"),
			@CacheEvict(value = "user", key = "'user_email_'+#user.email", condition = "#user.email != null and #user.email != ''"),
			@CacheEvict(value = "user", key = "'user_tel_'+#user.tel", condition = "#user.tel != null and #user.tel != ''" )
	})
	public void deleteUser(User user) {
		user.setDelFlag(true);
		baseMapper.updateById(user);
	}

	/**
	 * 查询用户拥有的每个菜单具体数量
	 * @return
	 */
	@Override
	public Map selectUserMenuCount() {
		return baseMapper.selectUserMenuCount();
	}

}
