import React, { createContext, useContext, useState, ReactNode } from 'react';

interface StockData {
    stock_name:   string;
    stock_symbol: string;
    base_price:   number;
}

interface StockDataContextProps {
  children: ReactNode;
}

interface StockDataContextValue {
  stockData: StockData[];
  updateStockData: (newData: StockData[]) => void;
}

const StockDataContext = createContext<StockDataContextValue | undefined>(undefined);

export const StockDataProvider: React.FC<StockDataContextProps> = ({ children }) => {
  const [stockData, setStockData] = useState<StockData[]>([]);

  const updateStockData = (newData: StockData[]) => {
    setStockData(newData);
  };

  return (
    <StockDataContext.Provider value={{ stockData, updateStockData }}>
      {children}
    </StockDataContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useStockData = (): StockDataContextValue => {
  const context = useContext(StockDataContext);
  if (!context) {
    throw new Error('useStockData must be used within a StockDataProvider');
  }
  return context;
};
