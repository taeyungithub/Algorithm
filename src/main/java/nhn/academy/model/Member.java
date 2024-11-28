package nhn.academy.model;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Getter;

@Getter
public class Member {
    private String id;
    private String password;
    private String email;
    private MemberState state;


    public Member() {
    }

    public Member(String id, String password, String email, MemberState state) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.state = state;
    }
}
