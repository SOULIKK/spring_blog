package com.soulikk.spring_blog.service;

import com.soulikk.spring_blog.dto.SignupRequestDto;
import com.soulikk.spring_blog.entity.User;
import com.soulikk.spring_blog.entity.UserRole;
import com.soulikk.spring_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_TOKEN = "";

    public void registerUser(SignupRequestDto singupRequestDto) {
        String username = singupRequestDto.getUsername();
        String password = passwordEncoder.encode(singupRequestDto.getPassword());
        Optional<User> searchedUser = userRepository.findByUsername(username);
        if (searchedUser.isPresent()) {
            throw new IllegalArgumentException("중복된 ID입니다.");
        }
        String email = singupRequestDto.getEmail();

        UserRole role = UserRole.USER;
        if (singupRequestDto.isAdmin()) {
            if (!singupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 토큰을 확인하세요");
            }
            role = UserRole.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

}
