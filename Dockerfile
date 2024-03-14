FROM postgres:latest
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=chat-app

# Copy your database schema and data initialization scripts
COPY db_scripts/01_create_tables.sql /docker-entrypoint-initdb.d/
