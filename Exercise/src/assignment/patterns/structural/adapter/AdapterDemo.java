package assignment.patterns.structural.adapter;

public class AdapterDemo {
    public static void main(String[] args) {
        CsvReport csvReport = new CsvReport();
        JsonReportAdapter adapter = new JsonReportAdapter(csvReport);

        System.out.println("CSV Report:\n" + csvReport.generateCsv());
        System.out.println("\nJSON Report:\n" + adapter.getJson());
    }
}