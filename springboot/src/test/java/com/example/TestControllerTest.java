package com.example;

//import jdk.jfr.internal.test.WhiteBox;
import com.example.controller.TestController;
import org.junit.jupiter.api.Test;

public class TestControllerTest {
    @Test
    void test01(){
        System.out.println("001");
    }

    @Test
    public void hello(){
        TestController testController = new TestController();
        String ret = testController.hello(1,2);
        System.out.println(ret);
    }

    @Test
    public void pleaseTest(){
        TestController testController = new TestController();
//        Whitebo
    }
}
