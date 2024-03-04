import React, { useEffect, useState } from 'react';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TextField from '@mui/material/TextField';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormGroup from '@mui/material/FormGroup';
import Button from '@mui/material/Button';
import CircularProgress from '@mui/material/CircularProgress';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';

interface Transaction {
  timestamp: string;
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  status: string;
  date?: string; 
}

const formatTransactionTime = (timestamp: string): string => {
  const date = new Date(timestamp);
  const options = { hour: 'numeric', minute: 'numeric', hour12: true };
  return new Intl.DateTimeFormat('en-US', options).format(date);
};

const MyPortfolio: React.FC = () => {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [filteredTransactions, setFilteredTransactions] = useState<Transaction[]>([]);
  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState('');
  const [failedFilter, setFailedFilter] = useState(false);
  const [stockFilters, setStockFilters] = useState<string[]>([]);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json');

        if (!response.ok) {
          throw new Error('Failed to fetch transaction data');
        }
        const data: Transaction[] = await response.json();

        
        const formattedData = data.map((transaction) => ({
          ...transaction,
          date: new Date(transaction.timestamp).toLocaleDateString(),
        }));

        setTransactions(formattedData);
        setFilteredTransactions(formattedData);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching data', error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const filterTransactions = () => {
    let filtered = [...transactions];

    if (filter) {
      filtered = filtered.filter(transaction =>
        transaction.stock_name.toLowerCase().includes(filter.toLowerCase())
      );
    }

    if (failedFilter) {
      filtered = filtered.filter(transaction => transaction.status.toLowerCase() === 'failed');
    }

    if (stockFilters.length > 0) {
      filtered = filtered.filter(transaction => stockFilters.includes(transaction.stock_symbol));
    }

    if (startDate && endDate) {
      filtered = filtered.filter(transaction =>
        transaction.date >= startDate && transaction.date <= endDate
      );
    }

    setFilteredTransactions(filtered);
  };

  useEffect(() => {
    filterTransactions();
  }, [filter, failedFilter, stockFilters, startDate, endDate]);

  const uniqueStockNames = Array.from(new Set(transactions.map(transaction => transaction.stock_symbol)));

  const handleStockFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const stockSymbol = event.target.name;
    setStockFilters(prevFilters =>
      prevFilters.includes(stockSymbol)
        ? prevFilters.filter(filter => filter !== stockSymbol)
        : [...prevFilters, stockSymbol]
    );
  };

  const clearFilters = () => {
    setFilter('');
    setFailedFilter(false);
    setStockFilters([]);
    setStartDate('');
    setEndDate('');
  };


  const groupedTransactions: { [date: string]: Transaction[] } = {};
  filteredTransactions.forEach((transaction) => {
    if (!groupedTransactions[transaction.date]) {
      groupedTransactions[transaction.date] = [];
    }
    groupedTransactions[transaction.date].push(transaction);
  });

  return (
    <div style={{ display: 'flex' }}>
     
      <Paper style={{ width: '30%', margin: '16px', padding: '16px', borderRadius: '10px', backgroundColor: '#f0f0f0' }}>
        <Typography variant="h6" style={{ marginBottom: '16px' }}>Filters</Typography>

        <div style={{ borderBottom: '1px solid #ccc', marginBottom: '11px', paddingBottom: '12px' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <TextField
              label="Filter by Stock Name"
              variant="outlined"
              value={filter}
              onChange={(e) => setFilter(e.target.value)}
              fullWidth
              style={{ marginRight: '8px', backgroundColor: '#f0f0f0' }}
            />
            <Button variant="contained" color="primary" onClick={clearFilters}>
              Clear All
            </Button>
          </div>
        </div>

        <div style={{ borderBottom: '1px solid #ccc', marginBottom: '16px', paddingBottom: '16px' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <TextField
              type="date"
              label="Start Date"
              variant="outlined"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
              fullWidth
              InputLabelProps={{ shrink: true }}
              style={{ marginRight: '4px', flex: 1, backgroundColor: '#f0f0f0' }}
            />
            <TextField
              type="date"
              label="End Date"
              variant="outlined"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
              fullWidth
              InputLabelProps={{ shrink: true }}
              style={{ flex: 1, backgroundColor: '#f0f0f0' }}
            />
          </div>
        </div>

        <div style={{ borderBottom: '1px solid #ccc', marginBottom: '16px', paddingBottom: '16px' }}>
          <FormControlLabel
            control={
              <Checkbox
                checked={failedFilter}
                onChange={() => setFailedFilter(!failedFilter)}
                color="primary"
              />
            }
            label="Show Failed Transactions"
          />
        </div>

        <div style={{ overflowY: 'auto', maxHeight: '150px', marginTop: '16px' }}>
          <FormControl component="fieldset">
            <FormGroup>
              {uniqueStockNames.map((stockSymbol) => (
                <FormControlLabel
                  key={stockSymbol}
                  control={
                    <Checkbox
                      checked={stockFilters.includes(stockSymbol)}
                      onChange={handleStockFilterChange}
                      name={stockSymbol}
                    />
                  }
                  label={stockSymbol}
                />
              ))}
            </FormGroup>
          </FormControl>
        </div>
      </Paper>

      
       <div style={{ width: '70%', padding: '16px' }}>
        <Typography variant="h4"></Typography>

        <div style={{ overflowX: 'auto', maxHeight: '500px', marginTop: '16px' }}>
          {loading ? (
            <CircularProgress />
          ) : (
            <>
              {Object.entries(groupedTransactions).map(([date, transactions]) => (
                <div key={date}>
                  <Typography variant="h6" style={{ marginBottom: '8px' }}>{date}</Typography>
                  <TableContainer component={Paper}>
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>Stock Name</TableCell>
                          <TableCell>Stock Symbol</TableCell>
                          <TableCell>Base Price</TableCell>
                          <TableCell>Transaction Time</TableCell>
                          <TableCell>Status</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {transactions.map((transaction, index) => (
                          <TableRow key={transaction.date + transaction.stock_symbol + index}>
                            <TableCell>{transaction.stock_name}</TableCell>
                            <TableCell>{transaction.stock_symbol}</TableCell>
                            <TableCell>{transaction.transaction_price}</TableCell>
                            <TableCell>{formatTransactionTime(transaction.timestamp)}</TableCell>
                            <TableCell>
                              {transaction.status.toLowerCase() === 'failed' ? (
                                <CancelIcon color="error" />
                              ) : (
                                <CheckCircleIcon color="success" />
                              )}
                            </TableCell>
                          </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </div>
              ))}
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default MyPortfolio;
