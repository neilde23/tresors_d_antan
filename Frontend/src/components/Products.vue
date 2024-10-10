<template>
    <div class="products">
        <ProductList @add-to-cart="addToCart" />
    </div>
</template>

<script>
import ProductList from './ProductList.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
    name: 'App',
    components: {
        ProductList
    },
    data() {
        return {
            products: []
        };
    },
    computed: {
    },
    methods: {
        async addToCart(productId) {
            try {
                // appel API pour ajouter le produit au panier
                const response = await axios.post('http://localhost:8080/api/cart-items/add', {
                    cartId: this.$store.state.cart.cartId,
                    articleId: productId,
                    quantity: 1
                });
                console.log(response.data); // Afficher la réponse du serveur

                // notifier l'utilisateur que le produit a été ajouté au panier
                Swal.fire({
                    icon: 'success',
                    title: 'Success!',
                    text: 'Product added to cart successfully!',
                });
            } catch (error) {
                console.error('Error adding product to cart:', error);

                // notifier l'utilisateur que l'ajout au panier a échoué
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'Failed to add product to cart!',
                });
            }
        }
    }
};
</script>

<style scoped>
.products {
    display: flex;
    padding-top: 200px;
}

h1, h2 {
    text-align: center;
    margin-bottom: 20px;
}

.category {
    margin-bottom: 40px;
}

</style>