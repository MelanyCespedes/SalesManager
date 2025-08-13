#!/bin/bash

# Script entrypoint para configurar la base de datos SQLite

echo "Iniciando configuración de SQLite..."

# Verificar si la base de datos ya existe en el volumen
if [ ! -f "/data/database.db" ]; then
    echo "Copiando base de datos template a volumen persistente..."
    cp /app/database_template.db /data/database.db
    echo "Base de datos copiada exitosamente."
else
    echo "Base de datos ya existe en el volumen persistente."
fi

# Configurar permisos para que sea accesible desde fuera
chmod 664 /data/database.db
chown root:root /data/database.db

echo "Configuración completada."
echo "Base de datos disponible en: /data/database.db"

# Si se pasa un comando, ejecutarlo; sino, abrir sqlite CLI
if [ $# -eq 0 ]; then
    echo "Iniciando SQLite CLI..."
    exec sqlite3 /data/database.db
else
    echo "Ejecutando comando: $@"
    exec "$@"
fi