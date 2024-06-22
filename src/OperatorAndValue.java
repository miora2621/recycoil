package models;

public class OperatorAndValue {
    private String column;
    private String operator;
    private String value;

    public String getColumn() {
        return this.column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public String getOperator() {
        return this.operator;
    }
    public void setOperator(String operator) throws Exception {
        if(operator.equals("<") || operator.equals(">") || operator.equals("=") || operator.equals("!=") || operator.equals("<=") || operator.equals(">=") || operator.equals(" LIKE ")) {
            this.operator = operator;
        } else {
            throw new Exception("Invalid operator => " + operator);
        }
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public OperatorAndValue(String column, String operator, String value) throws Exception {
        this.setColumn(column);
        this.setOperator(operator);
        this.setValue(value);
    }
}
