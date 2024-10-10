<template>
    <div class="container">
        <Header />
        <section class="site-container">
            <p> Bienvenue dans le coffre fort aux</p>
            <h1> Trésors d'Antan</h1>
            <h4> Votre site de confiance pour la vente et l'achat de bijoux anciens et d'occasion en ligne.<br>
                La caverne d'Ali Baba est à portée de clic !
            </h4>
            <div class="row">
                <a href="#">Prendre rendez-vous</a>
                <router-link to="/about">Voir les magasins</router-link>
                <span>
                    <i class="fas fa-phone-alt"></i> 01 23 45 67 89
                </span>
            </div>
        </section>
        <Bottom />
    </div>
</template>

<script>
    import Bottom from '@/components/Bottom.vue';
    import Header from '@/components/Header.vue';

    export default {
        name: 'Home',
        components: {
            Header,
            Bottom
        }
    }
</script>

<style>
    .container {
		position: relative;
		width: 100%;
		background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('../assets/background.jpg');
		background-size:cover;
		background-position: center;
        background-repeat: no-repeat;
		background-attachment: fixed;
		padding: 0 8%;
		min-height: 100vh;
    }

    .site-container {
        padding-top: 250px;
        position: relative;
        bottom: 10%;
        color: #fff;
    }

    .site-container p {
    font-size: 50px;
    font-weight: bold;
    }

    .site-container h1 {
        font-size: 120px;
        line-height: 120px;
        margin-left: -10px;
        color: transparent;
        -webkit-text-stroke: 1px white;
        background: url('../assets/background.jpg');
        -webkit-background-clip: text;
        background-clip: text;
        background-position: 0 0;
        animation: animate 20s linear 2s infinite alternate;
    }

    @keyframes animate {
    100% {
    background-position: -500px 0;
    }
    }

    .site-container h3 {
    font-size: 40px;
    font-weight: 500;
    }

    .site-container .row {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    margin-top: 40px;
    }

    .row a {
    display: flex;
    align-items: center;
    padding: 5px 20px;
    text-decoration: none;
    color: #fff;
    border: 1px solid #fff;
    margin-right: 40px;
    font-size: 18px;
    transition: 0.3s;
    }

    .row a:hover {
    background-color: #fff;
    color: #000;
    }

    .row a span {
    font-size: 15px;
    font-weight: bold;
    line-height: 2;
    margin-left: 10px;
    }

    .row span {
    font-size: 18px;
    }
</style>