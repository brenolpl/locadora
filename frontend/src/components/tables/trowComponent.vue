<template>
    <tr>
        <slot></slot>
        <td>
            <action-dropdown>
                <dropdown-item>
                    <trash-icon />
                    Excluir
                </dropdown-item>
                <dropdown-item>
                    <edit-icon />
                    Editar
                </dropdown-item>
            </action-dropdown>
        </td>
    </tr>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name:'MyRow',
    setup() {
        return {}
    },
})
</script>

<style scoped>
    td{
        padding:.75rem;
    }
</style>