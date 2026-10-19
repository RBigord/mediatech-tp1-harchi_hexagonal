# MédiaTech — TP1 : premier hexagone en Java pur

Projet de départ du TP1. L'énoncé complet est dans le document Word distribué.

## Démarrer

Dans IntelliJ : **File → Open…**, choisir ce dossier (celui qui contient `pom.xml`), JDK 21.
Puis fenêtre **Maven → Lifecycle → test** : le projet doit compiler, avec 0 test pour l'instant.

En ligne de commande, si Maven est installé : `mvn test`.

## Ce qui vous est fourni

- un `pom.xml` **volontairement minimal** : JUnit 5 et AssertJ, uniquement pour les tests ;
- l'arborescence des packages, avec un `package-info.java` qui indique ce que chaque package doit contenir.

```
com.mediatech.emprunt
├── domaine/                 🧠 pur Java
│   └── exceptions/
├── application/             ⬡ pur Java
│   └── port/
│       ├── in/              ⬅️ ports primaires
│       └── out/             ➡️ ports secondaires
└── adapter/                 🔌 technique
    ├── in/console/
    └── out/
        ├── memoire/
        └── console/
```

## Règles du jeu

- Aucune dépendance supplémentaire dans le `pom.xml`.
- Aucun `LocalDate.now()` dans `domaine/` ni dans `application/`.
- Aucun setter public dans le domaine.

## Lancer la borne (étape 6)

Ouvrez `MediatechApplication` et cliquez sur le triangle vert à côté de `main`.
(En ligne de commande : `mvn -q compile exec:java`.)

## Rendu

Déposez sur Moodle un zip de ce dossier, **sans le dossier `target/`**, nommé `TP1-Nom1-Nom2.zip`.
