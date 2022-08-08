package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.InviteCode;
import com.summerProject.ink_book.Mapper.InviteCodeMapper;
import com.summerProject.ink_book.Service.InviteCodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;


@Service
public class InviteCodeServiceImpl implements InviteCodeService {
    private final static String characters = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private final static Random random = new Random();
    private final InviteCodeMapper inviteCodeMapper;

    public InviteCodeServiceImpl(InviteCodeMapper inviteCodeMapper) {
        this.inviteCodeMapper = inviteCodeMapper;
    }

    @Override
    public InviteCode getInviteCode(Integer groupId) {
        InviteCode inviteCode = inviteCodeMapper.selectCode(groupId);
        if (inviteCode == null || inviteCode.getExpireTime().isBefore(LocalDateTime.now())) {
            inviteCode = new InviteCode();
            inviteCode.setGroupId(groupId);
            inviteCode.setInviteCode(DigestUtils.md5DigestAsHex(generateCode().getBytes(StandardCharsets.UTF_8)));
            inviteCode.setExpireTime(LocalDateTime.now().plusDays(1));
            inviteCodeMapper.insertCode(inviteCode);
        }
        return inviteCode;
    }

    @Override
    public Integer validateCode(String code) {
        return inviteCodeMapper.getGroupId(code);
    }

    private String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++)
            code.append(characters.charAt(random.nextInt(36)));
        return code.toString().toUpperCase();
    }
}
