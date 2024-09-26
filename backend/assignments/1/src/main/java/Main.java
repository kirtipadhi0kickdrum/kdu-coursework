


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] a)
    {
        CryptoMarket cryptoMarket = new CryptoMarket();
        cryptoMarket.loadCoinsFromCSV("src/main/resources/coins.csv");
        cryptoMarket.loadTradersFromCSV("src/main/resources/traders.csv");

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonTransactions = objectMapper.readTree(new File("src/main/resources/medium_transaction.json"));
            executeTransactions(jsonTransactions, cryptoMarket);

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void executeTransactions(JsonNode jsonTransactions, CryptoMarket cryptoMarket)
    {
        int numThreads = jsonTransactions.size();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        for(JsonNode transactionNode : jsonTransactions)
        {
            ExecuteTransaction executeTransaction = new ExecuteTransaction(transactionNode, cryptoMarket, latch);
            executor.execute(executeTransaction);
        }

        try{
            latch.await();
            executor.shutdown();
        }catch(InterruptedException e)
        {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        cryptoMarket.displayTopCoinsByPrice(5);
        cryptoMarket.displayTopTradersByProfitLoss(5);
    }
}
