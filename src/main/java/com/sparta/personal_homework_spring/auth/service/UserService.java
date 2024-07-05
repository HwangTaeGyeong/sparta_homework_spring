package com.sparta.personal_homework_spring.auth.service;

import com.sparta.personal_homework_spring.auth.dto.UserRequestDto;
import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.auth.jwt.JwtUtil;
import com.sparta.personal_homework_spring.auth.repository.UserRepository;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.todo.dto.TodoResponseDto;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.DuplicateMappingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public void signup(UserRequestDto requestDto) {

        // 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 1. dto -> entity 로 변환
        User user = User.toEntity(requestDto, password);

        Optional<User> findedUser = userRepository.findByEmail(user.getEmail());

        if (findedUser.isPresent()) {
            throw new DuplicateRequestException("중복된 아이디가 이미 있습니다.");
        }

        // 2. entity 를 리파지토리에 저장
        userRepository.save(user);

    }

    public void login(UserRequestDto requestDto, HttpServletResponse response) {

        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 사용자 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다. ")
        );

        // 비밀번호 확인
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지않습니다.");
        }

        // 3. 확인 후 토큰 생성
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());

        // 4. 쿠키에 저장 후 response 객체에 추가
        jwtUtil.addJwtToCookie(token, response);
    }

    public List<TodoResponseDto> getLikedTodo(User user) {
        Long userId = user.getId();

        User findUser = userRepository.findByIdWithTodoLikes(userId);

        List<TodoLike> todoLikes = findUser.getTodoLikes();

        return todoLikes.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }
}
