import React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TablePagination from '@mui/material/TablePagination';
import Typography from '@mui/material/Typography';
import CancelIcon from '@mui/icons-material/Cancel';
import { useWatchlist } from './WatchListContext';
import { useStockData } from './StockDataContext';

const Watchlist = () => {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const { watchlist, removeFromWatchlist } = useWatchlist();
  const { stockData } = useStockData();

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

return (
  <div>
    <Typography variant="h4"></Typography>
    <div style={{ border: '2px solid #333', borderRadius: '10px', width: '80%', margin: 'auto' }}>
      <TableContainer component={Paper}>
        <Table>
          <TableHead style={{borderBottom: '2px solid #333'}}>
            <TableRow>
              <TableCell>Stock Symbol</TableCell>
              <TableCell>Base Price</TableCell>
              <TableCell>Watchlist</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {watchlist.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((stockSymbol) => {
              const stockDetails = stockData.find((stock) => stock.stock_name === stockSymbol);

              return (
                <TableRow key={stockSymbol} style={{ textDecoration: 'none', color: 'inherit' }}>
                  <TableCell style={{ cursor: 'pointer' }}>
                    {stockDetails?.stock_symbol}
                  </TableCell>
                  <TableCell>{stockDetails ? stockDetails.base_price : 'N/A'}</TableCell>
                  <TableCell>
                    <div style={{ display: 'flex', alignItems: 'center' }}>
                      <CancelIcon
                        style={{ cursor: 'pointer' }}
                        onClick={() => removeFromWatchlist(stockSymbol)}
                      />
                    </div>
                  </TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={watchlist.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
        style={{display: 'flex',alignItems: 'center', justifyContent: 'center', borderTop: '2px solid #333'}}
      />
    </div>
  </div>
);
};

export default Watchlist;