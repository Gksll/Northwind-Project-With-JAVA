package northwind.com.Core;

public enum OperationStatus {
    LISTED("LISTED SUCCESSFULLY"),
    LISTEDALL("LISTED SUCCESSFULLY"),
    ADDED("ADDED SUCCESSFULLY"),
    UPDATED("UPDATED SUCCESSFULLY"),
    DELETED("DELETED SUCCESSFULLY"),
    EXISTS(" ALREADYS EXISTS"),
    NOTLISTED("A PROBLEM OCCURRED WHILE LISTING THE"),
    NOTFOUND(" COULD NOT BE FOUND TO UPDATE."),
    ;

    private final String description;

    OperationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}