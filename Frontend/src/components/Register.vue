<template>
    <div class="register">
        <h1>Inscrivez-vous</h1>
        <form @submit.prevent="submitForm" class="form-style">
            <div>
                <label for="username">Nom d'utilisateur:</label>
                <input type="text" id="username" v-model="user.username" required>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" v-model="user.email" required>
            </div>
            <div>
                <label for="address">Adresse:</label>
                <input type="address" id="address" v-model="user.address" required>
            </div>
            <div>
                <label for="password">Mot de passe:</label>
                <input type="password" id="password" v-model="user.password" required>
            </div>
            <div>
                <label for="confirmPassword">Confirmer le mot de passe:</label>
                <input type="password" id="confirmPassword" required>
            </div>
            <button type="submit">S'inscrire</button>
            <router-link to="/login" class="nav-link">Déjà un compte? Connectez-vous</router-link>
        </form>
    </div>
</template>

<script>
    import axios from 'axios';
    import Swal from 'sweetalert2';

    export default {
        data() {
            return {
                user:{
                    username: '',
                    email: '',
                    address: '',
                    password: ''
                }
            }
        },
        methods: {
            verifyPassword() {
                // Vérifier si les mots de passe correspondent
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirmPassword').value;

                if (password !== confirmPassword) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error!',
                        text: 'Passwords do not match!',
                    });
                    return false;
                }
                return true;
            },
            async submitForm() {
                // Vérifier si les mots de passe correspondent
                if (!this.verifyPassword()) {
                    return;
                }
                try {
                    // Effectuer la requête POST avec les données du formulaire
                    const response = await axios.post('http://localhost:8080/api/users/add', {
                        name: this.user.username,
                        email: this.user.email,
                        address: this.user.address,
                        password: this.user.password,
                        role: 'CLIENT'
                    }); 

                    console.log(response.data); // Afficher la réponse du serveur

                    // notify the user that the account has been created
                    Swal.fire({
                        icon: 'success',
                        title: 'Success!',
                        text: 'Your account has been created successfully!',
                    });

                    // Redirect to the sign-in page
                    this.$router.push('/login');
                } catch (error) {
                    console.error('Error creating user:', error);

                    // notify the user that the account creation failed
                    Swal.fire({
                        icon: 'error',
                        title: 'Error!',
                        text: 'An error occurred while creating your account!',
                    });
                }
            }
        }
    }
</script>

<style scoped>

.register {
    margin-top: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 100vh;
}

h1{
    padding : 10px;
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
    padding: 5px;
    border-radius: 5px;
    border: 1px solid #ccc;
    align-items: center;
    margin-bottom: 5px;
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
    color: maroon;
    text-decoration: underline;
}

</style>