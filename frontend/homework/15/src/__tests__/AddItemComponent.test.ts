
import { render, fireEvent } from '@testing-library/react';
import { Provider } from 'react';
import configureStore from 'redux-mock-store'; 
import { AddItem } from '../todo-list/AddItem';
import { addItem } from '../redux/Actions';


const mockStore = configureStore();
const store = mockStore({});

describe('AddItem Component', () => {
  it('renders correctly and dispatches addItem action on button click', () => {
    
    const { getByText, getByTestId } = render(
      <Provider store={store}>
        <AddItem />
      </Provider>
    );

    expect(getByText('Add Items')).toBeInTheDocument();


    const inputElement = getByTestId('list-input');
    const submitButton = getByText('Submit');

  
    fireEvent.change(inputElement, { target: { value: 'Test Item' } });

    
    fireEvent.click(submitButton);


    expect(store.getActions()).toEqual([addItem({ text: 'Test Item' })]);


    expect(inputElement).toHaveValue('');
  });
});
