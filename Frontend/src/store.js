// import { getCurrentInstance } from 'vue';
import { createStore } from 'vuex';

export default createStore({
    state: {
        userCredentials: JSON.parse(sessionStorage.getItem('userCredentials')) || null,
        cart: JSON.parse(sessionStorage.getItem('cart')) || null
    },
    mutations: {
        setUserCredentials(state, credentials) {
            state.userCredentials = credentials;
            sessionStorage.setItem('userCredentials', JSON.stringify(credentials));
        },
        removeUserCredentials(state) {
            state.userCredentials = null;
            sessionStorage.removeItem('userCredentials');
            state.cart = null;
            sessionStorage.removeItem('cart');
        },
        setCart(state, cart) {
            state.cart = cart;
            sessionStorage.setItem('cart', JSON.stringify(cart));
        }
    },
    actions: {
        setUserCredentials({ commit }, credentials) {
            commit('setUserCredentials', credentials);
        },
        setCart({ commit }, cart) {
            commit('setCart', cart);
        }
    },
    getters: {
        getUserCredentials(state) {
            return state.userCredentials;
        },
        getCart(state) {
            return state.cart;
        }
    }
});
