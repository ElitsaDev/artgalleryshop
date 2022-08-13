package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.model.user.ArtGalleryShopUserDetails;
import bg.softuni.artgalleryshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtGalleryShopUserDetailsServiceTest {
  @Mock
  private UserRepository mockUserRepo;

  private ArtGalleryShopUserDetailsService toTest;

  @BeforeEach
  void setUp() {
    toTest = new ArtGalleryShopUserDetailsService(
        mockUserRepo
    );
  }

  @Test
  void testLoadUserByUsername_UserExists() {
    // arrange
    UserEntity testUserEntity =
        new UserEntity().
                setUsername("pesho").
                setPassword("123456").
                setEmail("pesho@example.com").
                setFirstName("Pesho").
                setLastName("Peshov").
                setUserRoles(
                List.of(
                    new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                    new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR),
                    new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                )
            );

    when(mockUserRepo.findByUsername(testUserEntity.getUsername())).
        thenReturn(Optional.of(testUserEntity));

    // act
    ArtGalleryShopUserDetails userDetails = (ArtGalleryShopUserDetails)
      toTest.loadUserByUsername(testUserEntity.getUsername());

    // assert
    Assertions.assertEquals(testUserEntity.getUsername(),
        userDetails.getUsername());
    Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
    Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
    Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
    Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
            userDetails.getFullName());


    var authorities = userDetails.getAuthorities();

    Assertions.assertEquals(3, authorities.size());

    var authoritiesIter = authorities.iterator();

    Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
        authoritiesIter.next().getAuthority());
    Assertions.assertEquals("ROLE_" + UserRoleEnum.MODERATOR.name(),
        authoritiesIter.next().getAuthority());
    Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
        authoritiesIter.next().getAuthority());
  }

  @Test
  void testLoadUserByUsername_UserDoesNotExist() {

    // arrange
    // NOTE: No need to arrange anything, mocks return empty optionals.

    // act && assert
    Assertions.assertThrows(
        UsernameNotFoundException.class,
        () -> toTest.loadUserByUsername("non-exist-user")
    );
  }
}
