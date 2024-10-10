<template>
    <div class="cartTab" v-if="cartVisible">
        <h1>Shopping Cart</h1>
        <div class="listCart">
            <div class="item" v-for="item in cart" :key="item.cartItemId">
                <img :src="getProductImage(item.articleId)" alt="Product Image" />
                <div class="name">{{ getProductName(item.articleId) }}</div>
                <div class="totalPrice">$ {{ getProductPrice(item.articleId) * item.quantity }}</div>
                <div class="quantity">
                    <span @click="updateQuantity(item.cartItemId, 'minus')">-</span>
                    <span>{{ item.quantity }}</span>
                    <span @click="updateQuantity(item.cartItemId, 'plus')">+</span>
                </div>
            </div>
        </div>
        <div class="cart-buttons">
            <button @click="toggleCart">Close</button>
            <router-link to="/checkout"><button class="checkOut">Check Out</button></router-link>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
props: {
    cartVisible: Boolean,
    cart: Array
},
data() {
    return {
        products: []
    };
},
methods: {
    toggleCart() {
        this.$emit('toggle-cart');
    },
    updateQuantity(productId, type) {
        this.$emit('update-quantity', { productId, type });
    },
    async getProducts() {
        try {
            const response = await axios.get('http://localhost:8080/api/articles');
            this.products = response.data;
            console.log(this.cart);
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
    .cartTab {
        position: fixed;
        top: 0;
        right: 0;
        width: 400px;
        height: 100%;
        background-color: #fff;
        box-shadow: -2px 0 5px rgba(0,0,0,0.5);
        padding: 20px;
        z-index: 100;
        overflow-y: auto;
    }

    .cartTab h1 {
        font-size: 1.8rem;
        margin-bottom: 20px;
        text-align: center;
    }

    .listCart {
        margin-bottom: 20px;
    }

    .item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .item img {
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
