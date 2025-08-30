package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTransformer {
    @DataTableType //исп реализацию мапы с dataTable
    public Map<String,String> map (DataTable dataTable){
        List<List<String>> rows = dataTable.cells();
        Map<String,String> transformedMap = new HashMap<>();
        for(List<String> row : rows){
            transformedMap.put(row.get(0), convertValue(row.get(1)));
        }
        return transformedMap;
    }

    private String convertValue(String value){
        return switch (value){
            case "empty_value" -> "";
            default -> value;
        };
    }
}
