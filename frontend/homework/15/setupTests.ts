// setupTests.js or setupTests.ts
import { configure } from 'enzyme';
import Adapter from 'enzyme';

configure({ adapter: new Adapter() });
