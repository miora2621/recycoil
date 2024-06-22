package models;

import java.util.HashMap;
import java.util.List;

public class Utils {
        
    public static boolean isNumeric(String string){
        try{
            Double.parseDouble(string);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static String refactQuery(List<OperatorAndValue> datas) {
        StringBuilder query = new StringBuilder();
        for (OperatorAndValue value : datas) {
            if (value != null && value.getValue() != null && !value.getOperator().equals("") && !value.getValue().equals("")) {
                query.append(isNumeric(value.getValue()) ? " AND " + value.getColumn() + value.getOperator() + value.getValue() : " AND " + value.getColumn() + value.getOperator() + "'" + value.getValue() + "'");
            }
        }
        return query.toString();
    }
}
