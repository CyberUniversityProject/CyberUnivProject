package com.cyber.university.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.cyber.university.handler.AuthIntercepter;
import com.cyber.university.handler.AuthIntercepterForLogin;
import com.cyber.university.handler.AuthIntercepterForMainPage;
import com.cyber.university.handler.UserRoleAuthIntercepterForProfessor;
import com.cyber.university.handler.UserRoleAuthIntercepterForStaff;
import com.cyber.university.handler.UserRoleAuthIntercepterForStudent;
import com.cyber.university.utils.Define;

/**
 * packageName    : com.cyber.university.config
 * fileName       : WebMvcConfig
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : WebMvcConfig
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Configuration

public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthIntercepter authIntercepter;
    @Autowired
    private UserRoleAuthIntercepterForProfessor authIntercepterForProfessor;
    @Autowired
    private UserRoleAuthIntercepterForStaff authIntercepterForStaff;
    @Autowired
    private UserRoleAuthIntercepterForStudent authIntercepterForStudent;
    @Autowired
    private AuthIntercepterForLogin authIntercepterForLogin;
//    @Autowired
//    private AuthIntercepterForMainPage authIntercepterForMainPage;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authIntercepter).addPathPatterns(Define.PATHS);
        registry.addInterceptor(authIntercepterForProfessor).addPathPatterns(Define.PROFESSOR_PATHS);
        registry.addInterceptor(authIntercepterForStaff).addPathPatterns(Define.STAFF_PATHS);
        registry.addInterceptor(authIntercepterForStudent).addPathPatterns(Define.STUDENT_PATHS);
        registry.addInterceptor(authIntercepterForLogin).addPathPatterns("/login");
//        registry.addInterceptor(authIntercepterForMainPage).addPathPatterns("/");
    }

    // 파일 리소스 등록
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/uploads/**")
                .addResourceLocations("file:///C:\\spring_upload\\cyberUniversity\\upload/");
//        		.addResourceLocations("file:////Users/junhyuk/Documents/upload/");
        
        
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
