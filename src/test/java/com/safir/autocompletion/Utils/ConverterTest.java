package com.safir.autocompletion.Utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConverterTest {

    @Test
    public void emptyListConvertionCheck() {
        List<String> list = null;
        Assert.assertEquals (null, Converter.listToMultiLineString (list));
        list = new ArrayList<> ();
        Assert.assertEquals (null, Converter.listToMultiLineString (list));
    }

    @Test
    public void checkListToStringConvertion(){
        List<String> list = new ArrayList<> ();
        StringBuffer expectedString = new StringBuffer ();
        list.add ("kolkata");
        expectedString.append ("kolkata").append ("\n");
        list.add ("kota");
        expectedString.append ("kota").append ("\n");
        list.add ("hyderabad");
        expectedString.append ("hyderabad").append ("\n");
        Assert.assertEquals (expectedString.toString (), Converter.listToMultiLineString (list));
    }
}
