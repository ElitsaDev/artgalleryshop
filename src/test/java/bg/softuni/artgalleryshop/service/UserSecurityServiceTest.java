package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.PasswordTokenEntity;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.repository.PasswordTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserSecurityServiceTest {
    @Mock
    private PasswordTokenRepository passwordTokenRepository;

    private UserSecurityService underTest;
    private UserEntity userEntity;
    private UserRoleEntity userRole;

    private PasswordTokenEntity passwordTokenEntity;

    @BeforeEach
    void setUp() {
        underTest = new UserSecurityService(passwordTokenRepository);
        userEntity = new UserEntity();
        userRole = new UserRoleEntity();
        userRole.setUserRole(UserRoleEnum.USER);

        userEntity.setUsername("nameIt")
                .setPassword("123456")
                .setEmail("petar@abv.bg")
                .setFirstName("Petar").setLastName("Petrov");
        passwordTokenEntity = new PasswordTokenEntity();
        passwordTokenEntity.setUser(userEntity);
        passwordTokenEntity.setToken("testToken");
    }

    @Test
    public void createPasswordResetTokenForUser(){
      underTest.createPasswordResetTokenForUser("testToken",userEntity);
        String token = passwordTokenEntity.getToken();

        Assertions.assertNotNull(token);
    }

    @Test
    public void validatePasswordResetToken(){
       var valid = underTest.validatePasswordResetToken("testToken");
        Assertions.assertNotNull(valid);
    }
}
