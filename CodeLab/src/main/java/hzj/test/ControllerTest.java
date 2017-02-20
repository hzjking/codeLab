package hzj.test;

import hzj.controller.base.IndexController;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;


/**
 * Created by MichaelHe on 2017/2/20.
 */
public class ControllerTest {

    @Test
    public void testHomePage() throws Exception{
        IndexController indexController = new IndexController();
        assertEquals("sys/login",indexController.loginPage());
    }
}
