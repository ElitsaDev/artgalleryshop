package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @InjectMocks
    private UserRoleService toTest;
    private UserRoleEntity userRoleEntity;

    private UserRoleEnum userRoleEnum;

    @BeforeEach
    void setUp(){
        toTest = new UserRoleService(mockUserRoleRepository);
        userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRole(UserRoleEnum.USER);

    }

    @Test
    void testGetUserRoleEnumValues(){
        assertAll(
                ()-> assertEquals("ADMIN", UserRoleEnum.ADMIN.name()),
                ()-> assertEquals("MODERATOR", UserRoleEnum.MODERATOR.name()),
                ()-> assertEquals("USER", UserRoleEnum.USER.name())

        );
    }
    @Test
    void findByRoleNameDoesntExist(){
        userRoleEnum = UserRoleEnum.valueOf("USER");
        when(mockUserRoleRepository.findByUserRole(UserRoleEnum.USER)).thenReturn(Optional.empty());
        toTest.initializeRoles();

        Assertions.assertNotEquals(userRoleEnum,mockUserRoleRepository.findByUserRole(UserRoleEnum.USER));

    }


}
