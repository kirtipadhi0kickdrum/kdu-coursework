
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import Tab from '@mui/material/Tab';
import Tabs from '@mui/material/Tabs';
import headerIcon from '../assets/image.png';
import { Link as RouterLink, useLocation } from 'react-router-dom';

const Header = () => {
  const location = useLocation();

  const isTabActive = (route: string) => {
    return location.pathname === route;
  };
  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <IconButton edge="start" color="inherit" aria-label="icon">
            <img src={headerIcon} alt="Stock" style={{ height: '40px', width: '40px' }} />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            KDU Stock Market
          </Typography>
          <Tabs textColor="inherit">
            <Tab label="Summarizer" component={RouterLink} to="/summarizer" sx={{ textDecoration: isTabActive('/summarizer') ? 'underline' : 'none' }} />
            <Tab label="My Portfolio" component={RouterLink} to="/MyPortfolio"  sx={{ textDecoration: isTabActive('/MyPortfolio') ? 'underline' : 'none' }} />
          </Tabs>
        </Toolbar>
      </AppBar>
    </div>
  );
};

export default Header;
