

import java.util.logging.Logger;

public class Coin {
    private static final Logger logger = Logger.getLogger(Coin.class.getName());
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long circulatingSupply;
    private long volume;

    public Coin(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
        this.volume = circulatingSupply;
    }


    public double getPrice() {
        return price;
    }


    public long getVolume() {
        return volume;
    }


    public void decreaseVolume(int quantity) {
        volume -= quantity;
        if (volume < 0) {
            volume = 0;
        }
    }
    public void addVolume(int additionalVolume) {
        this.volume += additionalVolume;
    }



    @Override
    public String toString() {
        return String.format("Coin[%s - %s | Price: $%.2f | Volume: %d]", symbol, name, price, volume);
    }

    public void updatePrice(double newPrice) {
        if (newPrice >= 0) {
            this.price = newPrice;
        } else {
            logger.info("Invalid price. Price should be non-negative.");
        }
    }

    public String getCode() {
        return symbol;
    }

    public void increaseVolume(int additionalVolume) {
        if (additionalVolume >= 0) {
            this.volume += additionalVolume;
        } else {
           logger.info("Invalid additional volume. Volume should be non-negative.");
        }
    }
}
