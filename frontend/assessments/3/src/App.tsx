import { Provider } from "react-redux"
import store from "./redux/Store"
import { RoomType } from "./hotel/RoomType"


function App() {

  return (
    <Provider store={store}>
      <div id="hotel-booking">
        <RoomType/>
      </div>
    </Provider>
  )
}

export default App
