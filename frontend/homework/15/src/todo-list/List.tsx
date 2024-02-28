
import './List.css'

import { ListItem } from './ListItem';
import { useDispatch, useSelector } from 'react-redux';
import { deleteItem } from '../redux/Actions';
import { TodoListState } from '../redux/Store';

export function List() {

    const dispatch = useDispatch()
    const {list, searchText} = useSelector((state: TodoListState) => state.todo)

    const filteredList = list.filter((item) =>
        item.text.toLowerCase().includes(searchText.toLowerCase())
    );
    
  return (

    <ul id='list'>
        {
            filteredList.map((item) => {
                return(
                    <ListItem key={item.id.toString()} text={item.text} onDelete={() => dispatch(deleteItem(item.id))}/>
                )
            })
        }
    </ul>
  )
}
