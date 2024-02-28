
import { shallowMount } from '@vue/test-utils';
import { ListItem } from '../todo-list/ListItem';


describe('ListItem', () => {

  it('renders correctly and calls onDelete when the button is clicked', async () => {
 
    const onDeleteMock = jest.fn();

    const wrapper = shallowMount(ListItem, {
      props: {
        text: 'Test Item',
        onDelete: onDeleteMock,
      },
    });

    expect(wrapper.text()).toContain('Test Item');


    await wrapper.find('#delete-button').trigger('click');

    expect(onDeleteMock).toHaveBeenCalled();
  });
});
