1. 安装docker

2. 安装virtualBox

3. 安装kubectl
```
brew install kubectl
```

4. 安装minikube

使用阿里巴巴的minikube镜像：
```
curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.30.0/minikube-darwin-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/
```

5. 启动kubernetes

缺省Minikube使用VirtualBox驱动来创建Kubernetes本地环境
```
minikube start --registry-mirror=https://registry.docker-cn.com
```