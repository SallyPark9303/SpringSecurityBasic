package woncompany.springsecestion3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
@Configuration
public class ProjectSecurityConfig {

    // section3 jdbc 인증 방식을 사용해 데이터베이스에 기록과 유저 정보를 저장함
    // 1. jdbcUserDetailsManager 타입의 Bean 을 생성
    // application.properties에서 정의할 때마다 Spring Boot는 DataSource 객체를 자동적으로 웹 애플리케이션 내부에 생성
    // 즉, 이 DataSource 객체가  jdbcUserDetailsManager 에게 넘어갈 때마다 발생
    // spring security 가 jdbc 인증 방식을 사용한다는 것을 이해하며 데이터 베이스 정보를 가지고 있음
  /*  @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/
    // 비밀번호를 어떻게 저장하였는지 알려주어야 함
    // 일반 텍스트 or 암호화 와 해싱을 이용했는지.. 그러므로 항상 필요함
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/register").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }



}
