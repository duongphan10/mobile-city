package com.vn.mobilecity.socket.io;

import com.corundumstudio.socketio.AuthorizationResult;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.vn.mobilecity.constant.CommonConstant;
import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.exception.UnauthorizedException;
import com.vn.mobilecity.security.jwt.JwtTokenProvider;
import com.vn.mobilecity.socket.io.exception.SocketIOEventException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
    private final SocketIOEventException socketIOEventException;
    private final JwtTokenProvider tokenProvider;
    @Value("${socket.io.host}")
    private String host;
    @Value("${socket.io.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        //config.setHostname(host);
        config.setPort(port);
        //config.setOrigin("*");
        config.setAuthorizationListener(handshakeData -> {
            String accessToken = handshakeData.getSingleUrlParam(CommonConstant.Key.ACCESS_TOKEN);
            if (!StringUtils.hasText(accessToken) || !tokenProvider.validateToken(accessToken)) {
                throw new UnauthorizedException(ErrorMessage.FORBIDDEN);
            }
            return AuthorizationResult.SUCCESSFUL_AUTHORIZATION;
        });
        config.setExceptionListener(socketIOEventException);
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
