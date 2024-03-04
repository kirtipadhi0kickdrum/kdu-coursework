import React, { useEffect, useState } from 'react';

interface SimulatedStockPriceUpdaterProps {
  onUpdate: (newPrice: number) => void;
}

const SimulatedStockPriceUpdater: React.FC<SimulatedStockPriceUpdaterProps> = ({ onUpdate }) => {
    const [currentPrice, setCurrentPrice] = useState<number>(Math.random() * 500);
  useEffect(() => {
    const intervalId = setInterval(() => {

      const priceChange = (Math.random() - 0.5)*10
      const newPrice = currentPrice + priceChange; 

      
      onUpdate(newPrice);
      setCurrentPrice(newPrice)
    }, 2000); 

    return () => clearInterval(intervalId); 
  }, [currentPrice,onUpdate]);

  return null;
};

export default SimulatedStockPriceUpdater;
