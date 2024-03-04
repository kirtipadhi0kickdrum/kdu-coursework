import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useStockData } from '../Dahboard/StockDataContext';
import { styled } from '@mui/system';
import './StockPage.scss';
import SimulatedStockPriceUpdater from './StockSimulator';
import { ArrowDownward, ArrowUpward } from '@mui/icons-material';
import io from 'socket.io-client';


const socket = io('http://localhost:3001');

interface TransactionProps {
    timestamp: string;
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    status: string;
    date?: string;
  }

const StockPageContainer = styled('div')({
  display: 'flex',
  border: '1px solid lightgray', 
});

const StockContainer = styled('div')(({ theme }) => ({
  width: '75%',
  padding: theme.spacing(2),
  borderRight: `1px solid ${theme.palette.divider}`,
  display: 'flex',
  flexDirection: 'column',
}));

const LogsContainer = styled('div')(({ theme }) => ({
  width: '25%',
  padding: theme.spacing(2),
  display: 'flex',
  flexDirection: 'column',
  border: `1px solid ${theme.palette.divider}`,
  overflowY: 'auto', 
  maxHeight: '600px', 
}));

const TransactionContainer = styled('div')(({ theme, transactionType }: { theme: any; transactionType: string }) => ({
  border: `1px solid ${theme.palette.divider}`,
  borderRadius: theme.shape.borderRadius,
  marginBottom: theme.spacing(2),
  padding: theme.spacing(2),
  color: transactionType === 'Bought' ? 'green' : 'red', 
}));

