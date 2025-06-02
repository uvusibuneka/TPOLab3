package integration.function;

import org.example.function.LogarithmFunction;
import org.example.function.MainFunction;
import org.example.function.TrigonometricFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MainFunctionTest {
    private double accuracy = 1e-10;
    private LogarithmFunction logarithmFuncMock;
    private TrigonometricFunction trigonometricFuncMock;
    private MainFunction mainFunc;

    @BeforeEach
    public void initializeStubs(){
        logarithmFuncMock = mock(LogarithmFunction.class);
        trigonometricFuncMock = mock(TrigonometricFunction.class);

        when(logarithmFuncMock.calculateLogFunc(2.0, accuracy)).thenReturn(-0.804695749846);
        when(logarithmFuncMock.calculateLogFunc(5.0, accuracy)).thenReturn(-2.77080197564);
        when(logarithmFuncMock.calculateLogFunc(10.0, accuracy)).thenReturn(-4.25810391997);

        when(trigonometricFuncMock.calculateTrigFunc(-1.0, accuracy)).thenReturn(-0.369012618877);
        when(trigonometricFuncMock.calculateTrigFunc(-2.0, accuracy)).thenReturn(3.28479003356);
        when(trigonometricFuncMock.calculateTrigFunc(-5.0, accuracy)).thenReturn(2.33767979348);

        mainFunc = new MainFunction(logarithmFuncMock, trigonometricFuncMock);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/main_func_input.csv", numLinesToSkip = 1)
    public void testMainFunctionFromCsv(double x, double expected){
        double actual = mainFunc.calculateFunction(x, accuracy);
        assertEquals(expected, actual, 1e-4);

        if (x > 0) {
            verify(logarithmFuncMock, times(1)).calculateLogFunc(x, accuracy);
            verify(trigonometricFuncMock, never()).calculateTrigFunc(x, accuracy);
        } else {
            verify(trigonometricFuncMock, times(1)).calculateTrigFunc(x, accuracy);
            verify(logarithmFuncMock, never()).calculateLogFunc(x, accuracy);
        }
    }
}
