


import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Trader{
    private static final Logger logger = Logger.getLogger(Trader.class.getName());
    private String firstName;
    private String lastName;
    private String walletAddress;
    private static Map<String, Integer> portfolio;
    private static double balance;
    private static List<Coin> coins;
    public Trader(String firstName, String lastName,String phone, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.walletAddress = walletAddress;

    }

    public String getWalletAddress() {
        return walletAddress;
    }


    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }


    public void addToPortfolio(String coinCode, int quantity) {
        portfolio.put(coinCode, portfolio.getOrDefault(coinCode, 0) + quantity);
    }

    public static void updateBalance(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return String.format("Trader[%s %s | Wallet: %s | Balance: $%.2f]", firstName, lastName, walletAddress, balance);
    }

    public void removeFromPortfolio(String coinCode, int quantity) {
        if (portfolio.containsKey(coinCode)) {
            int currentQuantity = portfolio.get(coinCode);
            if (currentQuantity >= quantity) {
                portfolio.put(coinCode, currentQuantity - quantity);
            } else {
                logger.info("Invalid quantity to remove from the portfolio. Insufficient quantity.");
            }
        } else {
            logger.info("Coin not found in the portfolio.");
        }
    }

    public static double getProfitLoss() {
        double totalInvestment = calculateTotalInvestment();
        double currentPortfolioValue = calculateCurrentPortfolioValue();

        return currentPortfolioValue - totalInvestment;
    }

    private static double calculateTotalInvestment() {
        return portfolio.entrySet().stream()
                .mapToDouble(entry -> {
                    Coin coin = getCoinByCode( entry.getKey());
                    return entry.getValue() * coin.getPrice();
                })
                .sum();
    }

    private static Coin getCoinByCode(String code) {
        return coins.stream()
                .filter(coin -> coin.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    private static double calculateCurrentPortfolioValue() {
        return portfolio.entrySet().stream()
                .mapToDouble(entry -> {
                    Coin coin = getCoinByCode( entry.getKey());
                    return entry.getValue() * coin.getPrice();
                })
                .sum() + balance;
    }



}
