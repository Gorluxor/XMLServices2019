package com.megatravel.util;

import java.util.List;


public class JwtToken {
	String sub;
	String iat;
	String exp;
	List<JwtAuthorization> auth;
	public JwtToken() {
	}
	public JwtToken(String sub,  String iat, String exp, List<JwtAuthorization> auth) {
		super();
		this.sub = sub;
		this.iat = iat;
		this.exp = exp;
		this.auth = auth;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(String iat) {
		this.iat = iat;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}

	public List<JwtAuthorization> getAuth() {
		return auth;
	}

	public void setAuth(List<JwtAuthorization> auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "JwtToken [sub=" + sub + ", iat=" + iat + ", exp=" + exp + "]";
	}
	
}
