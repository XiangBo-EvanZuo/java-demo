# Nacos 惊天大坑 docker
# Mac M1
- docker 进入 bash，然后 vim /conf/application.properties
- db.url.0=本地的host地址！！！不能写localhost，这样就是在docker里面了，
- 这些环境变量还得看application.properties里面的。