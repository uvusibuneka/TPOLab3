package unit.logarithm;

import org.example.function.CsvExporter;
import org.example.logarithm.Ln;
import org.example.logarithm.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogTest {
    private static Ln ln;
    private static Log log2;
    private static Log log10;
    private static Log log3;
    private static Log log5;
    double eps = 1e-8;
    double accuracy = 1e-10;
    private final CsvExporter csvLog2Exporter = new CsvExporter(log2::calculateLog);
    private final CsvExporter csvLog3Exporter = new CsvExporter(log3::calculateLog);
    private final CsvExporter csvLog5Exporter = new CsvExporter(log5::calculateLog);
    private final CsvExporter csvLog10Exporter = new CsvExporter(log10::calculateLog);

    @BeforeAll
    public static void initializeLogarithms(){
        ln = new Ln();
        log2 = new Log(ln, 2);
        log3 = new Log(ln,3);
        log5 = new Log(ln, 5);
        log10 = new Log(ln,10);
    }

    @Test
    public void testAndSaveLogResults(){
        for(double x = 0.1; x <= 10.0; x += 0.1){
            double expectedLog2 = Math.log(x)/Math.log(2);
            double actualLog2 = log2.calculateLog(x, accuracy);
            assertEquals(expectedLog2, actualLog2, eps);

            double expectedLog3 = Math.log(x)/Math.log(3);
            double actualLog3 = log3.calculateLog(x, accuracy);
            assertEquals(expectedLog3, actualLog3, eps);

            double expectedLog5 = Math.log(x)/Math.log(5);
            double actualLog5 = log5.calculateLog(x, accuracy);
            assertEquals(expectedLog5, actualLog5, eps);

            double expectedLog10 = Math.log10(x);
            double actualLog10 = log10.calculateLog(x, accuracy);
            assertEquals(expectedLog10, actualLog10, eps);
        }

        csvLog2Exporter.testAndExportCsv(0.1, 10.0, 0.1, "log2_result.csv", eps);
        csvLog3Exporter.testAndExportCsv(0.1, 10.0, 0.1, "log3_result.csv", eps);
        csvLog5Exporter.testAndExportCsv(0.1, 10.0, 0.1, "log5_result.csv", eps);
        csvLog10Exporter.testAndExportCsv(0.1, 10.0, 0.1, "log10_result.csv", eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_test.csv", numLinesToSkip = 1)
    public void testLog2(double x){
        if(x < 1) return;
        double actual = log2.calculateLog(x, accuracy);
        assertEquals(Math.log(x)/Math.log(2), actual, eps);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_test.csv", numLinesToSkip = 1)
    public void testLog3(double x){
        if(x < 1) return;
        double actual = log3.calculateLog(x, accuracy);
        assertEquals(Math.log(x)/Math.log(3), actual, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_test.csv", numLinesToSkip = 1)
    public void testLog5(double x){
        if(x < 1) return;
        double actual = log5.calculateLog(x, accuracy);
        assertEquals(Math.log(x)/Math.log(5), actual, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_test.csv", numLinesToSkip = 1)
    public void testLog10(double x){
        if(x < 1) return;
        double actual = log10.calculateLog(x, accuracy);
        assertEquals(Math.log10(x), actual, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_test.csv", numLinesToSkip = 1)
    public void testInvalidValues(double x){
        if(x > 0) return;
        assertEquals(Math.log(x)/Math.log(2), log2.calculateLog(x, accuracy));
        assertEquals(Math.log(x)/Math.log(3), log3.calculateLog(x, accuracy));
        assertEquals(Math.log10(x), log10.calculateLog(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/log_test.csv", numLinesToSkip = 1)
    public void testBases(int base){
        if(base > 1) return;
        Log invalidLog = new Log(ln,base);
        assertTrue(Double.isNaN(invalidLog.calculateLog(10, accuracy)));
    }
}