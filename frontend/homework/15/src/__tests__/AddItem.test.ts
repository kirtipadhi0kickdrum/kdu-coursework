import {shallowMount} from '@vue/test-utils'
import { AddItem } from '../todo-list/AddItem'

describe('AddItem', () => {
    it('renders correctly and dispatches addItem action on button click', async () => {
      const wrapper = shallowMount(AddItem)
  

      expect(wrapper.html()).toContain('<div id="add-container">')
      expect(wrapper.html()).toContain('<p id="add-item-title">Add Items</p>')
      expect(wrapper.html()).toContain('<input type="text" id="list-input">')
      expect(wrapper.html()).toContain('<button id="add-item">Submit</button>')
  
     
      const mockDispatch = jest.fn()
      wrapper.vm.$store.dispatch = mockDispatch
  
   
      await wrapper.setData({ inputText: 'Test Item' })
  
    
      await wrapper.find('#add-item').trigger('click')
  

      expect(mockDispatch).toHaveBeenCalledWith('addItem', { text: 'Test Item' })
  
  
    })
  })