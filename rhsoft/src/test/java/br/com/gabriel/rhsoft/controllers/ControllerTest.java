package br.com.gabriel.rhsoft.controllers;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    protected String controllerPath;

    protected String webInfPath = "/WEB-INF/views/";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    protected String jspUrl(String jspName){
        return webInfPath + controllerPath + "/" + jspName + ".jsp";
    }
}