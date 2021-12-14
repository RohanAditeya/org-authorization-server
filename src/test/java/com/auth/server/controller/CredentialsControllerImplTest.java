package com.auth.server.controller;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.service.CredentialsServiceImpl;
import com.auth.server.util.ApplicationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class CredentialsControllerImplTest {

    @InjectMocks
    private CredentialsControllerImpl credentialsController;
    @Mock
    private CredentialsServiceImpl credentialsService;
    private UserDetailsModel userRecord = new UserDetailsModel();
    private ClientDetailsModel clientRecord = new ClientDetailsModel();

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userRecord.setUsername("sample");
        userRecord.setRole("ROLE_ADMIN");
        userRecord.setPassword("password");
        userRecord.setEnabled(true);
        clientRecord.setSecret("client_password");
        clientRecord.setClientId("client");
        clientRecord.setResourceId("resource");
        clientRecord.setScope("read");
        clientRecord.setGrantType("password");
    }

    @Test
    public void getUserDetailsTest() throws ApplicationException {
        Mockito.when(credentialsService.getUserDetailsFromDb(Mockito.anyString())).thenReturn(userRecord);
        ResponseEntity<UserDetailsModel> responseRecord = credentialsController.getUserDetails("sample");
        assertEquals(HttpStatus.OK, responseRecord.getStatusCode(), "Status code does not match");
        assertEquals(userRecord.getRole(), responseRecord.getBody().getRole(), "Roles are not equal");
        assertEquals(userRecord.getUsername(), responseRecord.getBody().getUsername(), "Username not equal");
        assertEquals(userRecord.getPassword(), responseRecord.getBody().getPassword(), "Password not equal");
        assertTrue(responseRecord.getBody().isEnabled(), "Enabled flag is not true");
    }

    @Test
    public void onBoardCredentialsTest() throws ApplicationException {
        Mockito.doNothing().when(credentialsService).onBoardUserCredentials(Mockito.any(UserDetailsModel.class));
        ResponseEntity<UserDetailsModel> response = credentialsController.onBoardCredentials(userRecord);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status code does not match");
    }

    @Test
    public void onBoardClientCredentialsTest() throws ApplicationException {
        Mockito.doNothing().when(credentialsService).onBoardClientDetails(Mockito.any(ClientDetailsModel.class));
        ResponseEntity<ClientDetailsModel> response = credentialsController.onBoardClientCredentials(clientRecord);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status code does not match");
    }

    @Test
    public void onBoardResourceAppTest() throws ApplicationException {
        Mockito.doNothing().when(credentialsService).onBoardResourceApp(Mockito.anyString(), Mockito.anyString());
        ResponseEntity<ClientDetailsModel> response = credentialsController.onBoardResourceApp("client", "newResource");
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status code does not match");
    }
}
