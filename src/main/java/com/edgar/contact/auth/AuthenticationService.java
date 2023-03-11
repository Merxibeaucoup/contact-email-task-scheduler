package com.edgar.contact.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edgar.contact.config.JwtService;
import com.edgar.contact.exceptions.ContactDoesNotExistException;
import com.edgar.contact.models.token.Token;
import com.edgar.contact.models.token.TokenType;
import com.edgar.contact.models.user.Role;
import com.edgar.contact.models.user.User;
import com.edgar.contact.repositories.TokenRepository;
import com.edgar.contact.repositories.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private  JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
public AuthenticationResponse register(RegisterRequest request) {
		
		var user = User.builder()
		        .firstname(request.getFirstname())
		        .lastname(request.getLastname())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(Role.USER)
		        .build();
		
		var savedUser = repository.save(user);
		
	    var jwtToken = jwtService.generateToken(user);
	   
	    saveUserToken(savedUser, jwtToken);
	    
	    
	    /** added response below email , firstname, id to access in frontend
	     * if any issues , just remove those and in AUthResponse class too 
	     * **/
		
	    return AuthenticationResponse.builder()
	            .token(jwtToken)
	            .email(user.getEmail())
	            .firstname(user.getFirstname())
	            .id(user.getId())
	            .build();
	}
	
	
	

	

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		
		 authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			            request.getEmail(),
			            request.getPassword()
			        )
			    );
		 
			    var user = repository.findByEmail(request.getEmail())
			        .orElseThrow();
			    
			    var jwtToken = jwtService.generateToken(user);
			    revokeAllUserTokens(user);
			    saveUserToken(user, jwtToken);
			    return AuthenticationResponse.builder()
			    		 .token(jwtToken)
				         .email(user.getEmail())
				         .firstname(user.getFirstname())
				         .id(user.getId())
			        .build();
		
		
		
	}
	
	
	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder()
	    		.user(user)
	    		.token(jwtToken)
	    		.tokenType(TokenType.BEARER)
	    		.revoked(false)
	    		.expired(false)
	    		.build();
	    
	    tokenRepository.save(token);
	}
	
	private void revokeAllUserTokens(User user) {
		var validUserToken = tokenRepository.findAllValidTokensByUser(user.getId());
	
	if(validUserToken.isEmpty()) {
		return;
	}
	
	validUserToken.forEach(t -> {
	t.setExpired(true);
	t.setRevoked(true);
	
	});
	
	tokenRepository.saveAll(validUserToken);
	}
	
	public User getUserByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(()-> new ContactDoesNotExistException("Contact not found with email :" + email));
		}

}
