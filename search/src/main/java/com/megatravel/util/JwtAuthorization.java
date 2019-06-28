package com.megatravel.util;

public class JwtAuthorization {
    String authority;

    public JwtAuthorization() {
    }
    public JwtAuthorization(String authority, String value) {
        super();
        this.authority = authority;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "JwtAuthorization [authority=" + authority + "]";
    }
}
