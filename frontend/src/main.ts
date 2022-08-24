import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import './assets/style.css'

import AppDropdown from './components/dropdown/dropdownComponent.vue';
import DropdownItem from './components/dropdown/dropdownItem.vue'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component("app-dropdown", AppDropdown)
app.component("dropdown-item", DropdownItem)

app.mount('#app')
