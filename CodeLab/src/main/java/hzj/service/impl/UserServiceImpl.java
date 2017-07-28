package hzj.service.impl;


import hzj.dao.base.PageQueryDAO;
import hzj.dao.mapper.SysUserMapper;
import hzj.dao.po.SysUser;
import hzj.dao.po.SysUserExample;
import hzj.exception.ErrCodeException;
import hzj.exception.UserBlockedException;
import hzj.exception.UserNotExistsException;
import hzj.exception.UserPasswordNotMatchException;
import hzj.page.PageDataDTO;
import hzj.page.PageQueryDTO;
import hzj.pojo.User;
import hzj.pojo.UserStatus;
import hzj.query.UserQueryDTO;
import hzj.respCode.RespCode;
import hzj.service.PasswordService;
import hzj.service.RoleService;
import hzj.service.UserService;
import hzj.utils.DateUtil;
import hzj.utils.PasswordHelper;
import hzj.utils.UserLogUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MichaelHe on 2015/6/11.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordService passwordService;

    @Resource
    private PageQueryDAO pageQueryDAO;

    private PasswordHelper passwordHelper;

    /**
     * 创建用户
     *
     * @param user
     */
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        SysUser sysUser = new SysUser();
        toEntity(user);
        sysUserMapper.insert(sysUser);
        return user;
    }

    @Override
    public User updateUser(User user) {
        SysUser sysUser = new SysUser();
        toEntity(user);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andIdEqualTo(sysUser.getId());
        sysUserMapper.updateByExampleSelective(sysUser, sysUserExample);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andIdEqualTo(userId);
        User user = findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        SysUser sysUser = new SysUser();
        sysUser.setPassword(user.getPassword());
        sysUserMapper.updateByExampleSelective(sysUser, sysUserExample);
    }

    @Override
    public User findOne(Long userId) {
        User user = toPojo(sysUserMapper.selectByPrimaryKey(userId));
        return user;
    }

    @Override
    public List<User> findAll() {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andIdNotEqualTo(Long.parseLong("0"));
        List<User> users = new ArrayList<User>();
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        for (int i = 0; i < sysUsers.size(); i++) {
            users.add(toPojo(sysUsers.get(i)));
        }
        return users;
    }

    @Override
    public PageDataDTO queryUser(UserQueryDTO userQueryDTO) {

        PageDataDTO pageData = null;

        pageData = pageQueryDAO.query("com.sand.selfMapper.QueryUserMapper.selectAllUser", userQueryDTO);

        return pageData;
    }

    @Override
    public List<User> queryUserList(UserQueryDTO userQueryDTO) throws Exception {

        PageQueryDTO queryParams = new PageQueryDTO();
        queryParams.put("starttime", userQueryDTO.getStarttime());
        queryParams.put("endtime", userQueryDTO.getEndtime());
        queryParams.put("status", userQueryDTO.getUserstatus());

        PageDataDTO pageData = pageQueryDAO.query("com.sand.selfMapper.QueryUserMapper.selectAllUserListByParam", queryParams);
        if (pageData.getData() == null) {
            throw new ErrCodeException(RespCode.NO_MORE_DATA);
        }
        List<User> users = (List<User>) pageData.getData();
        return users;
    }
    public List<User> queryUserNameList(UserQueryDTO userQueryDTO) throws Exception{
        PageQueryDTO queryParams = new PageQueryDTO();
        queryParams.put("starttime", userQueryDTO.getStarttime());
        queryParams.put("endtime", userQueryDTO.getEndtime());
        queryParams.put("status", userQueryDTO.getUserstatus());
        queryParams.put("username", userQueryDTO.getUsername());
        PageDataDTO pageData = pageQueryDAO.query("com.sand.selfMapper.QueryUserMapper.selectAllUserNameListByParam", queryParams);
        if (pageData.getData() == null) {
            throw new ErrCodeException(RespCode.NO_MORE_DATA);
        }
        List<User> users = (List<User>) pageData.getData();
        return users;
    }
    public List<User> queryUserPhoneList(UserQueryDTO userQueryDTO) throws Exception{
        PageQueryDTO queryParams = new PageQueryDTO();
        queryParams.put("starttime", userQueryDTO.getStarttime());
        queryParams.put("endtime", userQueryDTO.getEndtime());
        queryParams.put("status", userQueryDTO.getUserstatus());
        queryParams.put("userphone",userQueryDTO.getMobilePhoneNumber());

        PageDataDTO pageData = pageQueryDAO.query("com.sand.selfMapper.QueryUserMapper.selectAllUserPhoneListByParam", queryParams);
        if (pageData.getData() == null) {
            throw new ErrCodeException(RespCode.NO_MORE_DATA);
        }
        List<User> users = (List<User>) pageData.getData();
        return users;
    }
    public List<User> queryUserNamePhoneList(UserQueryDTO userQueryDTO) throws Exception{
        PageQueryDTO queryParams = new PageQueryDTO();
        queryParams.put("starttime", userQueryDTO.getStarttime());
        queryParams.put("endtime", userQueryDTO.getEndtime());
        queryParams.put("status", userQueryDTO.getUserstatus());
        queryParams.put("username",userQueryDTO.getUsername());
        queryParams.put("userphone",userQueryDTO.getMobilePhoneNumber());

        PageDataDTO pageData = pageQueryDAO.query("com.sand.selfMapper.QueryUserMapper.selectAllUserNamePhoneListByParam", queryParams);
        if (pageData.getData() == null) {
            throw new ErrCodeException(RespCode.NO_MORE_DATA);
        }
        List<User> users = (List<User>) pageData.getData();
        return users;
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(username);

        return toPojo(sysUserMapper.selectByExample(sysUserExample).get(0));
    }
    @Override
    public SysUser findBySysUsername(String username){
        SysUser sysUser=new SysUser();
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(username);
        if(sysUserMapper.selectByExample(sysUserExample).size()==0){
            sysUser=null;
        }else{
            sysUser=sysUserMapper.selectByExample(sysUserExample).get(0);
        }
        return sysUser;
    }
    @Override
    public SysUser findBySysUserphone(String userphone){
        SysUser sysUser=new SysUser();
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andMobilePhoneNumberEqualTo(userphone);
        if(sysUserMapper.selectByExample(sysUserExample).size()==0){
            sysUser=null;
        }else{
            sysUser=sysUserMapper.selectByExample(sysUserExample).get(0);
        }
        return sysUser;
    }
    @Override
    public SysUser findBySysUseremail(String useremail){
        SysUser sysUser=new SysUser();
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andEmailEqualTo(useremail);
        if(sysUserMapper.selectByExample(sysUserExample).size()==0){
            sysUser=null;
        }else{
            sysUser=sysUserMapper.selectByExample(sysUserExample).get(0);
        }
        return sysUser;
    }

    @Override
    public boolean checkTokenByAccount(Integer userid, String token, String uuid) throws ErrCodeException {
        return false;
    }


    @Override
    public User login(String username, String password) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "username is empty");
            throw new UserNotExistsException();
        }
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "password length error! password is between {} and {}",
                    User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);

            throw new UserPasswordNotMatchException();
        }

        User user = null;


        if (maybeUsername(username)) {
            user = findByUsername(username);
        }

        if (user == null && maybeEmail(username)) {
            user = findByEmail(username);
        }

        if (user == null && maybeMobilePhoneNumber(username)) {
            user = findByMobilePhoneNumber(username);
        }

        if (user == null || Boolean.TRUE.equals(user.getDeleted())) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "user is not exists!");

            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

       /* if (user.getStatus() == UserStatus.blocked) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "user is blocked!");
            throw new UserBlockedException("");
        }*/

        UserLogUtils.log(
                username,
                "loginSuccess",
                "");
        return user;
    }

    @Override
    public User findByEmail(String email) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andEmailEqualTo(email);
        return toPojo(sysUserMapper.selectByExample(sysUserExample).get(0));

    }

    @Override
    public User findByMobilePhoneNumber(String mobile) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andMobilePhoneNumberEqualTo(mobile);
        return toPojo(sysUserMapper.selectByExample(sysUserExample).get(0));

    }


    private boolean maybeUsername(String username) {
        if (!username.matches(User.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < User.USERNAME_MIN_LENGTH || username.length() > User.USERNAME_MAX_LENGTH) {
            return false;
        }

        return true;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(User.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    /*public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }*/

    /**
     * 根据用户名查找其权限
     */
    /*public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }*/
    public static SysUser toEntity(User user) {
        SysUser sysUser = new SysUser();
        sysUser.setPassword(user.getPassword());
        sysUser.setId(user.getId());
        sysUser.setSalt(user.getSalt());
        sysUser.setUsername(user.getUsername());
        sysUser.setMobilePhoneNumber(user.getMobilePhoneNumber());
        sysUser.setAdmin(user.getAdmin());
        sysUser.setCreateDate(new Date());
        sysUser.setEmail(user.getEmail());
        sysUser.setStatus(user.getUserStatus());
        return sysUser;
    }


    public User toPojo(SysUser sysUser) {
        User user = new User();

        user.setPassword(sysUser.getPassword());
        user.setId(sysUser.getId());
        user.setEmail(sysUser.getEmail());
        user.setSalt(sysUser.getSalt());
        user.setUsername(sysUser.getUsername());
        user.setAdmin(sysUser.getAdmin());
        user.setCreateDate(DateUtil.getCurrentDateStr());
        user.setMobilePhoneNumber(sysUser.getMobilePhoneNumber());
        return user;
    }
    public void insertUser(User user) throws Exception{
        SysUser sysUser=toEntity(user);
        try{
            sysUserMapper.insert(sysUser);
        }catch(Exception e){
            throw new ErrCodeException(RespCode.OPPER_FAIL);
        }

    }

}
