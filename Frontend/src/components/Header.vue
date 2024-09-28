<template>
    <header id="header" class="d-flex justify-content-center py-3 sticky-top">
        <nav class="navbar nav-masthead">
            <router-link class="logo" to="/"><img src="../assets/logo.png" alt="Logo"></router-link>
            <div class="nav-links">
                <router-link class="nav-link fw-bold py-1 px-0" to="/">Home</router-link>
                <router-link class="nav-link fw-bold py-1 px-0" to="/about">About</router-link>
            </div>
        </nav>
    </header>
</template>

<script>
    export default {
        name: 'Header'
    }
</script>

<style scoped>
    * {
        font-size: small;
    }

    #header {
        background-color: #161719;
        border-bottom: 0.5px solid #a47c3a;
        opacity: 0.6;
    }

    .navbar {
        display: flex;
        width : 100%;
        align-items: center;
    }

    .nav-links {
        display: flex;
        justify-content: space-between;
    }

    .nav-links .login-pages {
        display: flex;
        margin: 0 20px;
    }

    nav .logo {
        margin-left: 20px; /* Adjust margin as needed */
        height: 50px; /* Adjust height as needed */
    }

    nav img {
        height: 70px; /* Adjust height as needed */
    }

    .user-info {
        display: flex;
        margin-right: 20px;
    }

    .user-info a {
        color: white; /* Set text color */
        margin: 0 10px;
        text-decoration: none;
    }

    .user-info a:hover {
        text-decoration: underline;
    }

    .user-info span {
        color: white; /* Set text color */
        margin-right: 10px;
    }
</style>