package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;



public class CryptoMarket {
    private static final Logger logger = Logger.getLogger(CryptoMarket.class.getName());
    private List<Coin> coins;
    private List<Trader> traders;


    private void processCsvLine(String line) {
        String[] data = line.split(",");
        if (data.length >= 6) {
            String rankStr = data[1].trim();
            if (!rankStr.isEmpty()) {
                try {
                    int rank = Integer.parseInt(rankStr);
                    String name = data[2].trim();
                    String symbol = data[3].trim();
                    double price = Double.parseDouble(data[4].trim());
                    long circulatingSupply = Long.parseLong(data[5].trim());

                    Coin coin = new Coin(rank, name, symbol, price, circulatingSupply);
                    coins.add(coin);
                } catch (NumberFormatException e) {
                    logger.info("Invalid rank value in CSV: " + rankStr);
                }
            } else {
                logger.info("Rank value is empty in CSV.");
            }
        } else {
            logger.info("Invalid data in CSV: ");
        }
    }
    public void loadCoinsFromCSV(String filePath) {

        coins = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                processCsvLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void loadTradersFromCSV(String filePath) {
        traders = new ArrayList<>();


        try(BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line = br.readLine();

            while (line != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String firstName = data[1].trim();
                    String lastName = data[2].trim();
                    String phone = data[3].trim();
                    String walletAddress = data[4].trim();

                    Trader trader = new Trader(firstName, lastName, phone, walletAddress);
                    traders.add(trader);
                }
                else {
                    logger.info("invalid data in CSV");
                }
            }
        }catch(IOException e)
        {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void processBuyTransaction(JsonNode transactionNode) {
        String coinCode = transactionNode.get("data").get("coin").asText();
        int quantity = transactionNode.get("data").get("quantity").asInt();
        String walletAddress = transactionNode.get("data").get("wallet_address").asText();

        Coin coin = getCoinByCode(coinCode);
        Trader trader = getTraderByWalletAddress(walletAddress);

        if (coin != null && trader != null) {
            if (coin.getVolume() >= quantity) {
                coin.decreaseVolume(quantity);
                trader.addToPortfolio(coinCode, quantity);
                double totalCost = quantity * coin.getPrice();
                trader.updateBalance(-totalCost);
            } else {
                logger.info("Insufficient volume for the BUY transaction. Transitioning to a pending state...");
            }
        }


    }

    public synchronized void processSellTransaction(JsonNode transactionNode) {
        String coinCode = transactionNode.get("data").get("coin").asText();
        int quantity = transactionNode.get("data").get("quantity").asInt();
        String walletAddress = transactionNode.get("data").get("wallet_address").asText();

        Coin coin = getCoinByCode(coinCode);
        Trader trader = getTraderByWalletAddress(walletAddress);

        if (coin != null && trader != null) {

            if (trader.getPortfolio().containsKey(coinCode) && trader.getPortfolio().get(coinCode) >= quantity) {
                trader.removeFromPortfolio(coinCode, quantity);
                coin.increaseVolume(quantity);
                double totalRevenue = quantity * coin.getPrice();
                trader.updateBalance(totalRevenue);
            } else {
                logger.info("Trader does not own enough of the specified coin for the SELL transaction.");
            }
        }

    }

    public synchronized void processUpdatePriceTransaction(JsonNode transactionNode) {
        String coinCode = transactionNode.get("data").get("coin").asText();
        double newPrice = transactionNode.get("data").get("price").asDouble();
        Coin coin = getCoinByCode(coinCode);
        if (coin != null) {
            coin.updatePrice(newPrice);
        }
    }

    public synchronized void processAddVolumeTransaction(JsonNode transactionNode) {
        JsonNode dataNode = transactionNode.get("data");
        if(dataNode != null)
        {
            JsonNode coinNode = dataNode.get("coin");
            JsonNode additionalVolumeNode = dataNode.get("additional_volume");
            if (coinNode != null && additionalVolumeNode != null) {
                String coinCode = coinNode.asText();
                int additionalVolume = additionalVolumeNode.asInt();

                Coin coin = getCoinByCode(coinCode);

                if (coin != null) {
                    coin.addVolume(additionalVolume);
                }
            }
        }



    }

    public void displayTopCoinsByPrice(int n) {
        List<Coin> topCoins = getTopCoinsByPrice(n);
        for (Coin coin : topCoins) {
            logger.info("Coin is " + coin);
        }
    }

    public void displayTopTradersByProfitLoss(int n) {
        List<Trader> topTraders = getTopTradersByProfitLoss(n);
        for (Trader trader : topTraders) {
            logger.info(String.format("trader is %s", trader));
        }
    }


    private Coin getCoinByCode(String code) {
        return coins.stream()
                .filter(coin -> coin.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }


    private Trader getTraderByWalletAddress(String walletAddress) {
        return traders.stream()
                .filter(trader -> trader.getWalletAddress().equalsIgnoreCase(walletAddress))
                .findFirst()
                .orElse(null);
    }


    private List<Coin> getTopCoinsByPrice(int n) {
        return coins.stream()
                .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                .limit(n)
                .toList();

    }

    private List<Trader> getTopTradersByProfitLoss(int n) {
        return traders.stream()
                .sorted()
                .limit(n)
                .toList();
    }
}