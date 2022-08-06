package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.PasswordTokenEntity;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.repository.PasswordTokenRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional
public class UserSecurityService {
    private PasswordTokenRepository passwordTokenRepository;

    public void createPasswordResetTokenForUser(String token, UserEntity user) {
        PasswordTokenEntity myToken = new PasswordTokenEntity(token, user);
        passwordTokenRepository.save(myToken);
    }
    public String validatePasswordResetToken(String token) {
        PasswordTokenEntity passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken" : null;
    }


    private boolean isTokenFound(PasswordTokenEntity passToken) {
        return passToken != null;
    }
}
