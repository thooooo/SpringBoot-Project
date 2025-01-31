# **POKEMON TCG API**

## Contrôleur de Cartes (`/card/{dresseurUuid}`)
- `GET /card/{dresseurUuid}/draw`: Tirer des cartes pour un dresseur
  - Paramètre de requête : `numberOfCards` (défaut : 5)
  - Retourne la liste des cartes tirées

- `GET /card/{dresseurUuid}`: Obtenir toutes les cartes d'un dresseur

## Contrôleur de Dresseurs (`/dresseurs`)
- `GET /dresseurs`: Lister tous les dresseurs
- `POST /dresseurs`: Créer un nouveau dresseur
  ```json
  {
    "nom": "Dupont",
    "prenom": "Jean"
  }
  ```
- `DELETE /dresseurs/{uuid}`: Supprimer un dresseur
- `PATCH /dresseurs/{uuid}/capturer`: Capturer un pokémon
  ```json
  {
    "uuid": "identifiant-du-pokemon"
  }
  ```
- `POST /dresseurs/bataille`: Lancer un combat entre deux dresseurs
  - Paramètres requis : 
    - `challengerUuid`: UUID du dresseur challenger
    - `challengedUuid`: UUID du dresseur défié
  ```json
  {
    "challengerUuid": "uuid-du-dresseur-challenger",
    "challengedUuid": "uuid-du-dresseur-challenged"
  }
  ```

## Contrôleur de Pokémons (`/pokemons`)
- `GET /pokemons`: Lister tous les pokémons
  - Paramètre de requête optionnel : `type`
- `GET /pokemons/{uuid}`: Obtenir un pokémon spécifique
- `POST /pokemons`: Créer un nouveau pokémon
  ```json
  {
    "nom": "Pikachu",
    "niveau": 5,
    "type": "Électrik"
  }
  ```
  Règles de validation :
  - `nom` : Obligatoire, entre 3 et 20 caractères
  - `niveau` : Obligatoire, minimum 1
  - `type` : Obligatoire, doit correspondre à un type de pokémon existant
- `PUT /pokemons/{uuid}`: Mettre à jour un pokémon

## Contrôleur d'Échanges (`/trade`)
- `POST /trade`: Échanger une carte entre dresseurs
  ```json
  {
    "sourceDresseurUuid": "uuid-dresseur-source",
    "targetDresseurUuid": "uuid-dresseur-cible",
    "sourceCardUuid": "uuid-carte-source",
    "targetCardUuid": "uuid-carte-cible"
  }
  ```
- `GET /trade/historique`: Obtenir l'historique complet des échanges
- `GET /trade/historique/dresseur/{dresseurUuid}`: Obtenir l'historique des échanges d'un dresseur
- `GET /trade/historique/annee/{annee}`: Filtrer les échanges par année
- `GET /historique/annee/{annee}/mois/{mois}`: Filtrer les échanges par année et par mois
- `GET /historique/annee/{annee}/mois/{mois}/jour/{jour}`: Filtrer les échanges par année par mois et par jour