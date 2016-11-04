package hzj.utils;


import hzj.pojo.User;
import hzj.service.PasswordService;

/**
 */
public class PasswordHelper {


    private PasswordService passwordService;

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public PasswordHelper(PasswordService passwordService) {
        this.passwordService = passwordService;
    }



    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {

        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getUsername(),user.getPassword(),user.getSalt()));
    }

    /*public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }*/
}
