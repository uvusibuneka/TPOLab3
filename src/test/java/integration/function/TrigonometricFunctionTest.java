package integration.function;

import org.example.function.TrigonometricFunction;
import org.example.trigonometry.Csc;
import org.example.trigonometry.Tan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrigonometricFunctionTest {
    private double accuracy = 1e-10;
    private Tan tanMock;
    private Csc cscMock;
    private TrigonometricFunction trigFunc;

    @BeforeEach
    public void initializeStub(){
        tanMock = mock(Tan.class);
        cscMock = mock(Csc.class);

        when(tanMock.calculateTan(2.0, accuracy)).thenReturn(-2.18503986326);
        when(tanMock.calculateTan(-1.0, accuracy)).thenReturn(-1.55740772465);
        when(tanMock.calculateTan(-5.0, accuracy)).thenReturn(3.38051500625);
        when(tanMock.calculateTan(-10.0, accuracy)).thenReturn(-0.648360827459);
        when(tanMock.calculateTan(-15.0, accuracy)).thenReturn(0.855993400909);
        when(tanMock.calculateTan(-50.0, accuracy)).thenReturn(0.271900611998);

        when(cscMock.calculateCosec(2.0, accuracy)).thenReturn(1.09975017029);
        when(cscMock.calculateCosec(-1.0, accuracy)).thenReturn(-1.18839510578);
        when(cscMock.calculateCosec(-5.0, accuracy)).thenReturn(1.04283521277);
        when(cscMock.calculateCosec(-10.0, accuracy)).thenReturn(1.83816396089);
        when(cscMock.calculateCosec(-15.0, accuracy)).thenReturn(-1.53778056154);
        when(cscMock.calculateCosec(-50.0,accuracy)).thenReturn(3.81134085787);

        trigFunc = new TrigonometricFunction(tanMock, cscMock);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/trig_func_input.csv", numLinesToSkip = 1)
    public void testTrigFunc(double x){
        double expected = (tanMock.calculateTan(x, accuracy) - cscMock.calculateCosec(x, accuracy));
        double actual = trigFunc.calculateTrigFunc(x, accuracy);

        assertEquals(expected, actual, accuracy);
    }
}
