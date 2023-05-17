package qa;

public class TestRailCase {

    // ATTRIBUTES    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private String caseID;

    // CONSTRUCTORS  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    public TestRailCase(String caseID) {
        this.caseID = caseID;
    }


    // GETTERS & SETTERS --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }


}
