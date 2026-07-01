abstract class AIModelTrainer {
    
    public final void trainModel() {
        loadData();
        preprocessData();
        buildModel();
        train();
        evaluate();
        saveModel();
    }

    protected abstract void loadData();
    protected abstract void preprocessData();
    protected abstract void buildModel();
    protected abstract void train();
    protected abstract void evaluate();

   
    protected void saveModel() {
        System.out.println("Saving trained model to disk...");
    }
}


class NLPModelTrainer extends AIModelTrainer {
    @Override
    protected void loadData() {
        System.out.println("Loading text data from corpus...");
    }

    @Override
    protected void preprocessData() {
        System.out.println("Tokenizing and removing stop words...");
    }

    @Override
    protected void buildModel() {
        System.out.println("Building LSTM-based NLP model...");
    }

    @Override
    protected void train() {
        System.out.println("Training NLP model on processed text data...");
    }

    @Override
    protected void evaluate() {
        System.out.println("Evaluating NLP model accuracy on test dataset...");
    }
}


class ImageModelTrainer extends AIModelTrainer {
    @Override
    protected void loadData() {
        System.out.println("Loading image dataset...");
    }

    @Override
    protected void preprocessData() {
        System.out.println("Resizing and normalizing images...");
    }

    @Override
    protected void buildModel() {
        System.out.println("Building CNN model for image classification...");
    }

    @Override
    protected void train() {
        System.out.println("Training CNN model on image dataset...");
    }

    @Override
    protected void evaluate() {
        System.out.println("Evaluating CNN model performance...");
    }
}


public class Template {
    public static void main(String[] args) {
        // System.out.println("Training NLP Model:");
        // AIModelTrainer nlpModel = new NLPModelTrainer();
        // nlpModel.trainModel();

        System.out.println("\nTraining Image Recognition Model:");
        AIModelTrainer imageModel = new ImageModelTrainer();
        imageModel.trainModel();
    }
}