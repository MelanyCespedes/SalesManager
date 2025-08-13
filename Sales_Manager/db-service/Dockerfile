# Imagen base con sqlite3 instalado
FROM alpine:latest

# Instalar sqlite3 y bash
RUN apk add --no-cache sqlite bash

# Crear directorio para la base de datos y scripts
WORKDIR /app

# Copiar el script SQL y el entrypoint al contenedor
COPY init.sql /app/init.sql
COPY entrypoint.sh /app/entrypoint.sh

# Dar permisos de ejecución al entrypoint
RUN chmod +x /app/entrypoint.sh

# Crear la base de datos template y ejecutar el script para inicializar las tablas
RUN sqlite3 /app/database_template.db < /app/init.sql

# Crear directorio para datos persistentes
RUN mkdir -p /data

# Exponer el directorio como volumen para acceder a la base de datos desde fuera
VOLUME ["/data"]

# Exponer puerto para conexión externa (opcional, para herramientas que lo requieran)
EXPOSE 8080

# Usar el entrypoint personalizado
ENTRYPOINT ["/app/entrypoint.sh"]

# Comando por defecto: abre la CLI de sqlite
CMD ["sqlite3", "/data/database.db"]