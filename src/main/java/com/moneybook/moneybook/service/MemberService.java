package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.exceptions.DuplicatedMemberException;
import com.moneybook.moneybook.exceptions.InvalidUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberSaveRequestDto member) {

        Member memberEntity = Member.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();
        validateDuplicateMember(memberEntity);
        return memberRepository.save(memberEntity).getId();
    }

    @Transactional(readOnly = true)
    private void validateDuplicateMember(Member member){

        List<Member> findMembers = memberRepository.findByUsername(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new DuplicatedMemberException("already exist member. check username=" + member.getUsername());
        }
    }

    @Transactional
    public void changePassword(String username, String password) {
        List<Member> findMember = memberRepository.findByUsername(username);
        if(findMember.isEmpty()) {
            throw new InvalidUsernameException("not exist member. check username=" + username);
        }
        findMember.get(0).changePassword(password);
    }
}
