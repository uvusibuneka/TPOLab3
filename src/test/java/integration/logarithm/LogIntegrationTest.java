package integration.logarithm;

import org.example.logarithm.Ln;
import org.example.logarithm.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LogIntegrationTest {
    private double accuracy = 1e-10;
    private Ln lnMock;
    private Log log;

    @BeforeEach
    public void initializeStub(){
        lnMock = mock(Ln.class);
        when(lnMock.calculateLn(1.0, accuracy)).thenReturn(0.0);
        when(lnMock.calculateLn(2.0, accuracy)).thenReturn(0.693147);
        when(lnMock.calculateLn(3.0, accuracy)).thenReturn(1.09861228867);
        when(lnMock.calculateLn(8.0, accuracy)).thenReturn(2.07944154168);
        when(lnMock.calculateLn(10.0, accuracy)).thenReturn(2.30258509299);
        when(lnMock.calculateLn(16.0, accuracy)).thenReturn(2.77258872224);
        when(lnMock.calculateLn(20.0, accuracy)).thenReturn(2.99573227355);

        log = new Log(lnMock, 2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_integration.csv", numLinesToSkip = 1)
    public void testLogValues(double x){
        double expected = Math.log(x)/ Math.log(2.0);
        double actual = log.calculateLog(x, accuracy);

        assertEquals(expected, actual, 1e-4);

        if(x == 2.0){
            verify(lnMock, times(2)).calculateLn(x, accuracy);
        }else{
            verify(lnMock, times(1)).calculateLn(x, accuracy);
        }
    }
}
