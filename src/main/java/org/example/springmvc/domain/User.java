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
    @Size(min = 4, max = 6, message = "비밀번호는 4-6 자 사이만 입력됩니다.")
//    @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*",message = "비밀번호는 최소 하나의 특수문자를 포함해야만 합니다. ")
    private String password;
}
