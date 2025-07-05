package com.nisum.user.service;

import com.nisum.user.email.EmailSender;
import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    @Spy
    private UserService userService;

    private final String testEmail = "test@example.com";
    private final User user = User.builder()
            .email(testEmail)
            .name("Anurag")
            .build();

    @Captor private ArgumentCaptor<String> toCaptor;
    @Captor private ArgumentCaptor<String> subjectCaptor;
    @Captor private ArgumentCaptor<String> bodyCaptor;

    @BeforeEach
    void stubRepository() {
        when(userRepository.findByEmail(testEmail)).thenReturn(null, user);
    }

    @Test
    void givenTwoInvocations_whenFirstUserMissing_thenFallbackAndThenEmailSent() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.processUser(testEmail));
        assertEquals("User not found", ex.getMessage());

        verify(userService, times(1)).handleMissingUser(testEmail);

        userService.processUser(testEmail);

        verify(emailSender).send(toCaptor.capture(), subjectCaptor.capture(), bodyCaptor.capture());

        assertEquals(testEmail, toCaptor.getValue());
        assertEquals("Welcome", subjectCaptor.getValue());
        assertTrue(bodyCaptor.getValue().contains("Anurag"));
    }
}
