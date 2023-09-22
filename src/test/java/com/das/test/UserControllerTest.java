package com.das.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.das.controller.UserController;
import com.das.dto.UserDTO;
import com.das.entity.User;
import com.das.exceptions.UserNotFoundException;
import com.das.login.Login;
import com.das.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testRegisterUser_Success() throws Exception {
        UserDTO dto = new UserDTO(1, "mohan", "mohan@gmail.com", "mohan1234", 24, "male");

        User user = new User();
        BeanUtils.copyProperties(dto, user);

        when(userService.saveUser(user)).thenReturn(user);

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    // Negative test case for registerUser
    @Test
    public void testRegisterUser_Failure() throws Exception {
        UserDTO dto = new UserDTO(1, "mohan", "mohan@gmail.com", "mohan1234", 24, "male");
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        when(userService.saveUser(user)).thenReturn(null);

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    // Positive test case for loginUser
    @Test
    public void testLoginUser_Success() throws Exception {
        Login login = new Login();
        login.setEmail("mohan@gmail.com");
        login.setPassword("mohan1234");
        
        when(userService.login(login)).thenReturn("Login Successfull");

        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk());
    }

    // Negative test case for loginUser
    @Test
    public void testLoginUser_Failure() throws Exception {
        Login login = new Login();

        when(userService.login(login)).thenThrow(new UserNotFoundException("Invalid Credentials"));

        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().is4xxClientError());
        
    }
}
