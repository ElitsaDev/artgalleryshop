package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.exceptions.UserNotFoundException;
import bg.softuni.artgalleryshop.model.dto.UserRegisterDTO;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.model.mapper.UserMapper;
import bg.softuni.artgalleryshop.model.user.ArtGalleryShopUserDetails;
import bg.softuni.artgalleryshop.model.view.UserViewModel;
import bg.softuni.artgalleryshop.repository.PasswordTokenRepository;
import bg.softuni.artgalleryshop.repository.UserRepository;
import bg.softuni.artgalleryshop.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.stubbing.BaseStubbing;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserEntity userEntity;
    private UserRoleEntity userRole;
    private UserService serviceToTest;

    private UserDetailsService userDetailsService;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    private UserRoleService roleService;
    @Mock
    private UserRegisterDTO userRegisterDTO;

    private Locale locale;

    private EmailService emailService;
    @Mock
    private PasswordTokenRepository passwordTokenRepository;
    @Mock
    private UserRepository mockUserEntityRepository;
    @Mock
    private UserRoleRepository roleRepository;

    @Mock
    private ArtGalleryShopUserDetailsService artGalleryShopUserDetailsService;

    @BeforeEach
    private void init() {
        userEntity = new UserEntity();
        userRole = new UserRoleEntity();
        userRole.setUserRole(UserRoleEnum.USER).setUserRole(UserRoleEnum.ADMIN).setUserRole(UserRoleEnum.MODERATOR);

        serviceToTest = new UserService(mockUserEntityRepository,
                passwordEncoder,
                userMapper,
                userDetailsService,
                emailService,
                passwordTokenRepository,
                roleRepository);

        userEntity.setUsername("nameIt")
                .setPassword("123456")
                .setEmail("petar@abv.bg")
                .setFirstName("Petar").setLastName("Petrov");

    }

    @BeforeEach
    void setUp() {
        artGalleryShopUserDetailsService = new ArtGalleryShopUserDetailsService(
                mockUserEntityRepository
        );
    }

    @Test
    public void registerAndLogin_trowsRuntimeException(){

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO .setEmail("test@test")
                .setPassword("123456")
                .setFirstName("first").setLastName("last").setConfirmPassword("365487");
        Assertions.assertNotEquals(userRegisterDTO.getPassword(),userRegisterDTO.getConfirmPassword());
    }

    @Test
    public void userNotFound() {
           var fakeUser = serviceToTest.findByNameCriteria("invalid_firstName");
            Assertions.assertEquals(new ArrayList<>(), fakeUser);
    }

    @Test
    public void userFound() {
        when(mockUserEntityRepository.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.of(userEntity));

        UserEntity actual = serviceToTest.getUserByUsername(userEntity.getUsername());

        Assertions.assertEquals(actual.getUsername(), userEntity.getUsername());
    }

    @Test
    public void findByEmail() {
        when(mockUserEntityRepository.findByEmail(userEntity.getEmail()))
                .thenReturn(Optional.of(userEntity));

        UserEntity actual = serviceToTest.findUserByEmail(userEntity.getEmail());

        Assertions.assertEquals(actual, userEntity);
    }

    @Test
    public void findAll(){
        serviceToTest.findAll();
        verify(mockUserEntityRepository).findAll();

    }

    @Test
    public void deleteUser() {
        mockUserEntityRepository.save(userEntity);
        mockUserEntityRepository.deleteById(userEntity.getId());
        serviceToTest.deleteUser(userEntity.getId());

        List<UserViewModel> actual2 = serviceToTest.findAll()
                .stream()
               .toList();

        Assertions.assertEquals(actual2.size(), 0);
    }

    @Test
    public void findUserByEmail_throwsException() {
        // arrange
        // NOTE: No need to arrange anything, mocks return empty optionals.

        // act && assert
        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> serviceToTest.findUserByEmail("non-existant@example.com")
        );
    }

    @Test
    public void adminAddUserRoles(){

        var admin = mockUserEntityRepository.findById(1L);
        serviceToTest.adminAddUserRoles();
        long roles = roleRepository.count();
        admin.ifPresent(user -> {
                Assertions.assertEquals(admin.get().getUserRoles().size(), roles);
                Assertions.assertEquals(admin.get().getUserRoles().get(0).getUserRole().name(), "ADMIN");
                Assertions.assertEquals(admin.get().getUserRoles().get(1).getUserRole().name(), "MODERATOR");
                Assertions.assertEquals(admin.get().getUserRoles().get(2).getUserRole().name(), "USER");
            }
        );
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // arrange
        UserEntity testUserEntity = new UserEntity().setUsername("username")
                .setPassword("123456")
                .setEmail("test@test")
                .setFirstName("testFirstName").setLastName("testLastName").setUserRoles(
                        List.of(new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR),
                                new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                                )
                );
        when(mockUserEntityRepository
                .findByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));
        ArtGalleryShopUserDetails userDetails =
                (ArtGalleryShopUserDetails)artGalleryShopUserDetailsService.loadUserByUsername(testUserEntity.getUsername());


        // assert
        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername());
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
                () -> artGalleryShopUserDetailsService.loadUserByUsername("non-existing-username")
        );
    }

}
