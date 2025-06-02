package integration.function;

import org.example.function.LogarithmFunction;
import org.example.logarithm.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogarithmFunctionTest {
    private double accuracy = 1e-10;
    private Log log2Mock;
    private Log log3Mock;
    private Log log5Mock;
    private Log log10Mock;
    private LogarithmFunction logFunc;

    @BeforeEach
    public void initializeStub(){
        log2Mock = mock(Log.class);
        log3Mock = mock(Log.class);
        log5Mock = mock(Log.class);
        log10Mock = mock(Log.class);

        when(log2Mock.calculateLog(1.5, accuracy)).thenReturn(0.584962500721);
        when(log2Mock.calculateLog(2.0, accuracy)).thenReturn(1.0);
        when(log2Mock.calculateLog(3.0, accuracy)).thenReturn(1.58496250072);
        when(log2Mock.calculateLog(5.0, accuracy)).thenReturn(2.32192809489);
        when(log2Mock.calculateLog(10.0, accuracy)).thenReturn(3.32192809489);

        when(log3Mock.calculateLog(1.5, accuracy)).thenReturn(0.369070246429);
        when(log3Mock.calculateLog(2.0, accuracy)).thenReturn(0.630929753571);
        when(log3Mock.calculateLog(3.0, accuracy)).thenReturn(1.0);
        when(log3Mock.calculateLog(5.0, accuracy)).thenReturn(1.46497352072);
        when(log3Mock.calculateLog(10.0, accuracy)).thenReturn(2.09590327429);

        when(log5Mock.calculateLog(1.5, accuracy)).thenReturn(0.251929636413);
        when(log5Mock.calculateLog(2.0, accuracy)).thenReturn(0.430676558073);
        when(log5Mock.calculateLog(3.0, accuracy)).thenReturn(0.682606194486);
        when(log5Mock.calculateLog(5.0, accuracy)).thenReturn(1.0);
        when(log5Mock.calculateLog(10.0, accuracy)).thenReturn(1.43067655807);

        when(log10Mock.calculateLog(1.5, accuracy)).thenReturn(0.176091259056);
        when(log10Mock.calculateLog(2.0, accuracy)).thenReturn(0.301029995664);
        when(log10Mock.calculateLog(3.0, accuracy)).thenReturn(0.47712125472);
        when(log10Mock.calculateLog(5.0, accuracy)).thenReturn(0.698970004336);
        when(log10Mock.calculateLog(10.0, accuracy)).thenReturn(1.0);

        logFunc = new LogarithmFunction(log2Mock, log3Mock, log5Mock, log10Mock);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_func_input.csv", numLinesToSkip = 1)
    public void testLogarithmFunction(double x){
        double expected = (((log10Mock.calculateLog(x, accuracy)*log10Mock.calculateLog(x, accuracy) + log5Mock.calculateLog(x, accuracy)) / log3Mock.calculateLog(x, accuracy)
                - log2Mock.calculateLog(x, accuracy)) - log3Mock.calculateLog(x, accuracy));

        double actual = logFunc.calculateLogFunc(x, accuracy);
        assertEquals(expected, actual, accuracy);
    }
}
