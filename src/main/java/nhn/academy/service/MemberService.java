package nhn.academy.service;

import nhn.academy.model.Member;
import nhn.academy.model.MemberCreateCommand;
import nhn.academy.model.exception.MemberAlreadyExistsException;
import nhn.academy.model.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String HASH_NAME = "Member:";

    public Member getMember(String memberId) {

        Object object = redisTemplate.opsForHash().get(HASH_NAME, memberId);
        if (object == null) {
            throw new MemberNotFoundException();
        }
        return (Member) object;
    }

    public void createMember(MemberCreateCommand memberCreateCommand) {
        Object object = redisTemplate.opsForHash().get(HASH_NAME, memberCreateCommand.getId());
        if (object != null) {
            throw new MemberAlreadyExistsException("already used id");
        }
        Member member = new Member(memberCreateCommand.getId(),memberCreateCommand.getPassword(),memberCreateCommand.getEmail(),memberCreateCommand.getState());
        redisTemplate.opsForHash().put(HASH_NAME, member.getId(), member);
    }

    public List<Member> getMembers() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_NAME);
        List<Member> members = new ArrayList<>(entries.size());
        for (Object value : entries.values()) {
            members.add((Member) value);
        }
        return members;
    }

    public Member updateMember(String memberId, MemberCreateCommand memberCreateCommand) {

        return new Member(memberId,memberCreateCommand.getPassword(),memberCreateCommand.getEmail(),memberCreateCommand.getState());
    }

    public void deleteMember(String memberId) {
        redisTemplate.opsForHash().delete(HASH_NAME, memberId);
    }
}
