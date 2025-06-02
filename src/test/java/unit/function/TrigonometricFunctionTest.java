package unit.function;

import org.example.function.CsvExporter;
import org.example.function.TrigonometricFunction;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Csc;
import org.example.trigonometry.Sin;
import org.example.trigonometry.Tan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrigonometricFunctionTest {
    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);
    private final Csc csc = new Csc(sin);
    private final TrigonometricFunction trigonometricFunc = new TrigonometricFunction(tan, csc);
    private final double eps = 1e-3;
    double accuracy = 1e-8;
    private final CsvExporter csvExporter = new CsvExporter(trigonometricFunc::calculateTrigFunc);

    @Test
    public void testAndSaveTrigFunc(){
        csvExporter.testAndExportCsv(-10.0, -0.1, 0.1, "trig_func_result.csv", eps);
    }


    @Test
    public void testValues(){
        assertEquals(4.830779, trigonometricFunc.calculateTrigFunc(-0.2, accuracy), eps);
        assertEquals(3.07453, trigonometricFunc.calculateTrigFunc(-0.3, accuracy), eps);
        assertEquals(2.14514, trigonometricFunc.calculateTrigFunc(-0.4, accuracy), eps);
        assertEquals(1.53953, trigonometricFunc.calculateTrigFunc(-0.5, accuracy), eps);
        assertEquals(-0.36901, trigonometricFunc.calculateTrigFunc(-1.0, accuracy), eps);
        assertEquals(7.22871, trigonometricFunc.calculateTrigFunc(-3.0, accuracy), eps);
        assertEquals(2.33768, trigonometricFunc.calculateTrigFunc(-5.0, accuracy), eps);
    }
}