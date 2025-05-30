1. 이미지 다운로드

docker pull redis

2. 실행

docker run -p 6379:6379 redis
docker ps -a (별개의 창)

3. 컨테이너 접속

docker exec -it {container id} /bin/bash

4. redis 서버 접속 (컨테이너 접속 후)

redis-cli

**명령어**
- keys * -> 새 레디스를 실행하고 key가 없는 상태에서 레디스가 잘 실행되는지 확인하기에 좋은 command

- set key1 value1 -> key1에 value1을 저장

- get key1
"value1"

- flushall -> key를 다 지움

- MSET employee:1 jenny employee:2 author 

- MGET employee:1 employee:2
1) "jenny"
2) "author"

