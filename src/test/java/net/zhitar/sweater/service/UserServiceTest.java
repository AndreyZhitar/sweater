package net.zhitar.sweater.service;

import net.zhitar.sweater.domain.Role;
import net.zhitar.sweater.domain.User;
import net.zhitar.sweater.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUser() {
        User user = new User();

        user.setEmail("some@gmail.com");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        verify(userRepository, times(1)).save(user);
        verify(mailSender, times(1)).send(
                eq(user.getEmail()),
                anyString(),
                anyString());
    }

    @Test
    public void addUserFailTest() {
        User user = new User();

        user.setUsername("Tolik");

        doReturn(new User())
                .when(userRepository)
                .findByUsername(user.getUsername());

        boolean isUserCreated = userService.addUser(user);

        Assert.assertFalse(isUserCreated);

        verify(userRepository, times(0)).save(any(User.class));
        verify(mailSender, times(0)).send(
                anyString(),
                anyString(),
                anyString());
    }

    @Test
    public void activateUser() {
        User user = new User();

        user.setActivationCode("someCode");
        doReturn(user)
                .when(userRepository)
                .findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser("activate me");

        Assert.assertFalse(isUserActivated);

        verify(userRepository, times(0)).save(any(User.class));
    }
}