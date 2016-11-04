package hzj.service;


import hzj.pojo.User;

import java.util.Set;

/**
 * Created by MichaelHe on 2016/5/24.
 */
public interface UserAuthService {

    /**
     * 根据角色获取 角色字符串
     *
     * @param user
     * @return
     */
    public Set<String> findStringRoles(User user);

    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    Set<String> findStringPermissions(User user);




}
