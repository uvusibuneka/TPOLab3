package integration.trigonometry;

import org.example.trigonometry.Csc;
import org.example.trigonometry.Sin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CscIntegrationTest {
    private double accuracy = 1e-10;
    private Sin sinMock;
    private Csc csc;

    @BeforeEach
    public void initializeStub(){
        sinMock = mock(Sin.class);

        when(sinMock.calculateSin(-3*Math.PI/2, accuracy)).thenReturn(1.0);
        when(sinMock.calculateSin(-Math.PI, accuracy)).thenReturn(0.0);
        when(sinMock.calculateSin(-Math.PI/2, accuracy)).thenReturn(-1.0);
        when(sinMock.calculateSin(0.0, accuracy)).thenReturn(0.0);
        when(sinMock.calculateSin(Math.PI/2, accuracy)).thenReturn(1.0);
        when(sinMock.calculateSin(Math.PI, accuracy)).thenReturn(0.0);
        when(sinMock.calculateSin(3*Math.PI/2, accuracy)).thenReturn(-1.0);

        csc = new Csc(sinMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-3*Math.PI/2, -Math.PI, -Math.PI/2, 0.0, Math.PI/2, Math.PI, 3*Math.PI})
    public void testCscMockValue(double x){
        double sinValue = Math.sin(x);
        if(Math.abs(sinValue) < 1e-10){
            assertTrue(Double.isNaN(csc.calculateCosec(x, accuracy)));
        }else{
            double expected = 1/sinValue;
            double actual = csc.calculateCosec(x, accuracy);
            assertEquals(expected, actual, 1e-8);
        }
        verify(sinMock, times(1)).calculateSin(x, accuracy);
    }
}
