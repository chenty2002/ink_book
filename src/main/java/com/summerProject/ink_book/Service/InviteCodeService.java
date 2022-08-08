package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.InviteCode;

public interface InviteCodeService {
    InviteCode getInviteCode(Integer groupId);

    Integer validateCode(String code);
}
