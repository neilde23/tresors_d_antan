<template>
		<router-view />
		<div class="scroll-to-top" @click="scrollToTop">
		<i class="fas fa-arrow-up"></i>
		</div>
</template>

<script>
export default {
	name: 'App',
	components: {
	},
	methods: {
		scrollToTop() {
			window.scrollTo({
				top: 0,
				behavior: 'smooth'
			});
		}
	}
}
</script>

<style>
	*,
    *::before,
    *::after {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
		outline: none;
		font-family: 'Tw Cen MT', sans-serif;
    }

	body {
        margin: 0;
        padding: 0;
    }

	/* Bouton pour remonter en haut de la page */
	.scroll-to-top {
		position: fixed;
		bottom: 20px;
		right: 20px;
		background-color: #a47c3a;
		color: white;
		border: none;
		border-radius: 50%;
		padding: 10px;
		cursor: pointer;
		z-index: 1;
		transition: background-color 0.3s ease, transform 0.3s ease;
	}

	.scroll-to-top i {
		font-size: 20px;
	}

	.scroll-to-top:hover {
		background-color: #d79026;
		transform: scale(1.1);
	}

	/* Réduire la taille du bouton sur les petits écrans */
	@media (max-width: 600px) {
		.scroll-to-top {
		bottom: 10px;
		right: 10px;
		padding: 8px;
		font-size: 16px;
		}
	}
</style>
