package org.blockchain.service;

import org.blockchain.entity.User;
import org.blockchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Service
public class AuthorizationService {

    @Autowired
    UserRepository userRepository;

    public HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public User getAuthorizationFromRequest() {
        HttpServletRequest request = getHttpRequest();
        String authorization = request.getHeader("authorization");

        if(authorization == null || !authorization.matches("-?\\d+(\\.\\d+)?")){
            return null;
        }

        Optional<User> user = userRepository.findById(Long.parseLong(authorization));

        return user.orElse(null);
    }
}
