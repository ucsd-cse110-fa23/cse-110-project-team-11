package pantryPal.client.Controller;

public class MockInput implements IInput {
    String s;
    public void captureAudio(){

    }
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
        return "MealType";
    }
    public String getMealType(){
        return "Dinner";
    }
    public String getTranscription(){
        return "MockInput";
    }
}
