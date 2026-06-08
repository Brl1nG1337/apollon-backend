./mvnw clean package -DskipTests

git add .
git commit -m "Deploy $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
git push origin master

ssh bro@192.168.0.128 "
cd /home/bro/apollon-backend &&
git pull origin master &&
docker compose down &&
docker compose build &&
docker compose up -d
"