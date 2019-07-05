package com.nnf.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan
public class SpringWebConfig
        extends WebMvcConfigurerAdapter implements ApplicationContextAware, WebMvcConfigurer {

    private ApplicationContext applicationContext;


    public SpringWebConfig() {
        super();
    }


    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }



    /* ******************************************************************* */
    /*  GENERAL CONFIGURATION ARTIFACTS                                    */
    /*  Static Resources, i18n Messages, Formatters (Conversion Service)   */
    /* ******************************************************************* */

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("images/**").addResourceLocations("classpath:/images/");
        registry.addResourceHandler("css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("js/mdl/**").addResourceLocations("classpath:/js/mdl/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/hello").setViewName("welcome");
        registry.addViewController("/ouch").setViewName("ouch");
        registry.addViewController("/pain-ref-data").setViewName("pain-ref-data");
    }

}
