package unit.function;

import org.example.function.CsvExporter;
import org.example.function.LogarithmFunction;
import org.example.function.MainFunction;
import org.example.function.TrigonometricFunction;
import org.example.logarithm.Ln;
import org.example.logarithm.Log;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Csc;
import org.example.trigonometry.Sin;
import org.example.trigonometry.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainFunctionTest {
    private static final double accuracy = 1e-10;

    private final Ln ln = new Ln();
    private final Log log2 = new Log(ln, 2);
    private final Log log3 = new Log(ln, 3);
    private final Log log5 = new Log(ln, 5);
    private final Log log10 = new Log(ln, 10);
    private final LogarithmFunction logarithmFunc = new LogarithmFunction(log2, log3, log5, log10);

    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);
    private final Csc csc = new Csc(sin);
    private final TrigonometricFunction trigonometricFunc = new TrigonometricFunction(tan, csc);

    private final MainFunction mainFunc = new MainFunction(logarithmFunc, trigonometricFunc);
    private final CsvExporter csvExporter = new CsvExporter(mainFunc::calculateFunction);

    @Test
    public void testAndSaveTrigFunc(){
        csvExporter.testAndExportCsv(-10.0, -0.1, 0.1, "main_result.csv", accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_func_input.csv", numLinesToSkip = 1)
    public void testMainFuncPositive(double x){
        double expected = logarithmFunc.calculateLogFunc(x, accuracy);
        double actual = mainFunc.calculateFunction(x, accuracy);
        assertEquals(expected, actual, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/trig_func_input.csv", numLinesToSkip = 1)
    public void testMainFunctionNegative(double x){
        double expected = trigonometricFunc.calculateTrigFunc(x, accuracy);
        double actual = mainFunc.calculateFunction(x, accuracy);
        assertEquals(expected, actual, accuracy);
    }
}