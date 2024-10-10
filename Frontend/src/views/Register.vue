<template>
    <div class="container">
        <Header />
        <div class="register">
            <Register />
        </div>
        <Bottom />
    </div>
</template>

<script>
    import Register from "@/components/Register.vue";
    import Header from "@/components/Header.vue";
    import Bottom from "@/components/Bottom.vue";

    export default {
        name: 'Register_view',
        components: {
            Register,
            Header,
            Bottom
        }
    }
</script>

<style>
    .register {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        color: white;
    }
</style>