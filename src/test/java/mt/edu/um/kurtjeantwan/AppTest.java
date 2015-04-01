package mt.edu.um.kurtjeantwan;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 * Test for shell code
 */
public class AppTest 
{
    private App app1;
    
    @Before
    public void setup()
    {
        app1 = new App();   
    }
    
    @Test
    public void testMultiplication(){
        Assert.assertEquals(10, app1.multiplication(5,2));
    }
}
