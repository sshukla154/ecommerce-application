#!/bin/sh

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

until PGPASSWORD="$POSTGRES_PASSWORD" psql -h "$host" -p "$port" -U "$POSTGRES_USER" -c '\q' >/dev/null 2>&1; do
  >&2 echo "PostgreSQL is unavailable - sleeping"
  sleep 1
done

>&2 echo "PostgreSQL is up - executing command"
exec $cmd
