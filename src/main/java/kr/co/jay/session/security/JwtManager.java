package kr.co.jay.session.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import static java.util.stream.Collectors.toList;
import static kr.api.security.Constants.EXPIRATION_TIME;
import static kr.api.security.Constants.ROLE_CLAIM;


@Component
public class JwtManager {

  private final RSAPrivateKey privateKey;
  private final RSAPublicKey publicKey;

  public JwtManager(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }


}
