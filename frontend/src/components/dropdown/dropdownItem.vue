<template>
    <li>
        <div class="dropdown-item" @click="redirect">
            <slot></slot>
        </div>
    </li>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { useRouter } from 'vue-router';

export default defineComponent({
    name:"DropdownItem",
    props:{
        target:String,
        link:String
    },
    setup(props) {
        const target = ref(props.target);
        const link = ref(props.link);
        const router = useRouter();
        const redirect = () => {
            const routerData = ref(router.resolve({name:link.value}));
            window.open(routerData.value.href, target.value);
        }
        return {redirect};
    },
})
</script>

<style>
    li{
    width: 100%;
    border-radius: 1.25rem;
    padding: 1rem 2rem;
    text-align: center;
    }

    a{
    color: var(--flex-dark);
    white-space: nowrap;
    }

    li:hover{
    background-color: #18465d10;
    transition: all .5s;
    cursor: pointer;
    }

</style>
