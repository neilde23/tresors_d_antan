<!-- TeamFormPopup.vue -->
<template>
    <div class="popup-overlay" v-if="show">
        <div class="popup-container">
            <h2>Changer le mot de passe</h2>
            <form @submit.prevent="handleSubmit">
                <label for="oldPassword">Ancien mot de passe:</label>
                <input type="password" id="oldPassword" v-model="formData.oldPassword" required>

                <label for="newPassword">Nouveau mot de passe:</label>
                <input type="password" id="newPassword" v-model="formData.newPassword" required>

                <label for="confirmPassword">Confirmer le mot de passe:</label>
                <input type="password" id="confirmPassword" v-model="formData.confirmPassword" required>

                <div class="popup-buttons">
                    <button type="button" @click="closePopup">Annuler</button>
                    <button type="submit">Sauvegarder</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        show: {
            type: Boolean,
            required: true
        }
    },
    data() {
        return {
            formData: {
                oldPassword: '',
                newPassword: '',
                confirmPassword: ''
            }
        };
    },
    methods: {
        handleSubmit() {
            // Emit 'create' event with form data
            this.$emit('create', this.formData);
        },
        closePopup() {
            // Emit 'close' event to close the popup
            this.$emit('close');
        }
    }
}
</script>

<!-- Styles -->
<style scoped>

.popup-overlay {
    position: fixed;
    top: 5%;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.popup-container {
    align-items: center ;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #ab906d;
}

.popup-container h2 {
    padding: 20px;
    text-align: center;
    color: white;
}

.popup-container label {
    display: block;
    margin-bottom: 10px;
    color: white;
}

.popup-container input,
.popup-container textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.popup-container .popup-buttons {
    display: flex;
    justify-content: space-between;
}

.popup-container button {
    width: 25%;
    padding: 10px;
    border-radius: 5px;
    border: none;
    color: white;
    background-color: #ca852b;
}

.popup-container button:hover {
    background-color: maroon;
}
</style>
