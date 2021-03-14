# K8sLearning
dieses Repo dient dem Testen und Lernen von Minikube/K8s und helm.

## Voraussetzung
Folgende Tools müssen installiert sein:
* minikube
* helm
* (kubectl)

## Verwendung
zum Starten einfach das `create_and_start_minikube.sh` Skript aus dem scripts-Ordner ausführen.

Anschließend müssen die docker images gebaut werden. Hierzu im `hello-service` bzw `second-service`
 ein `mvn clean package` ausführen und dann `./../scripts/push-image-to-minikube.sh $SERVICE_NAME`

abschließend muss noch das `deploy_services.sh` ausgeführt werden.

### Ingress
Damit die Service über die Urls aufrufbar sind, müssen die Addressen aus der `Hosts.txt` Datei in die Systemhosts-Datei
eingebunden werden.
