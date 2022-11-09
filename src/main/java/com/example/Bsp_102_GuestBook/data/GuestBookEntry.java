package com.example.Bsp_102_GuestBook.data;

public class GuestBookEntry {
    private String nickname;
    private String email;
    private String message;

    public GuestBookEntry(String nickname, String email, String message) {
        this.nickname = nickname;
        this.email = email;
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
