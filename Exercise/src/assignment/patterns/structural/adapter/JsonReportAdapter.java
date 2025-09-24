package assignment.patterns.structural.adapter;

public class JsonReportAdapter {
    private CsvReport csvReport;

    public JsonReportAdapter(CsvReport csvReport) {
        this.csvReport = csvReport;
    }

    public String getJson() {
        String csv = csvReport.generateCsv();
        String[] lines = csv.split("\n");
        String[] headers = lines[0].split(",");

        StringBuilder json = new StringBuilder("[");
        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            json.append("{");
            for (int j = 0; j < headers.length; j++) {
                json.append("\"").append(headers[j]).append("\":\"").append(values[j]).append("\"");
                if (j < headers.length - 1) json.append(",");
            }
            json.append("}");
            if (i < lines.length - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }
}