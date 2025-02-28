# Utiliser une étape de build séparée pour compiler l'application
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copier uniquement les fichiers nécessaires pour la construction
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# Rendre gradlew exécutable et construire l'application
RUN ./gradlew build --no-daemon

# Étape finale avec une image plus légère
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copier uniquement le JAR construit de l'étape précédente
COPY --from=builder /app/build/libs/PayProModulith-0.0.1-SNAPSHOT.jar app.jar

# Créer un utilisateur non-root pour des raisons de sécurité
RUN addgroup --system springuser && \
    adduser --system --ingroup springuser springuser
USER springuser

# Configuration de l'environnement de production
ENV SPRING_PROFILES_ACTIVE=prod
#    JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

EXPOSE 8080

# Utiliser exec form pour permettre la propagation des signaux
ENTRYPOINT ["java", "-jar", "app.jar"]