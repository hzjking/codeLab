package hzj.service;


import hzj.pojo.User;

/**
 * Created by MichaelHe on 2016/5/24.
 */
public interface PasswordService {


    void validate(User user, String password);

    String encryptPassword(String username, String password, String salt);

}
