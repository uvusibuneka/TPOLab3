package unit.trigonometry;

import org.example.function.CsvExporter;
import org.example.trigonometry.Csc;
import org.example.trigonometry.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CscTest {
    double eps = 1e-8;
    double accuracy = 1e-10;
    private final Sin sin = new Sin();
    private final Csc csc = new Csc(sin);
    private final CsvExporter csvExporter = new CsvExporter(csc::calculateCosec);

    @Test
    public void testAndSaveCosecResults(){
        for (double x = 0.1; x <= 2*Math.PI; x += 0.1) {
            double expected = 1/Math.sin(x);
            double actual = csc.calculateCosec(x, accuracy);

            assertEquals(expected, actual, eps);

        }
        csvExporter.testAndExportCsv(0.1,2*Math.PI,0.1,"cosec_results.csv", eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2})
    public void testPiValues(double x){
        assertEquals(1/Math.sin(x), csc.calculateCosec(x , accuracy), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6, -Math.PI/4, -Math.PI/3, -Math.PI/2})
    public void testNegativePi(double x){
        assertEquals(1/Math.sin(x), csc.calculateCosec(x, accuracy), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, -Math.PI, -2*Math.PI, 2*Math.PI})
    public void testInvalidValues(double x){
        assertTrue(Double.isNaN(csc.calculateCosec(x, accuracy)));
    }

}