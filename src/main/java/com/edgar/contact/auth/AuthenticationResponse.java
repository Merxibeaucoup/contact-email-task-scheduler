package com.edgar.contact.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {

	private String token;
	
	private Long id;

    private String firstname;

    private String email;

	
}


//public AuthenticationResponse() {
//
//}
//
//public AuthenticationResponse(String token) {
//	super();
//	this.token = token;
//}
//
//public String getToken() {
//	return token;
//}
//
//public void setToken(String token) {
//	this.token = token;
//}
