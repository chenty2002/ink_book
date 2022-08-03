package com.summerProject.ink_book.Entity;

import java.time.LocalDateTime;

public class Group {
    private Integer groupId;
    private String groupName;
    private LocalDateTime groupCreateTime;
    private String groupProfile;
    private User founder;

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupCreateTime=" + groupCreateTime +
                ", groupProfile='" + groupProfile + '\'' +
                ", founder=" + founder +
                '}';
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getGroupCreateTime() {
        return groupCreateTime;
    }

    public void setGroupCreateTime(LocalDateTime groupCreateTime) {
        this.groupCreateTime = groupCreateTime;
    }

    public String getGroupProfile() {
        return groupProfile;
    }

    public void setGroupProfile(String groupProfile) {
        this.groupProfile = groupProfile;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }
}
