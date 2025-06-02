package unit.function;

import org.example.function.CsvExporter;
import org.example.function.LogarithmFunction;
import org.example.logarithm.Ln;
import org.example.logarithm.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogarithmFunctionTest {
    private final Ln ln = new Ln();
    private final Log log2 = new Log(ln, 2);
    private final Log log3 = new Log(ln, 3);
    private final Log log5 = new Log(ln, 5);
    private final Log log10 = new Log(ln, 10);
    private final LogarithmFunction logFunction = new LogarithmFunction(log2, log3, log5, log10);
    double accuracy = 1e-10;
    private final double eps = 1e-5;
    private final CsvExporter csvExporter = new CsvExporter(logFunction::calculateLogFunc);

    @Test
    public void testAndSaveLogFunctionResults(){
        csvExporter.testAndExportCsv(0.1, 10.0, 0.1, "log_func_result.csv", eps);
    }

    @Test
    public void testValues(){
        assertEquals(-0.18741,logFunction.calculateLogFunc(1.5, accuracy), eps);
        assertEquals(2.64871,logFunction.calculateLogFunc(0.4, accuracy), eps);
        assertEquals(2.16991, logFunction.calculateLogFunc(0.5, accuracy), eps);
        assertEquals(1.7787, logFunction.calculateLogFunc(0.6, accuracy), eps);
        assertEquals(-0.8047, logFunction.calculateLogFunc(2, accuracy), eps);
        assertEquals(-1.2835, logFunction.calculateLogFunc(2.5, accuracy), eps);
        assertEquals(-1.67471, logFunction.calculateLogFunc(3.0, accuracy), eps);
        assertEquals(-2.7708, logFunction.calculateLogFunc(5.0, accuracy), eps);
    }

}

