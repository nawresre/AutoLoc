# Étapes Atelier 4 sur springHOMe

1. Ouvrir le projet dans IntelliJ, Java 21, recharger Maven.
2. Vérifier les neuf interfaces `repositories/*Repo.java` : chacune étend `JpaRepository<Entity, Long>`. **Les noms existants sont conservés.**
3. Examiner `exception/ResourceNotFoundException.java`.
4. Examiner `services/IContratService.java` et `services/ContratServiceImpl.java` : CRUD, `@Service`, `@RequiredArgsConstructor`, repository final, validation et préservation des paiements.
5. Vérifier les huit autres couples de services : sept CRUD plus Paiement en lecture seule.
6. Expérience temporaire: injection par champ `@Autowired` dans Contrat, consulter SonarQube puis RESTAURER la version constructeur.
7. Examiner les 3 erreurs d'injection décrites dans `docs/service-notes.md`.
8. Remplacer temporairement l'exception métier par `RuntimeException` pour relever la règle S112 puis RESTAURER l'exception métier.
9. SonarQube for IDE sur `services` et `exception`; relever et corriger 3 anomalies réellement observées. Compléter la section D.
10. Vérifier MySQL dans `application.properties`, lancer l'application et chercher `Found 9 JPA repository interfaces`.
11. Commit et push Git.

Aucun contrôleur REST ajouté : il relève de l'Atelier 5. `Maintenance.java` a uniquement été corrigé en supprimant une relation JPA auto-référente invalide `maintenances` (mappedBy = vehicule), sans renommage d'entité ou champ restant.
