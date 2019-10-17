package com.svse.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
	
	@Autowired
	private MyInterceptor myInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		String xx[] = new String[] { "/", "/user.do", "/css/**","/js/**","/layer/**","/layui/**","/plugins/**" };
		// 注册自定义的拦截器，增加拦截路径和排除拦截路径
		registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(xx);

		super.addInterceptors(registry);

	}
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").addResourceLocations("file:D:/cao/");
        super.addResourceHandlers(registry);
    }
}
