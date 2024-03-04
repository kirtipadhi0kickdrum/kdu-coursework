import  { useEffect, useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TablePagination from '@mui/material/TablePagination';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import CancelIcon from '@mui/icons-material/Cancel';
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import { useStockData } from './StockDataContext';
import { useWatchlist } from './WatchListContext';

const Dashboard = () => {
  const [allStocks, setAllStocks] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const { addToWatchlist, removeFromWatchlist, watchlist } = useWatchlist();
  const { stockData, updateStockData } = useStockData();

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleToggleWatchlist = (stockName) => {
    if (watchlist.includes(stockName)) {
      removeFromWatchlist(stockName);
    } else {
      addToWatchlist(stockName);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json');

        if (!response.ok) {
          throw new Error('Failed to fetch stock data');
        }
        const data = await response.json();
        updateStockData(data);
        setAllStocks(data);
      } catch (error) {
        console.error('Error fetching data', error);
      }
    };

    fetchData();
  }, [updateStockData]);

  return (
    <div>
      <Typography variant="h4">
        <Link component={RouterLink} to="/dashboard" color="inherit"></Link>
      </Typography>
      <div style={{ border: '1px solid #ddd', width: '80%', margin: 'auto' }}>
        <TableContainer component={Paper} style={{ borderRadius: '10px' }}>
          <Table style={{ borderLeft: '2px solid', borderRight: '2px solid' }}>
            <TableHead style={{ border: '2px solid' }}>
              <TableRow>
                <TableCell>Stock Name</TableCell>
                <TableCell>Base Price</TableCell>
                <TableCell>Watchlist</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {allStocks.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((stock) => (
                <TableRow key={stock.stock_name}>
                  <TableCell
                    key={stock.stock_name}
                    component={RouterLink}
                    to={`/stock/${encodeURIComponent(stock.stock_name)}`}
                    style={{ textDecoration: 'none', color: 'inherit' }}
                  >
                    {stock.stock_name}
                  </TableCell>
                  <TableCell>{stock.base_price}</TableCell>
                  <TableCell>
                    {watchlist.includes(stock.stock_name) ? (
                      <CancelIcon
                        style={{ cursor: 'pointer' }}
                        onClick={() => handleToggleWatchlist(stock.stock_name)}
                      />
                    ) : (
                      <div
                        style={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }}
                        onClick={() => handleToggleWatchlist(stock.stock_name)}
                      >
                        {watchlist.includes(stock.stock_name) ? (
                          <CheckCircleOutlineIcon style={{ marginRight: '8px' }} />
                        ) : (
                          <AddCircleOutlineIcon style={{ marginRight: '8px' }} />
                        )}
                      </div>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={allStocks.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
            style={{ justifyContent: 'center', display: 'flex', border: '2px solid' }}
          />
        </TableContainer>
      </div>
    </div>
  );
};

export default Dashboard;

