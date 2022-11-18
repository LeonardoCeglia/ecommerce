package it.objectmethod.tutorial.ecommerce.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import it.objectmethod.tutorial.ecommerce.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JWTService {

	private static final String MY_SECRET_JWT_KEY = "orb-is-hide-and-bonus";

	public String createJWTToken(Customer customer) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);

		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		String token = JWT.create().withClaim("userId", customer.getId()).withClaim("usrename", customer.getFirstName())
				.withExpiresAt(cal.getTime()).sign(alg);
		return token;
	}

	public boolean checkJWTToken(String jwtToken) {
		if (jwtToken == null || jwtToken.trim().isBlank()) {
			return false;
		}
		boolean valid = false;
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);

			Long userId = decoded.getClaim("userId").asLong();
			String userName = decoded.getClaim("usrename").asString();

			log.debug("Utente verificato! " + userId + " | " + userName);
			valid = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return valid;
	}

	public boolean isAdminToken(String jwtToken) {
		if (jwtToken.trim().isBlank()) {
			return false;
		}

		boolean valid = false;
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);

//			Boolean admin = decoded.getClaim(Constants.PCOVAS_AUTH_TOKEN_ADMIN).asBoolean();
	//		valid = Boolean.TRUE.equals(admin);
		}catch(Exception e){
			e.printStackTrace();
			}
		return valid;
	}
	public Long getUserIdByToken(String jwtToken) {
		if (jwtToken.trim().isBlank()) {
			return null;
		}
		Long userId = null;
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);
			userId = decoded.getClaim("userId").asLong();
			String userName = decoded.getClaim("usrename").asString();
			log.debug("Utente verificato! " + userId + " - " + userName);
		} catch (Exception e) {
			e.printStackTrace();
			userId = null;
		}
		return userId;
	}
}
