<template>
    <nav>
		<router-link to="/"><img src="../assets/logo.png" alt="" class="logo" /></router-link>
		<ul>
            <li><router-link to="/">Accueil</router-link></li>
            <li><router-link to="/articles">Articles</router-link></li>
            <li><router-link to="/about">A propos</router-link></li>
            <div class="user-info">
                <!-- Icon for profile -->
                <router-link class="nav-link" :to="$store.state.userCredentials ? '/profile' : '/login'">
                    <i class="fas fa-user"></i>
                    {{ $store.state.userCredentials ? $store.state.userCredentials.name : 'Login' }}
                </router-link>
                <!-- User's name -->
                <span></span>
                <!-- Icon for notifications -->
                <router-link class="nav-link" to="/"><i class="fas fa-bell"></i></router-link>
            </div>
		</ul>
        <div class="cart icon-cart" @click="toggleCart">
            <i class="fas fa-shopping-cart"></i>
            <span class="cart-count">{{ cartTotalQuantity }}</span>
        </div>
        <Cart
            :cartVisible="cartVisible"
            :cart="cartItems"
            @toggle-cart="toggleCart"
            @update-quantity="updateQuantity"
        />
    </nav>
</template>

<script>
    import Cart from './Cart.vue';
    import axios from 'axios';
    import Swal from 'sweetalert2';

    export default {
        name: 'Header',
        components: {
            Cart
        },
        data() {
            return {
                cartVisible: false,
                cartItems: [],
            };
        },
        computed: {
            cartTotalQuantity() {
                if (!this.$store.state.cart) {
                    return 0;
                }
                if (!this.cartItems.length) {
                    return 0;
                }
                // this.getCartItems();
                let total = 0;
                this.cartItems.forEach(item => {
                    total += item.quantity;
                });
                return total;
            }
        },
        mounted () {
            this.getCartItems();
        },
        methods: {
            async getCartItems() {
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
            toggleCart() {
                this.cartVisible = !this.cartVisible;
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
            }
        }
    }
</script>

<style scoped>
    nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap;
    padding: 5px 0;
    position: fixed;
    margin-top:0;
    width :80%;
    z-index: 100;
    }

    nav .logo {
        width: 120px;
        cursor: pointer;
    }

    nav .cart {
        width: 40px;
        cursor: pointer;
        color : white;
    }

    nav ul {
        display: flex;
        justify-content: flex-end;
        flex: 1;
        padding-right: 40px;
        text-align: right;
        list-style-type: none;
    }

    ul li {
        margin-right: 10px;
    }

    ul li a {
        position: relative;
        padding: 10px 20px;
        text-decoration: none;
        color: #fff;
        font-size: 16px;
    }

    ul li a::after {
        content: '';
        position: absolute;
        bottom: 0;
        right: 0;
        width: 0;
        transform: translateX(-50%) translateY(-5px);
        height: 3px;
        background: #ffa400;
        transition: 0.3s;
    }

    ul li a:hover::after {
        width: 38%;
    }

    .user-info a {
        position: relative;
        padding: 10px 20px;
        text-decoration: none;
        color: #fff;
        font-size: 16px;
    }

    .user-info a::after {
        content: '';
        position: absolute;
        bottom: 0;
        right: 0;
        width: 0;
        transform: translateX(-50%) translateY(-5px);
        height: 3px;
        background: #ffa400;
        transition: 0.3s;
    }

    .user-info a:hover::after {
        width: 38%;
    }

    .user-info span {
        color: white; /* Set text color */
        margin-right: 10px;
    }
</style>