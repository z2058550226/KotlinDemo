package test.com.suikajy.kotlinDemo.ch10_annotation_reflect;

import com.suikajy.kotlinDemo.ch10_annotation_reflect.junit_test.Demo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** 
* Demo Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ�� 19, 2018</pre> 
* @version 1.0 
*/ 
public class DemoTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: add1() 
* 
*/ 
@Test
public void testAdd1() throws Exception {
    assertEquals(1, Demo.add1());
} 

/** 
* 
* Method: add2() 
* 
*/ 
@Test
public void testAdd2() throws Exception {
    assertEquals(1, Demo.add2());
} 


} 
