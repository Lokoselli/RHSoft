package br.com.gabriel.rhsoft.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.gabriel.rhsoft.conf.AppWebConfiguration;
import br.com.gabriel.rhsoft.conf.DataSourceConfigurationTest;
import br.com.gabriel.rhsoft.conf.JPAConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class HomeControllerTest extends ControllerTest{

    @Test
    public void returnToHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
    }
    
}