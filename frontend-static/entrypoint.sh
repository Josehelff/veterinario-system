#!/bin/sh
# Injeta a URL do backend no index.html antes de iniciar o nginx
BACKEND_URL="${BACKEND_URL:-http://localhost:8080}"
sed -i "s|BACKEND_URL_PLACEHOLDER|${BACKEND_URL}|g" /usr/share/nginx/html/index.html
echo "Backend URL configurado: $BACKEND_URL"
exec nginx -g "daemon off;"
