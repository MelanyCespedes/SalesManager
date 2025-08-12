# Imagen base con sqlite3 instalado
FROM alpine:latest

# Instalar sqlite3
RUN apk add --no-cache sqlite

# Crear directorio para la base de datos y scripts
WORKDIR /app

# Copiar el script SQL al contenedor
COPY init.sql /app/init.sql

# Crear la base de datos y ejecutar el script para inicializar las tablas
RUN sqlite3 /app/database.db < /app/init.sql

# Exponer el directorio como volumen para acceder a la base de datos desde fuera
VOLUME ["/app"]

# Comando por defecto: abre la CLI de sqlite
CMD ["sqlite3", "/app/database.db"]
