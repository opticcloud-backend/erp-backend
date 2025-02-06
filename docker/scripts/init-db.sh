#!/bin/bash


until pg_isready -h db -p 5432 -U ${POSTGRES_USER}; do
  echo "Aguardando o PostgreSQL iniciar..."
  sleep 5
done

echo "PostgreSQL iniciado. Verificando a necessidade de restauração do banco de dados..."

if [ -z "$(psql -U ${POSTGRES_USER} -t -c "SELECT 1 FROM pg_database WHERE datname = '${POSTGRES_DB}'")" ]; then
  echo "Banco de dados não existe, iniciando o restore a partir do backup."
  pg_restore -U ${POSTGRES_USER} -d ${POSTGRES_DB} /docker-entrypoint-initdb.d/backup.dump
else
  echo "Banco de dados já existe, ignorando o restore."
fi
