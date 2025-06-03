package com.example.demo.springsecurity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // Content-Type が application/json のときだけ JSON をパースする
        if ("application/json".equals(request.getContentType())) {
            try (BufferedReader reader = request.getReader()) {
                // JSON を Map<String, String> に変換
                Map<String, String> credentials = objectMapper.readValue(reader, Map.class);
                String username = credentials.get(getUsernameParameter()); // デフォルトは "username"
                String password = credentials.get(getPasswordParameter()); // デフォルトは "password"

                // null チェック（パラメータ名を変えたい場合は super.setUsernameParameter(...) などで変更可能）
                if (username == null) {
                    username = "";
                }
                if (password == null) {
                    password = "";
                }

                username = username.trim();
                // AuthenticationToken を作る
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                // デフォルトで設定されている AuthenticationManager に渡す
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);

            } catch (IOException e) {
                throw new RuntimeException("JSON parsing error in authentication filter", e);
            }
        }

        // JSON 以外（例: フォーム送信）の場合は、スーパークラスに処理をまかせる
        return super.attemptAuthentication(request, response);
    }
}
