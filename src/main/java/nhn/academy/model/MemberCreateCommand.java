package nhn.academy.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateCommand {
    private String id;
    private String password;
    private String email;
    private MemberState state;

}
