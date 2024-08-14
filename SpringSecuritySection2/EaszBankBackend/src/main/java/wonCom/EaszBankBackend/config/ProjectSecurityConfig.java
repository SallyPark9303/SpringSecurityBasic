package wonCom.EaszBankBackend.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //이 클래스 안에 특정 설정을 정의 , 시작단계에서 이 클래스 안에 우리가 정의한 모든 bean 을 스캔함.

public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated()); // 모든 요청이 디폴트로 보호됨
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated() // 보호됨 , /myAccount/** :  myAccount 가 기본 경로인 모든 경로는 보호됨
                        .requestMatchers("/notices","/contact").permitAll()) // 자격증명 없이  허가됨
                        .formLogin(Customizer.withDefaults())
                        .httpBasic(Customizer.withDefaults());
        return http.build();

    }


}
