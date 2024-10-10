<template>
<div class="checkout-page">
    <h1>Checkout</h1>
    
    <div class="cart-items">
        <CartItem v-for="item in cartItems" 
            :key="item.cartItemId" 
            :item="item" 
            @update-quantity="updateQuantity"
            @remove-item="removeFromCart" />
    </div>
    
    <div class="delivery-address">
    <h2>Adresse de livraison</h2>
    <form @submit.prevent="validateOrder">
        <label for="address">Adresse:</label>
        <input type="text" v-model="deliveryAddress" id="address" required />
        <button type="submit">Valider la commande</button>
    </form>
    </div>

    <div class="cart-buttons">
    <button v-if="orderId" @click="payOrder">Payer</button>
    </div>
</div>
</template>

<script>
import CartItem from './CartItem.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
name: 'Checkout',
components: {
    CartItem
},
data() {
    return {
    cartItems: [], // Load items from the cart
    orderId: null, // Store the order ID after validation
    deliveryAddress: '', // Delivery address
    };
},
mounted() {
    this.getCartItems(); // Fetch cart items when component is mounted
},
methods: {
    async getCartItems() {
    // Fetch the cart items from the API
    if (!this.$store.state.cart) {
        return;
    }
    try {
        console.log("cart id: ", this.$store.state.cart.cartId);
        const response = await axios.get('http://localhost:8080/api/cart-items/cart/' + this.$store.state.cart.cartId);
        this.cartItems = response.data;
    } catch (error) {
        console.error('Error getting cart items:', error);
    }
    },
    async validateOrder() {
    // Call API to create the order
    try {
        const response = await axios.post('http://localhost:8080/api/orders/add', {
            userId: this.$store.state.userCredentials.userId,
            cartId: this.$store.state.cart.cartId,
            totalPrice: this.cartItems.reduce((total, item) => total + item.totalPrice, 0),
            deliveryAddress: this.deliveryAddress
        });
        this.orderId = response.data.orderId;
        // vider le panier après la validation de la commande
        await axios.delete('http://localhost:8080/api/cart-items/delete/cart/' + this.$store.state.cart.cartId);
        Swal.fire({
            icon: 'success',
            title: 'Commande validée!',
            text: 'Votre commande a été créée avec succès.',
        });
    } catch (error) {
        console.error('Error validating order:', error);
        Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'La validation de la commande a échoué.',
        });
    }
    },
    async payOrder() {
    // Call API to pay for the order
    try {
        const response = await axios.post(`http://localhost:8080/api/payments`, {
            orderId: this.orderId,
            amount: this.cartItems.reduce((total, item) => total + item.totalPrice, 0)
        });
        Swal.fire({
            icon: 'success',
            title: 'Paiement effectué!',
            text: 'Votre commande a été payée avec succès.',
        });
        console.log(response.data);
        this.$router.push('/profile');
    } catch (error) {
        console.error('Error processing payment:', error);
        Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Le paiement a échoué.',
        });
    }
    },
    updateQuantity({ productId, type }) {
        const item = this.cartItems.find(i => i.cartItemId === productId);
        if (type === 'minus') {
            item.quantity -= 1;
        } else {
            item.quantity += 1;
        }
        this.updateCart(item);
    },
    async updateCart(item) {
        try {
            await axios.put('http://localhost:8080/api/cart-items/update/' + item.cartItemId, item.quantity, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            this.getCartItems();
            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: 'Cart updated successfully!',
            });
        } catch (error) {
            console.error('Error updating cart item:', error);
        }
    },
    async removeFromCart(productId) {
        try {
            await axios.delete('http://localhost:8080/api/cart-items/delete/' + productId);
            this.getCartItems();
            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: 'Product removed from cart successfully!',
            });
        } catch (error) {
            console.error('Error removing product from cart:', error);
            Swal.fire({
                icon: 'error',
                title: 'Error!',
                text: 'Failed to remove product from cart!',
            });
        }
    }
}
};
</script>

<style scoped>
.checkout-page {
    background-color: white;
    width: 50%;
    margin: 0 auto;
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

.checkout-page {
padding: 20px;
}

.checkout-actions {
display: flex;
gap: 20px;
margin-top: 20px;
}

.delivery-address {
margin-top: 20px;
}

.delivery-address form {
display: flex;
flex-direction: column;
}

.delivery-address label {
margin: 10px 0 5px;
}

.delivery-address input {
padding: 8px;
margin-bottom: 10px;
border: 1px solid #ccc;
border-radius: 5px;
}

.cart-buttons {
display: flex;
justify-content: center;
margin-top: 20px;
}

button {
padding: 10px 20px;
border: none;
border-radius: 5px;
background-color: #ca852b;
color: white;
cursor: pointer;
}

button:hover {
background-color: maroon;
}
</style>