<template>
    <div class="login">
        <h1>Connectez-vous</h1>
        <form @submit.prevent="submitForm" class="form-style">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" v-model="user.email" required>
            </div>
            <div>
                <label for="password">Mot de passe:</label>
                <input type="password" id="password" v-model="user.password" required>
            </div>
            <button type="submit">Se connecter</button>
            <router-link to="/register" class="nav-link">Pas de compte? Inscrivez-vous</router-link>
        </form>
    </div>  
</template>

<script>
    import axios from 'axios';
    import Swal from 'sweetalert2';

    export default {
        data() {
            return {
                user: {
                    email: '',
                    password: ''
                }
            }
        },
        methods: {
            async submitForm() {
                try {
                    // Effectuer la requête GET avec les données du formulaire en paramètres
                    const response = await axios.get('http://localhost:8080/api/users/login', {
                        params: {
                            email: this.user.email,
                            password: this.user.password
                        }
                    });
                    console.log("response : " + response.data.userId); // Afficher la réponse du serveur
                    try {
                        const response2 = await axios.get('http://localhost:8080/api/cart/user/' + response.data.userId);
                        this.$store.dispatch('setCart', response2.data);
                        console.log(response2.data); // Afficher la réponse du serveur
                    } catch (error) {
                        console.error('Error getting cart:', error);

                        // notifier l'utilisateur que la récupération du panier a échoué
                        Swal.fire({
                            icon: 'error',
                            title: 'Error!',
                            text: 'Failed to get cart!',
                        });
                    }
                    // save the user credentials in the store
                    this.$store.dispatch('setUserCredentials', response.data);

                    // notify the user that he is logged in successfully
                    Swal.fire({
                        icon: 'success',
                        title: 'Success!',
                        text: 'You are logged in successfully!',
                    });

                    // redirect to the player page
                    this.$router.push('/');

                } catch (error) {
                    console.error('Error logging in user:', error);

                    // notify the user that the login failed
                    Swal.fire({
                        icon: 'error',
                        title: 'Error!',
                        text: 'Your email or password is incorrect!',
                    });
                }
            }
        }
    }
</script>

<style scoped>
h1{
    padding : 20px;
    text-align : center;
    color : white;
}

.form-style {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: transparent;
}

.form-style label {
    display: block;
    margin-bottom: 10px;
    color : white;
}

.form-style input {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.form-style button {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: none;
    color: white;
    background-color: #ca852b;
}

.form-style button:hover {
    background-color: maroon;
}

.nav-link {
    color: white;
    text-align: center;
    display: block;
    margin-top: 10px;
}

.nav-link:hover {
    text-decoration: underline;
    color: maroon;
}

</style>