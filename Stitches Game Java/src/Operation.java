public class Operation extends Locale {       // Operation IS-A Locale.

    //
    // Public
    //

    // Constructor
    public Operation(int id){
        super(id);
    }

    // Getters and Setters
    public String getOperationTable() {
        return OperationTable;
    }
    public void setOperationTable(String OperationTable) {
        this.OperationTable = OperationTable;
    }


    @Override
    public String toString() {
        return "Operation..." + super.toString() + " OperationTable=" + this.OperationTable;
    }

    //
    // Private
    //
    private String OperationTable;
}