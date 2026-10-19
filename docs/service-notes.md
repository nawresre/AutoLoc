# Atelier 4 — IoC, DI et couche Service

## A. Modes d'injection

| Critère | Constructeur | Attribut |
|---|---|---|
| Champ final | Oui | Non |
| Dépendance visible | Oui, dans le constructeur | Non |
| Utilisable hors Spring | Oui, avec repository fourni | Risque de null |
| SonarQube | Recommandé | Vérifier S6813 dans IntelliJ |

Expérience à faire: remplacer temporairement `@RequiredArgsConstructor` et `final` dans `ContratServiceImpl` par `@Autowired` sur le champ. Relancer, relever l'avertissement, puis RESTAURER le constructeur.

## B. Services

| Service | Dépendance injectée | Injection | Justification |
|---|---|---|---|
| AgenceServiceImpl | AgenceRepo | Constructeur | Découplage, instance sans état |
| EmployeServiceImpl | EmployeRepo | Constructeur | Découplage, instance sans état |
| VehiculeServiceImpl | VehiculeRepo | Constructeur | Découplage, instance sans état |
| EquipementServiceImpl | EquipmentRepo | Constructeur | Découplage, instance sans état |
| ClientServiceImpl | ClientRepo | Constructeur | Découplage, instance sans état |
| ReservationServiceImpl | ReservationRepo | Constructeur | Découplage, instance sans état |
| ContratServiceImpl | ContractRepo | Constructeur | Découplage, instance sans état |
| PaiementServiceImpl | PaimentRepo | Constructeur | Découplage, instance sans état |
| MaintenanceServiceImpl | MaintenanceRepo | Constructeur | Découplage, instance sans état |


**Noms existants conservés** : `ContractRepo`, `PaimentRepo`, `EquipmentRepo`, package `tn.esprit.springhome` et `services`.

## C. Erreurs du conteneur

1. Bean `IContratService` introuvable: `@Service` absent sur `ContratServiceImpl` ou classe hors component scan. Corriger l'annotation et le package.
2. Deux notificateurs: `@Primary` sur l'implémentation préférée OU `@Qualifier("emailNotificateur")` sur le paramètre injecté du constructeur.
3. Cycle entre `ClientServiceImpl` et `ReservationServiceImpl`: chaque constructeur attend l'autre; extraire une responsabilité commune dans un troisième service non cyclique.

## D. SonarQube for IDE

**À vérifier réellement dans IntelliJ (ne pas prétendre les avoir exécutés)** :

| Essai | Règle attendue | Correction |
|---|---|---|
| Injection par attribut (temporaire) | S6813, selon version | Injection constructeur |
| `RuntimeException` générique (temporaire) | S112 | `ResourceNotFoundException` |
| Autre alerte | À relever lors de l'analyse locale | À documenter après correction |

## E. Questions

1. Aucun `new ContratServiceImpl` dans le code applicatif: le conteneur Spring crée le bean via `@Service` et le scan.
2. Un contrôleur dépend de `IContratService` pour rester indépendant de l'implémentation.
3. Le singleton est partagé entre requêtes; garder un `contratCourant` en attribut provoquerait des écrasements entre utilisateurs.
4. `update` recharge la ligne existante pour modifier seulement des champs autorisés et préserver identifiants et collections (`paiements` notamment).
5. `@Component` scanne une classe annotée; `@Bean` déclare un objet depuis une méthode de configuration. `@Primary` choisit le candidat par défaut; `@Qualifier` sélectionne explicitement le bean. Paiement est en lecture seule parce que les écritures passent par le Contrat.
