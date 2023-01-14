docker run --rm \
  -v "${DIPLOMA_HOME}/db:/flyway/sql/users" \
  --network "tms-diploma_default" \
  flyway/flyway \
  -url=jdbc:postgresql://diploma-postgres:5432/diploma \
  -user=postgres \
  -password=postgres \
  migrate