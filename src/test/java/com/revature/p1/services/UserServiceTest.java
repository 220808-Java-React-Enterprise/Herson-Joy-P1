package com.revature.p1.services;

import com.revature.P1.daos.UserDAO;
import com.revature.P1.dtos.requests.LoginRequest;
import com.revature.P1.dtos.responses.Principal;
import com.revature.P1.models.User;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.AuthenticationException;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.RegistrationPendingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    /* dependency injection */
    private UserService sut; // sut = system under test
    private final UserDAO mockUserDao = mock(UserDAO.class);

    /* jank constructor */
    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }

/*
    Common JUnit annotations:
        - @Test (marks a method as a test case)
        - @Ignore (tells JUnit to skip this test case)
        - @Before (logic that runs once before every test case)
        - @After (logic that runs once after every test case)
        - @BeforeClass (logic that runs only once before all test cases)
        - @AfterClass (logic that runs only once after all test cases)
 */

    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        // Arrange
        String validUsername = "baowow123";

        // Act
        boolean flag = sut.isValidUsername(validUsername);

        // Assert
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_givenInCorrectUsername() {
        // Arrange
        String invalidUsername = "2short";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_givenEmptyUsername() {
        // Arrange
        String invalidUsername = "";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_givenOnlyNumbers() {
        // Arrange
        String invalidUsername = "123456";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_startingWithUnderscore() {
        // Arrange
        String invalidUsername = "_baowow123";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = RegistrationPendingException.class)
    public void test_login_validLoginGivenCorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "baowow123";
        String validPassword = "Passw0rd";

        LoginRequest loginRequest = new LoginRequest(validUsername, validPassword);
        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDao.getUserByUsernameAndPassword(loginRequest.getUsername(), spiedSut.MD5HashPassword(validPassword))).thenReturn(new User());

        // Act
        Principal principal = spiedSut.login(loginRequest);

        // Assert
        Assert.assertNotNull(principal);
        verify(mockUserDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }

    @Test(expected = AuthenticationException.class)
    public void test_login_invalidLoginGivenIncorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        LoginRequest loginRequest = new LoginRequest(invalidUsername, invalidPassword);
        when(mockUserDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);

        // Act
        sut.login(loginRequest);
    }

    @Test
    public void test_getUserById(){
        UserService spiedSut = Mockito.spy(sut);
        when(mockUserDao.getById("1")).thenReturn(new User("1", "user", "pass", "email.com", "cart"));

        User user = spiedSut.getUserById("1");
        Assert.assertNotNull(user);
    }
}
