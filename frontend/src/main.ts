import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import './assets/style.css'

import myComponents from './plugins/myComponents'

// import AppDropdown from './components/dropdown/dropdownComponent.vue';
// import ActionDropdown from './components/dropdown/actionDropdown.vue'
// import DropdownItem from './components/dropdown/dropdownItem.vue'
// import TrashIcon from './components/iconsComponents/trashFillComponent.vue'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(myComponents)

app.mount('#app')
