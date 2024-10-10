<template>
    <div class="container">
        <Header />
        <div class="checkout">
            <Checkout />
        </div>
        <Bottom />
    </div>
</template>

<script>
    import Checkout from '@/components/Checkout.vue';
    import Header from '@/components/Header.vue';
    import Bottom from '@/components/Bottom.vue';

    export default {
        name: 'Checkout_view',
        components: {
            Checkout,
            Header,
            Bottom
        }
    }
</script>

<style>
    .checkout {
        padding-top: 150px;
    }
</style>