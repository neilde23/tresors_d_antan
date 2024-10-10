<template>
    <div class="container">
        <Header />
        <div class="about">
            <h1>A propos de nous</h1>
            <p class="about-us-text">
                Notre site vous propose une large gamme de produits de qualité à des prix très compétitifs. Nous vous garantissons une livraison rapide et un service client de qualité.
                <br />
                N'hésitez pas à nous contacter pour toute question.
                Retrouvez-nous facilement ! Nous sommes impatients de vous accueillir dans notre boutique, située à proximité de votre localisation. 
                Grâce à notre calculateur d'itinéraire, découvrez à quel point il est simple de nous rejoindre ! Venez profiter de nos produits de qualité, 
                proposés à des prix compétitifs, et bénéficiez d'un service client attentionné. Nous vous attendons avec plaisir !
            </p>
            <!-- Section pour afficher la carte Leaflet -->
            <div id="map"></div>
            <div class="carte">
                <input type="text" id="origin" placeholder="Entrez vos coordonnées (latitude, longitude)" v-model="origin">
                <button @click="calculateRoute">Calculer l'itinéraire</button>
            </div>
        </div>
        <Bottom />
    </div>
</template>

<script>
    import Bottom from '@/components/Bottom.vue';
    import Header from '@/components/Header.vue';
    import 'leaflet/dist/leaflet.css';
    import L from 'leaflet';

    export default {
        name: 'About',
        components: {
            Header,
            Bottom
        },
        data() {
            return {
                origin: '',
                destination: [48.78890195355877, 2.3636654662180594],  // Coordonnées du magasin
                map: null
            };
        },
        mounted() {
            this.initMap();
        },
        methods: {
            initMap() {
                // Initialisation de la carte centrée sur Paris
                this.map = L.map('map').setView([48.8566, 2.3522], 12);

                // Ajouter une couche de base à la carte
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                }).addTo(this.map);
            },
            calculateRoute() {
                const originCoords = this.origin.split(',');
                if (originCoords.length !== 2) {
                alert('Veuillez entrer des coordonnées valides.');
                return;
                }

                const originLat = originCoords[0].trim();
                const originLng = originCoords[1].trim();
                const apiKey = '5b3ce3597851110001cf6248b2f6cca88eb146c9a02ce76655524578';

                const url = `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${originLng},${originLat}&end=${this.destination[1]},${this.destination[0]}`;

                fetch(url)
                .then(response => response.json())
                .then(data => {
                    if (data.features && data.features.length > 0) {
                    const coords = data.features[0].geometry.coordinates;
                    const latlngs = coords.map(coord => [coord[1], coord[0]]);
                    const polyline = L.polyline(latlngs, { color: 'blue' }).addTo(this.map);
                    this.map.fitBounds(polyline.getBounds());
                    }
                })
                .catch(error => {
                    console.error('Erreur de calcul de l\'itinéraire:', error);
                });
            }
        }
    };
</script>

<style scoped>
    .about {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: white;
        padding-top: 200px;
    }
    .about h1 {
        font-size: 50px;
    }
    .about p {
        margin-top: 20px;
        font-size: 20px;
    }
    #map {
        width: 50%;
        height: 300px;
        margin-top: 20px;
    }

    .carte {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 20px;
    }

    input {
        padding: 10px;
        margin: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        padding: 10px 20px;
        background-color: #ca852b;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: maroon;
    }

    .about-us-text {
        margin-top: 50px;
        font-family: 'Roboto', sans-serif;
        font-size: 1.2rem;
        color: #333;
        line-height: 1.6;
        background-color: rgba(255, 255, 255, 0.8); /* Légère transparence pour un effet élégant */
        padding: 20px;
        border-left: 5px solid #d4a373; /* Bordure gauche pour accentuer le style */
        border-radius: 8px; /* Coins arrondis */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Ombre subtile pour un effet de profondeur */
        margin: 20px auto;
        max-width: 800px; /* Largeur maximale pour un alignement centré et lisible */
        text-align: justify; /* Alignement justifié pour une belle présentation du texte */
    }
</style>
