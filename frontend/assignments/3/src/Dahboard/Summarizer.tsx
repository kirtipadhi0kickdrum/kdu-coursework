import  { useEffect, useState } from 'react';
import axios from 'axios';
import './Summarizer.scss'; 

interface Price {
  date: string;
  prices: number[];
}

interface StockData {
  company: string;
  symbol: string;
  data: Price[];
}

interface ProfitInfo {
  maxProfit: number;
  buyDate: string;
  sellDate: string;
  buyPrice: number;
  sellPrice: number;
}

const calculateMaxProfit = (prices: number[], dates: string[]): ProfitInfo | null => {
  const n = prices.length;

  if (n <= 1) {
    return null;
  }

  let maxProfit = 0;
  let buyDate = '';
  let sellDate = '';
  let buyPrice = 0;
  let sellPrice = 0;

  let minPrice = prices[0];

  for (let i = 1; i < n; i++) {
    const currentPrice = prices[i];

    if (currentPrice < minPrice) {
      minPrice = currentPrice;
    } else {
      const currentProfit = currentPrice - minPrice;

      if (currentProfit > maxProfit) {
        maxProfit = currentProfit;
        buyDate = dates[prices.indexOf(minPrice)] || '';
        sellDate = dates[i] || '';
        buyPrice = minPrice;
        sellPrice = currentPrice;
      }
    }
  }

  return { maxProfit, buyDate, sellDate, buyPrice, sellPrice };
};

const findMaxProfitEntry = (entries: StockData[]): StockData[] | null => {
  const maxProfitEntries: StockData[] | null = [];

  entries.forEach((entry) => {
    const profitInfo = calculateMaxProfit(entry.data[0].prices, entry.data.map(entry => entry.date));

    if (profitInfo) {
      maxProfitEntries.push({
        company: entry.company,
        symbol: entry.symbol,
        data: [{
          ...entry.data[0],
          ...profitInfo,
        }],
      });
    }
  });

  return maxProfitEntries;
};

const Summarizer = () => {
  const [data, setData] = useState<StockData[]>([]);
  const [loading, setLoading] = useState(true);
  const [result, setResult] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<StockData[]>(
          'https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json'
        );
        setData(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching data', error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const worker = new Worker('./service-worker.js')
    worker.addEventListener('messafe', (event) => {
        setResult(event.data)
    })

    worker.postMessage(data)
    return () => {
        worker.terminate()
    }
  }, [data])

  const maxProfitEntries = findMaxProfitEntry(data);

  return (
    <div className="summarizer-container">
      {loading ? (
        <div className="loading-container">
          <div className="circular-progress" />
        </div>
      ) : (
        <div className="paper-container">
          {maxProfitEntries && maxProfitEntries.length > 0 ? (
            maxProfitEntries.map((entry, index) => (
              <div key={index} className="stock-container">
                <div className="stock-info">
                  <div className="left-info">
                    <h5 className="stock-name">{entry.company}</h5>
                    <p className="profit-margin">Profit margin: ₹ {entry.data[0].maxProfit}</p>
                  </div>
                  <div className="right-info">
                    <div className="buy-sell-dates">
                      <p className="buy-sell-date">Buy: ₹ {entry.data[0].buyPrice} on {entry.data[0].buyDate}</p>
                      <p className="buy-sell-date">Sell: ₹ {entry.data[0].sellPrice} on {entry.data[0].sellDate}</p>
                    </div>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <p>No data available</p>
          )}
        </div>
      )}
    </div>
  );
};

export default Summarizer;