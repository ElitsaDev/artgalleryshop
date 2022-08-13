package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {
    @Mock
    private UserRoleRepository mockUserRoleRepository;

    private UserRoleEnum userRoleEnum;
    @InjectMocks
    private UserRoleService toTest;

    private UserRoleEntity userRoleEntity;

    @BeforeEach
    void setUp(){
        toTest = new UserRoleService(mockUserRoleRepository);
        userRoleEntity = new UserRoleEntity();

    }

    @Test
    void testGetUserRoleEnumValues(){
        assertAll(
                ()-> assertEquals("ADMIN", UserRoleEnum.ADMIN.name()),
                ()-> assertEquals("MODERATOR", UserRoleEnum.MODERATOR.name()),
                ()-> assertEquals("USER", UserRoleEnum.USER.name())

        );
    }

//    @Test
//    void initializeRoles_IfNotExist(){
//        toTest.initializeRoles();
//        userRoleEntity.setUserRole(UserRoleEnum.ADMIN);
//       UserRoleEntity userRole = mockUserRoleRepository.save(userRoleEntity.setId(1l));
//
//        Assertions.assertEquals("ADMIN",userRole.getUserRole().name());
//
//    }
}
