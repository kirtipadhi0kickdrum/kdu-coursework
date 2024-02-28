
import { render, fireEvent } from '@testing-library/react';
import { Provider } from 'react';
import configureStore from 'redux-mock-store'; 
import { List } from '../todo-list/List';
import { deleteItem } from '../redux/Actions';


const mockStore = configureStore();
const initialState = {
  todo: {
    list: [
      { id: 1, text: 'Item 1' },
      { id: 2, text: 'Item 2' },
   
    ],
    searchText: '',
  },
};
const store = mockStore(initialState);

describe('List Component', () => {
  it('renders correctly and dispatches deleteItem action on item deletion', () => {
 
    const { getByText } = render(
      <Provider store={store}>
        <List />
      </Provider>
    );


    expect(getByText('Item 1')).toBeInTheDocument();
    expect(getByText('Item 2')).toBeInTheDocument();


    const deleteButton = getByText('X');

    fireEvent.click(deleteButton);

    expect(store.getActions()).toEqual([deleteItem(1)]);
  });
});
