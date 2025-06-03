package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.springsecurity.JsonUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")  // フロント開発サーバー
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true)
                        .allowedHeaders("*"); //どんなHeadersでも許可する
            }
        };
    }
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.cors();
    	
        // 1) JSON ログイン用カスタムフィルタをインスタンス化
        JsonUsernamePasswordAuthenticationFilter jsonFilter = new JsonUsernamePasswordAuthenticationFilter();
        // POST /api/login で JSON を受け取ると、このフィルタが最初に起動し、認証処理を行います。
        jsonFilter.setFilterProcessesUrl("/api/login");
        // AuthenticationManager の設定
        jsonFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        http
            // カスタムフィルタを UsernamePasswordAuthenticationFilter の「前」に差し込む
            .addFilterBefore(jsonFilter, UsernamePasswordAuthenticationFilter.class)

            //  以下は既存の formLogin/authorize 設定など
            .logout(logout -> logout
                .logoutSuccessUrl("/")            //ログアウト後にリダイレクトされるURL
            )
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()       ///css/**, /js/**, /images/** など、一般的な場所にある静的ファイルへのアクセスを許可
                .requestMatchers("/").permitAll()                  //ルートパス（/）へのアクセスは誰でも許可
                .requestMatchers("/general").hasRole("GENERAL")    // /generalというURLには、ROLE_GENERALの権限を持つユーザのみアクセスを許可します。hasRole("GENERAL")と書くと内部的にROLE_GENERALという権限名をチェックします
                .requestMatchers("/admin").hasRole("ADMIN")        //同様に、ROLE_ADMIN を持つユーザのみ /admin にアクセスできるようにします。
                .anyRequest().authenticated()                      //上記で明示的に許可（permitAll()）や特定の権限（hasRole(...)）を設定していないすべての URL は認証済みユーザでなければアクセス不可とします。
            );

        return http.build();
    }

}
