import { shallowMount } from "@vue/test-utils"
import { SearchItem } from "../todo-list/SearchItem"
import { createStore } from "redux"



import { rootReducer }from "../redux/Reducers"; // Adjust the path accordingly
import { combineReducers } from "@reduxjs/toolkit";


const rootReducers = combineReducers({
  todo: rootReducer,
 
});


const store = createStore(rootReducers);


  describe('SearchItem', () => {
    it('renders correctly and updates search text', async () => {
        const wrapper = shallowMount(SearchItem, {
          global: {
            plugins: [
              {
                install: (app) => {
                  app.config.globalProperties.$store = store;
                },
              },
            ],
          },
        });
  

      expect(wrapper.html()).toContain('<div id="search-container">')
      expect(wrapper.html()).toContain('<p id="search-title">Items Lister</p>')
      expect(wrapper.html()).toContain('<input type="text" id="searchInput"')
  
      
      await wrapper.setData({ searchText: 'Test Search' })
  
    
      expect(store.commit).toHaveBeenCalledWith('todo/setSearchText', 'Test Search')
  

      const filteredList = wrapper.vm.filteredList
      expect(filteredList).toEqual([
        { id: 1, text: 'Item 1' },
        { id: 2, text: 'Item 2' },

      ])

      await wrapper.setData({ searchText: 'Nonexistent Item' })
      expect(wrapper.html()).toContain('<p>No items found</p>')
    })
  })