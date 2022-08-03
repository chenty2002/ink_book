package com.summerProject.ink_book.Utils;

public enum UserLevel {
    FOUNDER(3, "Group Founder"),
    ADMINISTRATOR(2, "Group Admin"),
    MEMBER(1, "Group Member");
    private Integer code;
    private String description;

    UserLevel(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code.toString();
    }
}
