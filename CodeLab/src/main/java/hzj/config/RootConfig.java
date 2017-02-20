package hzj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by MichaelHe on 2017/2/20.
 */
@Configuration
@ComponentScan(basePackages = {"hzj"},
        excludeFilters = {@ComponentScan.Filter(
        type= FilterType.ANNOTATION,value= EnableWebMvc.class)})
public class RootConfig {

}
