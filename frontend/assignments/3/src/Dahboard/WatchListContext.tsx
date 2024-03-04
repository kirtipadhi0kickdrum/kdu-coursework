import React, { createContext, useContext, useState, ReactNode } from 'react';

interface WatchlistContextType {
  watchlist: string[];
  addToWatchlist: (stockName: string) => void;
  removeFromWatchlist: (stockName: string) => void;
}

interface WatchlistProviderProps {
  children: ReactNode;
}

const WatchlistContext = createContext<WatchlistContextType | undefined>(undefined);

export const WatchlistProvider: React.FC<WatchlistProviderProps> = ({ children }) => {
  const [watchlist, setWatchlist] = useState<string[]>([]);

  const addToWatchlist = (stockName: string) => {
    setWatchlist((prevWatchlist) =>
      prevWatchlist.includes(stockName)
        ? prevWatchlist
        : [...prevWatchlist, stockName]
    );
  };

  const removeFromWatchlist = (stockName: string) => {
    setWatchlist((prevWatchlist) =>
      prevWatchlist.filter((stock) => stock !== stockName)
    );
  };

  return (
    <WatchlistContext.Provider value={{ watchlist, addToWatchlist, removeFromWatchlist }}>
      {children}
    </WatchlistContext.Provider>
  );
};

export const useWatchlist = () => {
  const context = useContext(WatchlistContext);
  if (!context) {
    throw new Error('useWatchlist must be used within a WatchlistProvider');
  }
  return context;
};
