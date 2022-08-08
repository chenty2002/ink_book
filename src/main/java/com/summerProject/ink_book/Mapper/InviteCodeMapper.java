package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.InviteCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InviteCodeMapper {
    int insertCode(InviteCode code);

    int updateCode(InviteCode code);

    InviteCode selectCode(Integer groupId);

    Integer getGroupId(String code);
}
