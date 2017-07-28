package hzj.usertest;

import hzj.base.BaseTest;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MichaelHe on 2017/7/28.
 */
public class LoginTest extends BaseTest {



    @Before
    public void init(){

        settingToken();
    }

    @Test
    public void testUserLogin(){



    }

    @Override
    protected void setToken() {
        this.token="46c9c2c3f7f20d21f5b3ac94903947ba";
    }

    @Override
    protected void setUserId() {
        this.userId=102;
    }

    public String packJsonReqParams(){



        return null;
    }




}