const StockPage: React.FC = () => {
  const [priceChanges, setPriceChanges] = useState<{ price: number; positive: boolean }[]>([]);
  const [currentPrice, setCurrentPrice] = useState<number | null>(null);
  const [quantity, setQuantity] = useState<number | null>(null);
  const [walletBalance, setWalletBalance] = useState<number>(2000);
  const [transactionHistory, setTransactionHistory] = useState<JSX.Element[]>([]);
  const { stockData } = useStockData();
  const { stockName } = useParams<{ stockName?: string }>();

  const handlePriceUpdate = (newPrice: number) => {
    const isPositive = newPrice > (priceChanges[priceChanges.length - 1]?.price || 0);
    if (newPrice >= 500) {
      newPrice = 490;
    }
    const limitedPriceChanges = [...priceChanges, { price: Math.abs(newPrice), positive: isPositive }];
    const slicedPriceChanges = limitedPriceChanges.slice(-45);
    setPriceChanges(slicedPriceChanges);
    setCurrentPrice(newPrice);
  };

  useEffect(() => {
    socket.on('priceUpdate', (newPrice) => {
      handlePriceUpdate(newPrice);
    });

    return () => {
      socket.disconnect();
    };
  }, []);

  useEffect(() => {
    if (priceChanges.length >= 2 && currentPrice !== null) {
      const previousPrice = priceChanges[priceChanges.length - 2].price;
      const absoluteChange = Math.abs(currentPrice - previousPrice);
      const percentageChange = (absoluteChange / previousPrice) * 100;
      const percentageChangeText = percentageChange.toFixed(2);

      document.getElementById('percentage-change')!.textContent = `${percentageChangeText}%`;
    }
  }, [priceChanges, currentPrice]);

  const handleBuyOrSell = (action: string) => {
    if (quantity !== null && currentPrice !== null) {
      const transactionType = action === 'buy' ? 'Bought' : 'Sold';
      const transactionAmount = action === 'buy' ? quantity! : -quantity!;
      const transactionCost = Math.abs(transactionAmount * currentPrice);

      if (action === 'buy' && transactionCost > walletBalance) {
        alert('Insufficient funds. Please choose a smaller quantity or sell some stocks.');
        return;
      }

     
      setWalletBalance((prevBalance) => prevBalance + (action === 'buy' ? -transactionCost : transactionCost));

      setTransactionHistory((prevHistory) => [
        <TransactionContainer
          key={new Date().toLocaleString()}
          transactionType={transactionType}
        >
          <div>Stock Amount: {Math.abs(transactionAmount)}</div>
          <div>{transactionType}</div>
          <div>Date: {new Date().toLocaleString()}</div>
        </TransactionContainer>,
        ...prevHistory,
      ]);


      const transaction: TransactionProps = {
        timestamp: new Date().toISOString(),
        stock_name: stockData?.find((stock) => stock.stock_name === stockName)?.stock_name || '',
        stock_symbol: stockName?.slice(0, 3).toUpperCase() || '',
        transaction_price: currentPrice,
        status: 'success', 
        date: new Date().toLocaleDateString(),
      };

     
      onTransactionUpdate(transaction);
    }
    socket.emit('updatePrice', currentPrice);
  };

  if (!stockName) {
    return <p>No stock name provided.</p>;
  }

  const selectedStock = stockData.find((stock) => stock.stock_name === stockName);

  return (
    <StockPageContainer id="StockPageContainer">
      <StockContainer id="StockContainer">
        <div id="StockDataContainer">
          {selectedStock ? (
            <div id="stock-data">
              <div id="stock-name-container">
                <div id="stock-symbol">{stockName.slice(0, 3).toUpperCase()}</div>
                <p id="stock-name">{selectedStock.stock_name}</p>
              </div>
              <div id="price-details">
                <p id="price">Price</p>
                <div
                  id="price-change"
                  style={{
                    color:
                      currentPrice !== null
                        ? priceChanges.length > 0 && priceChanges[priceChanges.length - 1].positive
                          ? 'green'
                          : 'red'
                        : 'black',
                    display: 'flex',
                    alignItems: 'center',
                  }}
                >
                  {currentPrice !== null && priceChanges.length > 0 ? (
                    <>
                      {priceChanges[priceChanges.length - 1].positive ? (
                        <ArrowUpward style={{ color: 'green', marginRight: '4px' }} />
                      ) : (
                        <ArrowDownward style={{ color: 'red', marginRight: '4px' }} />
                      )}
                      {currentPrice.toFixed(2)}
                    </>
                  ) : (
                    'N/A'
                  )}
                </div>
                <p id="percentage-change" style={{ color: 'black' }}>
                  {'N/A'}
                </p>
              </div>
              <div id="enter-quantity">
                <input
                  type="text"
                  placeholder="Enter Qty"
                  onChange={(e) => setQuantity(parseInt(e.target.value, 10) || null)}
                />
              </div>
              <div id="buy-button">
                <button id="buy" onClick={() => handleBuyOrSell('buy')}>
                  Buy
                </button>
              </div>
              <div id="sell-button">
                <button id="sell" onClick={() => handleBuyOrSell('sell')}>
                  Sell
                </button>
              </div>
            </div>
          ) : (
            <p>Stock data not found for {stockName}...</p>
          )}
        </div>
        <div id="graph-container">
          <div id="graph-overlay"></div>
          {priceChanges.map((change, index) => (
            <div
              key={index}
              style={{
                width: '20px',
                height: `${change.price}px`,
                backgroundColor: change.positive ? 'lightgreen' : 'rgb(227, 145, 145)',
                margin: '2px',
              }}
            />
          ))}
        </div>
        <SimulatedStockPriceUpdater onUpdate={handlePriceUpdate} />
      </StockContainer>
      <LogsContainer style={{ border: '2px solid lightgray', borderRadius: '5px' }}>
        
        <div style={{ marginBottom: '10px', textAlign: 'center', fontWeight: 'bold', borderBottom: '2px solid lightgray' }}>
          Wallet Balance: ₹{walletBalance.toFixed(2)}
        </div>

        
        {transactionHistory.map((transaction, index) => (
          <div
            key={index}
            id="transaction"
            style={{ border: '1px solid lightgray', borderRadius: '15px', marginBottom: '6px' }}
          >
            {transaction}
          </div>
        ))}
      </LogsContainer>
    </StockPageContainer>
  );
};

export default StockPage;
