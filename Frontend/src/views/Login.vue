<template>
    <div class="container">
        <Header />
        <div class="login">
            <Login />
        </div>
        <Bottom />
    </div>
</template>

<script>
    import Login from '@/components/Login.vue';
    import Header from '@/components/Header.vue';
    import Bottom from '@/components/Bottom.vue';

    export default {
        name: 'Login_view',
        components: {
            Login,
            Header,
            Bottom
        }
    }
</script>

<style>
    .login {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        color: white;
    }
</style>