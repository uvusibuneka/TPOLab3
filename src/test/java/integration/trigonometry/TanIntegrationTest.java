package integration.trigonometry;

import org.example.trigonometry.Cos;
import org.example.trigonometry.Sin;
import org.example.trigonometry.Tan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TanIntegrationTest {
    private double accuracy = 1e-10;
    private Sin sinMock;
    private Cos cosMock;
    private Tan tan;

    @BeforeEach
    public void initializeStub(){
        sinMock = mock(Sin.class);
        cosMock = mock(Cos.class);

        when(sinMock.calculateSin(-3 * Math.PI / 2, accuracy)).thenReturn(1.0);
        when(sinMock.calculateSin(-Math.PI, accuracy)).thenReturn(0.0);
        when(sinMock.calculateSin(-Math.PI / 2, accuracy)).thenReturn(-1.0);
        when(sinMock.calculateSin(0.0, accuracy)).thenReturn(0.0);
        when(sinMock.calculateSin(Math.PI / 2, accuracy)).thenReturn(1.0);
        when(sinMock.calculateSin(Math.PI, accuracy)).thenReturn(0.0);
        when(sinMock.calculateSin(3 * Math.PI / 2, accuracy)).thenReturn(-1.0);

        when(cosMock.calculateCos(-3 * Math.PI / 2, accuracy)).thenReturn(0.0);
        when(cosMock.calculateCos(-Math.PI, accuracy)).thenReturn(-1.0);
        when(cosMock.calculateCos(-Math.PI / 2, accuracy)).thenReturn(0.0);
        when(cosMock.calculateCos(0.0, accuracy)).thenReturn(1.0);
        when(cosMock.calculateCos(Math.PI / 2, accuracy)).thenReturn(0.0);
        when(cosMock.calculateCos(Math.PI, accuracy)).thenReturn(-1.0);
        when(cosMock.calculateCos(3 * Math.PI / 2, accuracy)).thenReturn(0.0);

        tan = new Tan(sinMock, cosMock);

    }

    @ParameterizedTest
    @ValueSource(doubles = {-3*Math.PI/2, -Math.PI, -Math.PI/2, 0.0, Math.PI/2, Math.PI, 3*Math.PI/2})
    public void testTanMockValues(double x){
        double expected = Math.tan(x);
        double actual = tan.calculateTan(x, accuracy);

        if(Math.abs((x % Math.PI) - Math.PI/2)< 1e-10 || x == -3*Math.PI/2 || x == -Math.PI/2){
            assertTrue(Double.isNaN(actual));
        }else{
            assertEquals(expected, actual, 1e-8);
        }

        verify(sinMock, times(1)).calculateSin(x, accuracy);
        verify(cosMock, times(1)).calculateCos(x, accuracy);
    }

}