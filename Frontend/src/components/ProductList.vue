<template>
    <div class="category-tabs">
        <!-- Navigation pour les onglets -->
        <ul class="tabs">
            <li 
                v-for="category in categories" 
                :key="category.id"
                :class="{ active: activeTab === category.categoryId }"
                @click="activeTab = category.categoryId"
                class="tab"
            >
                {{ category.name }}
            </li>
        </ul>

        <!-- Afficher les produits pour l'onglet actif -->
        <div v-if="activeCategory" class="products">
            <ProductItem 
                v-for="product in activeCategory.products" 
                :key="product.articleId" 
                :product="product"
                @add-to-cart="addToCart"
                class="product-card"
            />
        </div>

        <!-- Boutons pour ajouter ou supprimer une catégorie et un produit et afficher si 
         l'utilisateur est un manager -->
        <div class="action-buttons" v-if="this.$store.state.userCredentials.role === 'MANAGER'">
            <button @click="addCategory">Ajouter une catégorie</button>
            <button @click="addProduct">Ajouter un produit</button>
        </div>
    </div>
</template>

<script>
import ProductItem from './ProductItem.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
    name: 'ProductList',
    components: {
        ProductItem
    },
    data() {
        return {
            categories: [],
            activeTab: null // L'onglet actif (ID de la catégorie active)
        };
    },
    computed: {
        // Retourne la catégorie active en fonction de l'onglet sélectionné
        activeCategory() {
            return this.categories.find(category => category.categoryId === this.activeTab);
        }
    },
    mounted() {
        this.getCategories();
    },
    methods: {
        async getCategories() {
            try {
                const response = await axios.get('http://localhost:8080/api/categories');
                this.categories = await Promise.all(response.data.map(async (category) => {
                    const productsResponse = await axios.get(`http://localhost:8080/api/articles/category/${category.categoryId}`);
                    return {
                        ...category,
                        products: productsResponse.data
                    };
                }));
                // Définir la première catégorie comme l'onglet actif par défaut
                if (this.categories.length > 0) {
                    this.activeTab = this.categories[0].categoryId;
                }
            } catch (error) {
                console.error('Error getting categories and products:', error);
            }
        },
        addToCart(productId) {
            this.$emit('add-to-cart', productId);
        },
        async addProduct() {
            const categoryOptions = this.categories.map(category => ({
                value: category.categoryId,
                text: category.name
            }));

            const { value: formValues } = await Swal.fire({
                title: 'Ajouter un produit',
                // NSERT INTO articles (name, category_id, price, year, stock, description, image_url) VALUES 
                html: `
                    <label for="productName">Nom du produit:</label>
                    <input id="productName" class="swal2-input" placeholder="Entrez le nom du produit">
                    <label for="categorySelect">Choisissez une catégorie:</label>
                    <select id="categorySelect" class="swal2-input">
                        ${categoryOptions.map(option => `<option value="${option.value}">${option.text}</option>`).join('')}
                    </select>
                    <label for="productPrice">Prix du produit:</label>
                    <input id="productPrice" class="swal2-input" placeholder="Entrez le prix du produit">
                    <label for="productYear">Année du produit:</label>
                    <input id="productYear" class="swal2-input" placeholder="Entrez l'année du produit">
                    <label for="productStock">Stock du produit:</label>
                    <input id="productStock" class="swal2-input" placeholder="Entrez le stock du produit">
                    <label for="productDescription">Description du produit:</label>
                    <input id="productDescription" class="swal2-input" placeholder="Entrez la description du produit">
                    <label for="productImageUrl">URL de l'image du produit:</label>
                    <input id="productImageUrl" class="swal2-input" placeholder="Entrez l'URL de l'image du produit">
                `,
                focusConfirm: false,
                preConfirm: () => {
                    const productName = document.getElementById('productName').value;
                    const categoryId = document.getElementById('categorySelect').value;
                    const productPrice = document.getElementById('productPrice').value;
                    const productYear = document.getElementById('productYear').value;
                    const productStock = document.getElementById('productStock').value;
                    const productDescription = document.getElementById('productDescription').value;
                    const productImageUrl = document.getElementById('productImageUrl').value;
                    return { productName, categoryId, productPrice, productYear, productStock, productDescription, productImageUrl };
                },
                showCancelButton: true,
                confirmButtonText: 'Ajouter',
                cancelButtonText: 'Annuler'
            });

            if (formValues) {
                const { productName, categoryId, productPrice, productYear, productStock, productDescription, productImageUrl } = formValues;

                if (productName) {
                    try {
                        await axios.post('http://localhost:8080/api/articles/add', {
                            name: productName,
                            categoryId: categoryId, // Utilisez l'ID de la catégorie sélectionnée
                            price: productPrice,
                            year: productYear,
                            stock: productStock,
                            description: productDescription,
                            imageUrl: productImageUrl
                        });
                        this.getCategories(); // Recharger les catégories
                        Swal.fire('Succès', 'Produit ajouté avec succès.', 'success');
                    } catch (error) {
                        Swal.fire('Erreur', 'Erreur lors de l\'ajout du produit.', 'error');
                    }
                }
            }
        },
        async addCategory() {
            const { value: categoryName } = await Swal.fire({
                title: 'Ajouter une catégorie',
                input: 'text',
                inputLabel: 'Nom de la catégorie',
                inputPlaceholder: 'Entrez le nom de la catégorie',
                showCancelButton: true,
                confirmButtonText: 'Ajouter',
                cancelButtonText: 'Annuler'
            });

            if (categoryName) {
                try {
                    await axios.post('http://localhost:8080/api/categories/add', {
                        name: categoryName
                    });
                    this.getCategories(); // Recharger les catégories
                    Swal.fire('Succès', 'Catégorie ajoutée avec succès.', 'success');
                } catch (error) {
                    Swal.fire('Erreur', 'Erreur lors de l\'ajout de la catégorie.', 'error');
                }
            }
        }
    }
};
</script>

<style scoped>
/* Style pour les onglets */
.tabs {
    display: flex;
    list-style: none;
    padding: 0;
    margin: 0 0 20px 0;
    border-bottom: 2px solid #ccc;
}

.tab {
    padding: 10px 20px;
    cursor: pointer;
    background-color: #f8f8f8;
    margin-right: 10px;
    border-radius: 10px 10px 0 0;
    transition: background-color 0.3s ease;
}

.tab:hover {
    background-color: #e0e0e0;
}

.active {
    background-color: #fff;
    border-bottom: 2px solid #fff; /* Pour donner l'impression d'être actif */
    font-weight: bold;
}

.products {
    display: flex;
}

.product-card {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
    padding: 15px;
    transition: transform 0.3s, box-shadow 0.3s;
}

.product-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
}

/* Style pour les boutons d'action */
.action-buttons {
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
}

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

@media (max-width: 768px) {
    .products {
        grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
    }
}
</style>
