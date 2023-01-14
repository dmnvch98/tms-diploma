docker run --rm \
  -v "${DIPLOMA_HOME}/db/users:/flyway/sql/users" \
  -v "${DIPLOMA_HOME}/db/languageLevel:/flyway/sql/languageLevel" \
  --network "tms-diploma_default" \
  flyway/flyway \
  -url=jdbc:postgresql://diploma-postgres:5432/diploma \
  -user=postgres \
  -password=postgres \
  migrate