package kdu.homework.SentiAnalysis;

import kdu.homework.APIResponseParser.APIResponseParser;

import java.util.Arrays;
import java.util.logging.Logger;

public class SentiAnalysis {

    private static final Logger logger = Logger.getLogger(APIResponseParser.class.getName());

    public static int[] detectProsAndCons(String review, String[][]
            featureSet, String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];
        for(int feat_set =0;feat_set<featureSet.length;feat_set++)
        {
            for(int feat_synonym=0;feat_synonym<featureSet[feat_set].length;feat_synonym++)
            {
                int opinion =getOpinionOnFeature(review, featureSet[feat_set][feat_synonym],posOpinionWords, negOpinionWords);
                if(opinion != 0)
                {
                    featureOpinions[feat_set] = opinion;
                    break;
                }
            }
        }

        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature,
                                           String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
        if(opinion == 0)
        {
            opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        }


        return opinion;
    }


    private static int checkForWasPhrasePattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature + " was ";
        if(review.toLowerCase().contains(pattern.toLowerCase()))
        {
            for(int posWord = 0;posWord<posOpinionWords.length;posWord++)
            {
                if(review.toLowerCase().contains(posOpinionWords[posWord].toLowerCase()))
                {
                    opinion = 1;
                    break;
                }
            }
            if(opinion == 0)
            {
                for(int negWord = 0;negWord<negOpinionWords.length;negWord++)
                {
                    if(review.toLowerCase().contains(negOpinionWords[negWord].toLowerCase()))
                    {
                        opinion = -1;
                        break;
                    }
                }
            }
        }
        return opinion;
    }


    private static int checkForOpinionFirstPattern(String review, String
            feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {


        String[] sentences = review.split("\\.");
        int opinion = 0;
        int n = sentences.length;
        for(int sentence = 0;sentence < n;sentence++)
        {
            if(sentences[sentence].toLowerCase().contains(feature.toLowerCase()))
            {
                for(int posWord = 0;posWord<posOpinionWords.length;posWord++)
                {
                    if(sentences[sentence].toLowerCase().contains(posOpinionWords[posWord].toLowerCase()))
                    {
//                        int dist = (sentences[sentence].toLowerCase().indexOf(feature.toLowerCase()) - sentences[sentence].toLowerCase().indexOf(posOpinionWords[posWord].toLowerCase()));
//                        if(dist == 1 || dist == -1)
//                        {
//                            opinion = 1;
//                            break;
//                        }
                        opinion = 1;
                        break;

                    }
                }
                if(opinion == 0)
                {
                    for(int negWord = 0;negWord<negOpinionWords.length;negWord++)
                    {
                        if(sentences[sentence].toLowerCase().contains(negOpinionWords[negWord].toLowerCase()))
                        {
//                            int dist = (sentences[sentence].toLowerCase().indexOf(feature.toLowerCase()) - sentences[sentence].toLowerCase().indexOf(posOpinionWords[negWord].toLowerCase()));
//                            if(dist == 1 || dist == -1)
//                            {
//                                opinion = -1;
//                                break;
//                            }
                            opinion = -1;
                            break;
                        }
                    }
                }
                if(opinion != 0)
                {
                    break;
                }
            }
        }
        return opinion;
    }
    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";


        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress",
                        "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly",
                "great", "excellent", "amazing", "awesome",
                "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible",
                "awful", "unprofessional", "poor" };
        int[] featureOpinions = detectProsAndCons(review, featureSet,
                posOpinionWords, negOpinionWords);
        logger.info("Opinions on Features: " +
                Arrays.toString(featureOpinions));
    }
}
