import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JunitExersiceTest {

    private static int a = 0;
    private int b = 0;
    private int c = 0;

    @BeforeClass
    public static void assignClassValueA(){
        a=2;
    }

    @Before
public void assignAValue() {
    b=8;
    c=3;

}

@Test
    public void junitExersises () {
        assertTrue("a is equal to 2", a == 2);
        assertEquals("a plus b times 3 is 30", 30, (a + b) * c);

//use assertThat to cover varios matchers instead of using all of the different asserts
        assertThat("a plus b is 10",a+b,is(10));
        assertThat("a equals 2 is true",a==2, is(true));
        assertThat("a is not 1",a ==1,is(false));


    }



}
