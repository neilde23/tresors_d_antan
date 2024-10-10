<template>
    <h1 class="title">Bonjour {{ this.$store.state.userCredentials.name }} !</h1>
    <h2>Bienvenue sur votre profil</h2>
    <div class="profile-container">
        <!-- Bloc des informations utilisateur -->
        <div class="user-info">
            <h3>Nom d'utilisateur :</h3>
            <p> {{ this.$store.state.userCredentials.name }}</p>
            <h3>Email :</h3>
            <p> {{ this.$store.state.userCredentials.email }}</p>
            <h3>Adresse :</h3>
            <p> {{ this.$store.state.userCredentials.address }}</p>
            <h3>Role :</h3>
            <p> {{ this.$store.state.userCredentials.role }}</p>
            <h3>Mot de passe :</h3>
            <p>
                <span v-if="showPassword">{{ this.$store.state.userCredentials.password }}</span>
                <span v-else>**********</span>
                <button @click="togglePasswordVisibility">{{ showPassword ? 'Cacher' : 'Afficher' }} Mot de passe</button>
                <button @click="changePassword">Changer le mot de passe</button>
            </p>
            <button @click="logout">Déconnexion</button>
            <ChangePasswordPopup :show="showPopup" @close="showPopup = false" />
        </div>

        <!-- Bloc des commandes utilisateur -->
        <div class="user-orders">
            <h3>Vos commandes :</h3>
            <ul v-if="orders.length">
                <li v-for="order in orders" :key="order.id">
                    <p><strong>Commande #{{ order.orderId }}</strong></p>
                    <p>Total: {{ order.totalPrice }} €</p>
                    <p>Statut de la commande: {{ order.status }}</p>
                </li>
            </ul>
            <p v-else>Aucune commande pour le moment.</p>
        </div>
    </div>
</template>

<script>
import ChangePasswordPopup from './ChangePasswordPopup.vue';
import axios from 'axios';

export default {
    name: 'ProfileComponent',
    components: {
        ChangePasswordPopup
    },
    data() {
        return {
            showPopup: false,
            showPassword: false,
            orders: [] // Variable pour stocker les commandes de l'utilisateur
        };
    },
    mounted() {
        this.getUserOrders(); // Charger les commandes de l'utilisateur à la montée du composant
    },
    methods: {
        getUserOrders() {
            // Exemple de requête API pour obtenir les commandes de l'utilisateur
            axios.get('http://localhost:8080/api/orders/user/' + this.$store.state.userCredentials.userId)
                .then(response => {
                    this.orders = response.data;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des commandes :", error);
                });
        },
        changePassword() {
            this.showPopup = true;
        },
        logout() {
            console.log('Logging out...');
            this.$store.commit('removeUserCredentials');
            this.$router.push('/');
        },
        togglePasswordVisibility() {
            this.showPassword = !this.showPassword;
        }
    }
};
</script>

<style scoped>
.title {
    text-align: center;
    font-size: 2em;
    color: white;
    padding-top: 200px;
}

h2 {
    text-align: center;
    margin-top: 10px;
    font-size: 1.5em;
    color: white;
}

h3 {
    margin-top: 10px;
    font-size: 1.2em;
    color: white;
}

/* Container principal pour l'affichage en colonnes */
.profile-container {
    position: relative;
    display: flex;
    justify-content: space-between;
}

/* Bloc des informations utilisateur */
.user-info {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex: 1;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: transparent;
    padding: 20px;
    margin-top: 20px;
    margin-right: 20px;
    color: white;
    height: fit-content;
}

/* Bloc des commandes utilisateur */
.user-orders {
    display: flex;
    flex-direction: column;
    flex: 1;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: transparent;
    padding: 20px;
    margin-top: 20px;
    margin-right: 20px;
    color: white;
    /* donner la hauteur de user-info */
    height: 270px;
    overflow-y: auto;
}

.user-orders ul {
    list-style-type: none;
    padding: 0;
}

.user-orders li {
    border-bottom: 1px solid #ccc;
    padding: 10px 0;
}

/* Style pour les boutons */
button {
    background-color: #ca852b;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: maroon;
}
</style>
