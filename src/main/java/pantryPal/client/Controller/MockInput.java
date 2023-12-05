package pantryPal.client.Controller;

public class MockInput implements IInput {
    String s;
    public void captureAudio(){}

    public boolean stopCapture(){
        return true;
    }
    public void setPromptType(String s){
        this.s = s;
    }
    public boolean parseInput(String s){
        return true;
    }
    public String getPromptType(){
        return s;
    }
    // private String responses[] = {"Dinner", "valid"};
    // private int index = 0;
    // public String callAPI(String prompt) {

    //     if (prompt.equals("stop")) {
    //         return responses[(index++)%2];
    //     }
    //     return null;
    // }
    public String getMealType(){
        return "Dinner";
    }
    public String getTranscription(){
        return "MockInput";
    }
}
