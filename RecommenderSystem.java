import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class RecommenderSystem {
    public static void main(String[] args) {
        try {
            // Load data from a CSV file (userId, itemId, preference)
            DataModel model = new FileDataModel(new File("data.csv"));

            // Calculate similarity using Pearson correlation
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Define user neighborhood
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Create the recommender system
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Get recommendations for a user
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            for (RecommendedItem recommendation : recommendations) {
                System.out.println("💡 Recommended Item: " + recommendation.getItemID() + " (Value: " + recommendation.getValue() + ")");
            }
        } catch (Exception e) {
            System.err.println("Error in recommendation system: " + e.getMessage());
          }
    }
}