<template>
    <div class="cart-item">
        <img :src="getProductImage(item.articleId)" alt="Product Image" />
        <div class="name">{{ getProductName(item.articleId) }}</div>
        <div class="totalPrice">$ {{ getProductPrice(item.articleId) * item.quantity }}</div>
        <div class="quantity">
            <span @click="updateQuantity(item.cartItemId, 'minus')">-</span>
            <span>{{ item.quantity }}</span>
            <span @click="updateQuantity(item.cartItemId, 'plus')">+</span>
        </div>
        <div class="cart-buttons">
            <button @click="removeFromCart" class="remove-btn">Remove</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
    export default {
        name: 'CartItem',
        data () {
            return {
                products: []
            };
        },
        props: {
            item: Object, // L'objet 'item' contient les informations sur l'article dans le panier
        },
        methods: {
            updateQuantity(productId, type) {
                // Emmetre un événement pour mettre à jour la quantité dans le parent
                this.$emit('update-quantity', { productId, type });
            },
            removeFromCart() {
                // Emmetre un événement pour retirer l'article du panier
                this.$emit('remove-item', this.item.cartItemId);
            },
            async getProducts() {
                try {
                    const response = await axios.get('http://localhost:8080/api/articles');
                    this.products = response.data;
                } catch (error) {
                    console.error('Error getting products:', error);
                }
            },
        },
        computed: {
            getProductImage() {
                return (productId) => {
                    const product = this.products.find(p => p.articleId === productId);
                    return product ? product.imageUrl : '';
                };
            },
            getProductName() {
                return (productId) => {
                    const product = this.products.find(p => p.articleId === productId);
                    return product ? product.name : '';
                };
            },
            getProductPrice() {
                return (productId) => {
                    const product = this.products.find(p => p.articleId === productId);
                    return product ? product.price : 0;
                };
            }
        },
        mounted() {
            this.getProducts();
        }
    };
</script>

<style scoped>
    .cart-item {
        display: flex;
        gap: 20px;
        padding: 15px;
        border-bottom: 1px solid #ccc;
        align-items: center;
    }

    img {
    width: 50px;
    height: 50px;
    border-radius: 5px;
    }

    .name {
    flex-grow: 1;
    margin-left: 10px;
    font-size: 1rem;
    }

    .totalPrice {
    font-size: 1.2rem;
    margin-right: 10px;
    }

    .quantity {
    display: flex;
    align-items: center;
    }

    .quantity span {
    padding: 0 5px;
    cursor: pointer;
    font-weight: bold;
    }

    .cart-buttons {
    display: flex;
    justify-content: space-between;
    }

    .cart-buttons button {
    padding: 10px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    background-color: #ca852b;
    color: white;
    }

    .cart-buttons button:hover {
    background-color: maroon;
    }
</style>
