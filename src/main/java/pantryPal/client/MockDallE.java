package pantryPal.client;

public class MockDallE implements IAPI {
    public String callAPI(String prompt) {
        String url = "https://demo.sirv.com/looks.jpg?h=150&w=150";
        return url;
    }
} 
