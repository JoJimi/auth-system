## K8S 환경 구축하기

1. minikube 설치하기

minikube는 가벼운 K8S 구현체 입니다. 로컬 머신에 클러스터를 구성하기 위해 vm을 사용합니다. 
vm을 돌릴 도구가 필요합니다. (docker 추천)

2. vm 도구 설치

brew install docker

3. kubectl & minikube 설치

brew install kubectl (winget install --id Kubernetes.kubectl -e)

brew install minikube (winget install --id Kubernetes.minikube -e)

4. 설치 확인

kubectl version --client

minikube version

5. 설치 완료 테스트

docker desktop 실행

minikube start 혹은 minikube start --driver=docker

6. kubectl get all

7. kubectl apply -f secret.yml

8. kubectl get secret