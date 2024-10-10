<template>
    <div class="container">
        <Header />
        <div class="products">
            <Products />
        </div>
        <Bottom />
    </div>
</template>

<script>
    import Products from "@/components/Products.vue";
    import Header from "@/components/Header.vue";
    import Bottom from "@/components/Bottom.vue";

    export default {
        name: 'Products_view',
        components: {
            Products,
            Header,
            Bottom
        }
    }
</script>

<style>
</style>