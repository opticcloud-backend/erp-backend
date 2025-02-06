#!/bin/bash

# Aguardar o PostgreSQL ficar pronto antes de restaurar o banco
until pg_isready -U ${SPRING_DATASOURCE_USERNAME} -d ${POSTGRES_DB} > /dev/null 2>&1; do
  echo "Aguardando o PostgreSQL iniciar..."
  sleep 2
done

# Restaurar o banco a partir do dump se o banco não existir
echo "PostgreSQL iniciado. Verificando a necessidade de restauração do banco de dados..."

if [ -z "$(psql -U ${SPRING_DATASOURCE_USERNAME} -t -c "SELECT 1 FROM pg_database WHERE datname = '${POSTGRES_DB}'")" ]; then
  echo "Banco de dados não existe, iniciando o restore a partir do backup."
  pg_restore -U ${SPRING_DATASOURCE_USERNAME} -d ${POSTGRES_DB} /docker-entrypoint-initdb.d/backup.dump
else
  echo "Banco de dados já existe, ignorando o restore."
fi
