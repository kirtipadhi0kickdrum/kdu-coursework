
import { render, fireEvent } from '@testing-library/react';
import configureStore from 'redux-mock-store';
import { SearchItem } from '../todo-list/SearchItem';
import { setSearchText } from '../redux/Actions';
import { Provider } from 'react';


const mockStore = configureStore();
const initialState = {
  todo: {
    list: [{ id: 1, text: 'Item 1' }, { id: 2, text: 'Item 2' }],
    searchText: '',
  },
};
const store = mockStore(initialState);

describe('SearchItem Component', () => {
  it('renders correctly and updates search text', () => {
 
    const { getByText, getByPlaceholderText } = render(
      <Provider store={store}>
        <SearchItem />
      </Provider>
    );


    expect(getByText('Items Lister')).toBeInTheDocument();

    
    const searchInput = getByPlaceholderText('  Search Items...');


    fireEvent.change(searchInput, { target: { value: 'Test Search' } });


    expect(store.getActions()).toEqual([setSearchText('Test Search')]);

    expect(getByText('No items found')).toBeInTheDocument();
  });
});
