<template>
<div class="item">
    <img :src="product.imageUrl" alt="Product Image" />
    <h2>{{ product.name }}</h2>
    <div class="price">$ {{ product.price }}</div>
    <h2>{{ product.description }}</h2>
    <!-- afficher seulement pour les agents et les managers -->
    <h2 v-if="this.$store.state.userCredentials.role === 'AGENT' || this.$store.state.userCredentials.role === 'MANAGER'">
    Stock: {{ product.stock }}</h2>
    <button @click="addToCart">Add To Cart</button>
</div>
</template>

<script>
export default {
    name: 'ProductItem',
    props: {
        product: Object
    },
    methods: {
        addToCart() {
            this.$emit('add-to-cart', this.product.articleId);
        }
    }
};
</script>

<style scoped>
.item {
    border: 1px solid #ddd;
    padding: 20px;
    text-align: center;
    border-radius: 10px;
    background-color: #4d2d04;
    transition: transform 0.2s ease-in-out;
    margin: 10px;
    width: 300px;
}

.item:hover {
    transform: translateY(-5px);
}

.item img {
    max-width: 100%;
    height: auto;
    border-radius: 5px;
}

h2 {
    font-size: 1.5rem;
    margin: 10px 0;
}

.price {
    font-size: 1.2rem;
    color: #333;
    margin-bottom: 15px;
}

button {
    padding: 10px 15px;
    background-color: #ca852b;
    border: none;
    color: white;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

button:hover {
  background-color: maroon;
}
</style>
