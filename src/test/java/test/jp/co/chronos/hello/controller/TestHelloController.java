package test.jp.co.chronos.hello.controller;

import jp.co.chronos.hello.configuration.ApplicationConfiguration;
import jp.co.chronos.hello.configuration.ApplicationPersistenceConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import test.jp.co.chronos.hello.configuration.TestApplication;
import test.jp.co.chronos.hello.configuration.TestApplicationDataSourceConfiguration;

import javax.sql.DataSource;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={TestApplication.class, TestApplicationDataSourceConfiguration.class , ApplicationConfiguration.class, ApplicationPersistenceConfiguration.class})
public class TestHelloController {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private DataSource dataSource;
    @Before
    public void setUp() {
        Operation operation = sequenceOf(
                insertInto("MESSAGE")
                        .columns(
                                "MESSAGE_ID", "MESSAGE")
                        .values("1","Hello")
                        .build());
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @Test
    public void HelloTest() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Hello"));
    }
    @After
    public void tearDown() throws Exception {
        Operation operation = deleteAllFrom("MESSAGE");
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }
}
