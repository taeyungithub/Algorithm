package nhn.academy.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MemberState {
    JOIN, SLEEPER, WITHDRAW;

    @JsonCreator
    public static MemberState fromString(String str){
        for (MemberState value : MemberState.values()) {
            if (value.name().equalsIgnoreCase(str)) {
                return value;
            }
        }
        return SLEEPER;
    }

    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
