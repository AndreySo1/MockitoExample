package org.example;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CarTest {

    @Mock
    Car myCar;

    @BeforeTest
    void setupMock(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void createCar(){
        Car car = new Car("BMW", "123", 2014, "Andrey"); // create usual object
        Car newCar = Mockito.mock(Car.class); // create object with Machito

        Assert.assertEquals(car.getManufacturer(), "BMW");
        Assert.assertEquals(newCar.getManufacturer(), null);
    }

    @Test
    void remoteServiseReturnValue(){
        Car newCar = Mockito.mock(Car.class);
//        Car newCar = new Car("BMW", "123", 2014, "Andrey");
        when(newCar.testInt(4)).thenReturn(10);//!!!!!!!!! // set return if we use method with parametr
        Assert.assertEquals(newCar.testInt(4), 10); //ok
        Assert.assertEquals(newCar.testInt(4), 8); // errror
        System.out.println(newCar.testInt(4));
    }

    @Test
    void getOwner(){
        Car newCar = Mockito.mock(Car.class);
        when(newCar.getOwner()).thenReturn("Andrey"); // if we use method getOwner(), program return "Andrey"

        Assert.assertEquals(newCar.getOwner(), "Andrey");
        System.out.println(newCar.getOwner());
    }

    @Test
    void verificationTest(){
        Car newCar = Mockito.mock(Car.class);
        System.out.println(newCar.getOwner());
        verify(newCar).getOwner(); // if we use in this test methood newCar.getOwner() , return ok, else return error
    }

    @Test
    void verificationMetodParametrTest(){
        Car newCar = Mockito.mock(Car.class);
        newCar.setOwner("Andrey1");
        verify(newCar).setOwner("Andrey1");//if we use in this test methood newCar.setOwner() with parametr"Andrey", return OK, if use with anther parametr return error
    }

    @Test
    void mockAnotation(){
        when(myCar.testInt(3)).thenReturn(333);
        Assert.assertEquals(myCar.testInt(3), 333); //init myCar in Befor anotatio
    }

    @Test
    void verificationTimesTest(){
        myCar.getOwner();
        myCar.getOwner();

        verify(myCar, times(2)).getOwner(); // count how much times call methods
//        verify(myCar, never()).getOwner();  // method never called
//        verify(myCar, atLeast(2)).getOwner();  // method called min 2 times or more
//      verify(myCar, atMost(2)).getOwner();  // method called max 2 times or more
//      verify(myCar, only()).getOwner();  // if use only this class 1 times - true
    }

    @Test
    void mockAlternativeSintacses(){
//        when(myCar.testInt(3)).thenReturn(333);
        given(myCar.testInt(3)).willReturn(444);
//        Assert.assertEquals(myCar.testInt(3), 333); //init myCar in Befor anotation
        Assert.assertEquals(myCar.testInt(3), 444); //init myCar in Befor anotation
    }

    @Test
    void mockAnyInt(){
        when(myCar.testInt(anyInt())).thenReturn(11111);
        Assert.assertEquals(myCar.testInt(356), 333); //error! anyInt get result 11111
    }

    @Test(expectedExceptions = Exception.class)
    void mockAnyIntEception() throws Exception{
        when(myCar.testInt(anyInt())).thenThrow(Exception.class);
        Assert.assertEquals(myCar.testInt(333), 333); //anyInt get exception
    }
}
