package com.junit.tut;


import org.junit.*;
//import org.junit.rules.*;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.verify;

import mockit.*;

abstract class BaseService {
    public int save() {
    	System.out.println("base service save executing...");
        return 2;
    }
    public abstract void blabla();

}

class ChildService extends BaseService {
    public int save() {
        System.out.println("child service save executing...");
        int y = super.save();
        return 1 + y;
    }

	@Override
	public void blabla() {
		// TODO Auto-generated method stub
	}
}

class MockBase extends MockUp<BaseService>{
    @Mock
    public int save() {
        System.out.println("mocked base");
        return 9;
    }
}

// Deprecated code
//@MockClass(realClass = BaseService.class)
//class MockBase {

//    @Mock
//    public int save() {
//        System.out.println("mocked base");
//        return 9;
//    }
//}

public class TestSuperCall {

    @Test
    public void testSave() throws Exception {
        MockBase mockBase = new MockBase();
        ChildService childService = new ChildService();

        Assert.assertEquals(9 + 1, childService.save());
        new Verifications() {{ mockBase.save(); }};
        
        //Mockit.tearDownMocks();
    }

}