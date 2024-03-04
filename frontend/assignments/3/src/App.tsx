import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link as RouterLink } from 'react-router-dom';
import Dashboard from './Dahboard/Dashboard';  
import StockPage from './StockPage/StockPage';
import Header from './Dahboard/Header';  
import { Tab, Tabs } from '@mui/material';
import { StockDataProvider } from './Dahboard/StockDataContext';  
import { WatchlistProvider } from './Dahboard/WatchListContext';  
import Summarizer from './Dahboard/Summarizer'; 
import Watchlist from './Dahboard/Watchlist'; 
import MyPortfolio from './Dahboard/MyPortfolio';  

const App: React.FC = () => {
  return (
    <Router>
      <StockDataProvider>
        <WatchlistProvider>
          <div>
            <Header />
            <Tabs>
              <Tab label="Explore" component={RouterLink} to="/dashboard" />
              <Tab label="My Watchlist" component={RouterLink} to="/watchlist" />
            </Tabs>
            <Routes>
              <Route path="/dashboard" element={<Dashboard />} />
              <Route path="/watchlist" element={<Watchlist />} />  
              <Route path="/stock/:stockName" element={<StockPage />} />
              <Route path="/summarizer" element={<Summarizer />} />
              <Route path="/MyPortfolio" element={<MyPortfolio />} />
            </Routes>
          </div>
        </WatchlistProvider>
      </StockDataProvider>
    </Router>
  );
};

export default App;
