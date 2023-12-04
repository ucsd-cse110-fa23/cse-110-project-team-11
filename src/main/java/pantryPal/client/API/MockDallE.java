package pantryPal.client.API;

public class MockDallE implements IAPI {
    public String callAPI(String prompt) {
        String url = "https://ohsnapmacros.com/wp-content/uploads/2023/06/protein-banana-bread-114.jpg";
        return url;
    }
} 
