package xyz.subho.covidhelp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HomeController.class})
@ExtendWith(SpringExtension.class)
public class HomeControllerTest {
    @Autowired
    private HomeController homeController;

    @Test
    public void testDashboard() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dashboard");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("dashboard.html"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("dashboard.html"));
    }

    @Test
    public void testDashboard2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dashboard", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("dashboard.html"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("dashboard.html"));
    }

    @Test
    public void testHome() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("home.html"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home.html"));
    }

    @Test
    public void testHome2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("home.html"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home.html"));
    }

    @Test
    public void testUserFront() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userFront");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userFront.html"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userFront.html"));
    }

    @Test
    public void testUserFront2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/userFront");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userFront.html"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userFront.html"));
    }
}

