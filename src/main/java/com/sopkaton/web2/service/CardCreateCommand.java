package com.sopkaton.web2.service.command;

public record CardCreateCommand(int groupId, String hint, long userId) {
    public static CardCreateCommand from(int groupId, String hint, long userId) {
        return new CardCreateCommand(groupId, hint, userId);
    }
}
