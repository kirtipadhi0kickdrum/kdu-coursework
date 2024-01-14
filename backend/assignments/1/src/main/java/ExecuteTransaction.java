
import com.fasterxml.jackson.databind.JsonNode;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import static java.lang.String.*;

public class ExecuteTransaction implements Runnable{
    private static final Logger logger = Logger.getLogger(ExecuteTransaction.class.getName());
    private final JsonNode transactionNode;
    private final CryptoMarket cryptoMarket;
    private final CountDownLatch latch;

    public ExecuteTransaction(JsonNode transactionNode, CryptoMarket cryptoMarket, CountDownLatch latch)
    {
        this.cryptoMarket =cryptoMarket;
        this.latch = latch;
        this.transactionNode = transactionNode;
    }


    public void run()
    {
        try{
            processTransaction();
        }finally {
            latch.countDown();
        }
    }

    private void processTransaction(){
        String type = transactionNode.get("type").asText();
        switch (type) {
            case "BUY":
                cryptoMarket.processBuyTransaction(transactionNode);
                break;
            case "SELL":
                cryptoMarket.processSellTransaction(transactionNode);
                break;
            case "UPDATE_PRICE":
                cryptoMarket.processUpdatePriceTransaction(transactionNode);
                break;
            case "ADD_VOLUME":
                cryptoMarket.processAddVolumeTransaction(transactionNode);
                break;
            default:
                logger.info("Invalid transaction type");
        }
    }
}
