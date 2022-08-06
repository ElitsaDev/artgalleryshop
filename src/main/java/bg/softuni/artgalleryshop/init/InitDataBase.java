package bg.softuni.artgalleryshop.init;

import bg.softuni.artgalleryshop.service.UserRoleService;
import bg.softuni.artgalleryshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataBase implements CommandLineRunner {
    private final UserRoleService userRoleService;
    private final UserService userService;

    public InitDataBase(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        //Initialization of user roles
        userRoleService.initializeRoles();

        //Initialization of first user - ADMIN
        userService.adminAddUserRoles();

    }
}
