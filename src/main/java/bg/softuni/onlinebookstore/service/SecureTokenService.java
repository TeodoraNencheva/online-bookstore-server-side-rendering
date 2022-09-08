package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.entity.SecureTokenEntity;
import bg.softuni.onlinebookstore.repositories.SecureTokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class SecureTokenService {
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;

    private final SecureTokenRepository secureTokenRepository;

    public SecureTokenService(SecureTokenRepository secureTokenRepository) {
        this.secureTokenRepository = secureTokenRepository;
    }

    @Value("${secure.token.validity}")
    private int tokenValidityInHours;

    public SecureTokenEntity createSecureToken(){
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        SecureTokenEntity secureTokenEntity = new SecureTokenEntity();
        secureTokenEntity.setToken(tokenValue);
        secureTokenEntity.setExpireAt(LocalDateTime.now().plusHours(tokenValidityInHours));
        this.secureTokenRepository.save(secureTokenEntity);
        return secureTokenEntity;
    }
}
