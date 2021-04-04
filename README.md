# Mocking a super method invocation by jMockit on Eclipse
In order to do the unit test for my child class, I tried to mock the super's method which is called inside the child's method,
but it seems impossible with Mockito.
I searched somewhere and it it said that jMockit can do what I want. 
https://stackoverflow.com/questions/14125774/powermock-mocking-a-super-method-invocation
But some functions of JMockit were deprecated. So, I rewrite it. 
This post maybe useful if you want to use jMockit to mock super' method like me.
Below is how to import jMockit to Eclipse and mocking the super method.
```
abstract class BaseService { // jMockit can mock the super class no matter it is abstract or not
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
		// TODO 
	}
}

// Below is test part
class MockBase extends MockUp<BaseService>{
    @Mock
    public int save() {
        System.out.println("mocked base");
        return 9;
    }
}

public class TestSuperCall {
    @Test
    public void testSave() throws Exception {
        MockBase mockBase = new MockBase();
        ChildService childService = new ChildService();
        Assert.assertEquals(9 + 1, childService.save());
        new Verifications() {{ mockBase.save(); }};
    }
}
```
## Download jmockit jar file 
https://javadoc.io/doc/org.jmockit/jmockit/latest/index.html
For example, in this sample i downloaded jmockit-1.49.jar and put here.
![0](https://user-images.githubusercontent.com/73010204/113500342-211ad780-9558-11eb-8a71-25cc73a7d064.png)

## Add jmockit library to your project
![1](https://user-images.githubusercontent.com/73010204/113500262-7c989580-9557-11eb-80f8-1029b56eafa8.png)
![2](https://user-images.githubusercontent.com/73010204/113500349-3132b700-9558-11eb-94dd-ba8b15ea20b9.png)

## Config javaagent for your test where you want to use jmockit
![3](https://user-images.githubusercontent.com/73010204/113500701-1e6db180-955b-11eb-9604-2b4956395bbc.png)
![4](https://user-images.githubusercontent.com/73010204/113500704-20377500-955b-11eb-8e7c-948a59f490dd.png)

## Run test
Right click on the file containning your jMockit test code > Run As > JUnit Test

