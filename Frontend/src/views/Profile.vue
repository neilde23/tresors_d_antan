<template>
    <div class="container">
        <Header />
        <div v-if="this.$store.state.userCredentials" class="profile">
            <ProfileComponent />
        </div>
        <Bottom />
    </div>
</template>

<script>
    import ProfileComponent from "@/components/ProfileComponent.vue";
    import Header from "@/components/Header.vue";
    import Bottom from "@/components/Bottom.vue";

    export default {
        name: 'Profile',
        components: {
            ProfileComponent,
            Header,
            Bottom
        }
    }
</script>

<style>
    .profile {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        color: white;
    }
</style>