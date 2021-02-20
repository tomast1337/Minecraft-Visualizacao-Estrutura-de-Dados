echo "Compilando Plugin"
# mvn compile -f "./pom.xml"
mvn package
echo "Copiando Plugin"
cp "./target/blankPlugin-1.0-SNAPSHOT.jar" "./Server/plugins/blankPlugin.jar"

echo "Plugin Copiado"