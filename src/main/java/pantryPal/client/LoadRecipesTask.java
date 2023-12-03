package pantryPal.client;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class LoadRecipesTask extends Task<String> {

    private final String name;
    private final RecipeLoaderCallback callback;
    private final Model model;

    public LoadRecipesTask(String name, RecipeLoaderCallback callback, Model model) {
        this.name = name;
        this.callback = callback;
        this.model = model;
    }

    @Override
    protected String call() throws Exception {
        // Perform time-consuming operations (e.g., network request)
        return model.performRequest(this.name);
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        String response = getValue();
        callback.onRecipesLoaded(response);
    }

    @Override
    protected void failed() {
        super.failed();
        Throwable exception = getException();
        callback.onLoadFailed(exception);
    }

    public interface RecipeLoaderCallback {
        void onRecipesLoaded(String response);

        void onLoadFailed(Throwable exception);
    }
}