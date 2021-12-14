package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.repository.ClientDetailsCassandraDao;
import com.auth.server.repository.UserDetailsCassandraDao;
import com.auth.server.util.ApplicationException;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class CredentialsServiceImplTest {

    @InjectMocks
    private CredentialsServiceImpl credentialsService;
    @Mock
    private UserDetailsCassandraDao userDetailsCassandraDao;
    @Mock
    private ClientDetailsCassandraDao clientDetailsCassandraDao;
    @Mock
    private PasswordEncoder passwordEncoder;
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
    public void getUserDetailsFromDbTest() throws ApplicationException {
        Mockito.when(userDetailsCassandraDao.findByUser(Mockito.anyString())).thenReturn(userRecord);
        UserDetailsModel testResponse = credentialsService.getUserDetailsFromDb("sample");
        assertEquals(userRecord.getUsername(), testResponse.getUsername(), "Username not equal");
        assertEquals(userRecord.getPassword(), testResponse.getPassword(), "Password not equal");
        assertEquals(userRecord.getRole(), testResponse.getRole(), "Roles are not equal");
        assertEquals(userRecord.isEnabled(), testResponse.isEnabled(), "Enabled flag not equal");
    }

    @Test
    public void getUserDetailsFromDbNotFoundTest() {
        Mockito.when(userDetailsCassandraDao.findByUser(Mockito.anyString())).thenReturn(null);
        try {
            UserDetailsModel testResponse = credentialsService.getUserDetailsFromDb("sample");
        } catch (ApplicationException exception) {
            assertEquals("Requested username not found", exception.getMessage());
        }
    }

    @Test
    public void onBoardUserCredentialsTest() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(userDetailsCassandraDao.save(Mockito.any(UserDetailsModel.class))).thenReturn(rs);
        Mockito.when(rs.wasApplied()).thenReturn(true);
        assertDoesNotThrow(() -> credentialsService.onBoardUserCredentials(userRecord), "Exception thrown in method");
    }

    @Test
    public void onBoardUserCredentialsUnSuccessFulWriteTest() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(userDetailsCassandraDao.save(Mockito.any(UserDetailsModel.class))).thenReturn(rs);
        Mockito.when(rs.wasApplied()).thenReturn(false);
        Exception exception = assertThrows(ApplicationException.class, () -> credentialsService.onBoardUserCredentials(userRecord));
        assertEquals("User record already exists", exception.getMessage());
    }

    @Test
    public void onBoardClientDetailsTest() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(clientDetailsCassandraDao.save(Mockito.any(ClientDetailsModel.class))).thenReturn(rs);
        Mockito.when(rs.wasApplied()).thenReturn(true);
        assertDoesNotThrow(() -> credentialsService.onBoardClientDetails(clientRecord), "Exception thrown in method");
    }

    @Test
    public void onBoardClientDetailsTestUnSuccessFulWriteTest() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(clientDetailsCassandraDao.save(Mockito.any(ClientDetailsModel.class))).thenReturn(rs);
        Mockito.when(rs.wasApplied()).thenReturn(false);
        Exception exception = assertThrows(ApplicationException.class, () -> credentialsService.onBoardClientDetails(clientRecord));
        assertEquals("Client record already exists", exception.getMessage());
    }

    @Test
    public void onBoardResourceAppTest() throws ApplicationException {
        Mockito.when(clientDetailsCassandraDao.findByClientId(Mockito.anyString())).thenReturn(clientRecord);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(clientDetailsCassandraDao.save(Mockito.any(ClientDetailsModel.class))).thenReturn(rs);
        credentialsService.onBoardResourceApp("client", "newResource");
        assertEquals("newResource", clientRecord.getResourceId(), "Resource is not set properly");
    }

    @Test
    public void onBoardResourceAppNotFoundTest() {
        Mockito.when(clientDetailsCassandraDao.findByClientId(Mockito.anyString())).thenReturn(null);
        Exception exception = assertThrows(ApplicationException.class, () -> credentialsService.onBoardResourceApp("client", "newResource"));
        assertEquals("Client not found, cannot add resource", exception.getMessage());
    }
}
