package com.megatravel.utils;

public class JwtToken {
	String sub;
	String iat;
	String exp;
	
	public JwtToken() {
	}
	public JwtToken(String sub,  String iat, String exp) {
		super();
		this.sub = sub;
		this.iat = iat;
		this.exp = exp;
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
	@Override
	public String toString() {
		return "JwtToken [sub=" + sub + ", iat=" + iat + ", exp=" + exp + "]";
	}
	
}
