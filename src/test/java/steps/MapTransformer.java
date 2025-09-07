package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static consts.UrlParamsValues.VALID_KEY;
import static consts.UrlParamsValues.VALID_TOKEN;

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
            case "current_user_key" -> VALID_KEY;
            case "current_user_token" -> VALID_TOKEN;
            default -> value;
        };
    }
}
