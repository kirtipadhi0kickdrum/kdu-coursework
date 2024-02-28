import { render } from "@testing-library/react"
import { persistor, todoListStore } from "../redux/Store"
import { Provider } from "react-redux"
import { PersistGate } from "redux-persist/integration/react"
import { TodoList } from "../todo-list/TodoList"

jest.mock('./List', () => () => <div data-testid='list-component'/>)

test('TodoList integration test', async ()=> {
    render(
        <Provider store={todoListStore}>
            <PersistGate loading={null} persistor={persistor}>
                <TodoList/>
            </PersistGate>
        </Provider>
    )

    // const searchInput = screen.getByPlaceholderText'  Search Items...'()
})