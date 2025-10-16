package org.example.springmvc.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 모든 필드 받는 생성자
@NoArgsConstructor  // 기본 생성자
public class User {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;
    @NotEmpty(message = "아이디를 입력하세요.")
    private String username;
    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;
    private boolean admin;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    @Size()
    private String password;


}
