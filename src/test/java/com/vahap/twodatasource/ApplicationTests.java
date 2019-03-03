package com.vahap.twodatasource;

import com.vahap.twodatasource.config.DataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataSourceConfig.class})
public class ApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertEquals(true, true);
    }

}
