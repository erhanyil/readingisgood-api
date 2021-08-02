package com.example.demo.util;

import com.example.readingisgood.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.HashMap;


@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {

    @Test
    public void convert_with_str_param() {
        String date = "12.12.2012";
        Date result = DateUtil.convert(date);
        Assert.assertNotNull(result);
    }

    @Test
    public void convert_with_date_param() {
        Date date = new Date();
        String result = DateUtil.convert(date);
        Assert.assertNotNull(result);
    }

    @Test(expected = RuntimeException.class)
    public void convert_with_invalid_format() {
        String date = "12.122012";
        DateUtil.convert(date);
    }

    @Test
    public void getDatesFromYear() {
        Integer year = 2021;
        HashMap<String, Date> result = DateUtil.getDatesFromYear(year);
        Assert.assertEquals(result.size(), 2);
    }
}