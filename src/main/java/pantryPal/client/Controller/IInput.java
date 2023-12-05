package pantryPal.client.Controller;

public interface IInput {
    public void captureAudio();
    public boolean stopCapture();
    public void setPromptType(String s);
    public boolean parseInput(String s);
    public String getPromptType();
    public String getMealType();
    public String getTranscription();
}