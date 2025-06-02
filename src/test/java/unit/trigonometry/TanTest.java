package unit.trigonometry;

import org.example.function.CsvExporter;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Sin;
import org.example.trigonometry.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TanTest {
    double accuracy = 1e-10;
    double eps = 1e-8;
    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);
    private final CsvExporter csvExporter = new CsvExporter(tan::calculateTan);

    @Test
    public void testAndSaveTanResults(){
        for (double x = 0; x <= 2*Math.PI; x += 0.1) {
            double expected = Math.tan(x);
            double actual = tan.calculateTan(x, accuracy);

            assertEquals(expected, actual, eps);

        }
        csvExporter.testAndExportCsv( 0.0, 2*Math.PI, 0.1, "tan_results.csv", eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI/6, Math.PI/4, Math.PI/3, Math.PI, 2*Math.PI, 0})
    public void testPiValues(double x){
        assertEquals(Math.tan(x), tan.calculateTan(x, accuracy), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6, -Math.PI/4, -Math.PI/3, -Math.PI, -2*Math.PI})
    public void testNegativeValues(double x){
        assertEquals(Math.tan(x), tan.calculateTan(x, accuracy), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI/2, 3*Math.PI/2, -Math.PI/2, -3*Math.PI/2})
    public void testInvalidValues(double x){
        assertEquals(Double.NaN, tan.calculateTan(x, accuracy), eps);
    }

}