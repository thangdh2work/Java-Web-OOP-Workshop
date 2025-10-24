# Student Manager

---
## 1) Run on Local
```bash
export SPRING_PROFILES_ACTIVE=''
mvn spring-boot:run
# open http://localhost:8080
# admin / 123456
```
- Switch VI/EN using the button in the **navbar** (after login) and on the **login page**.

---
## 2) Build the Docker image from source
The Dockerfile is **multi-stage** (builds the JAR internally):

```bash
docker build -t registry.minnn.tech/fpt-university/java-web-oop-workshop:local .
```

> The local image `registry.minnn.tech/fpt-university/java-web-oop-workshop:local` is ready. You can test-run it (step 4) before pushing.

---

## 3) Tag the image for Docker Registry

Tag:
```bash
docker tag registry.minnn.tech/fpt-university/java-web-oop-workshop:local registry.minnn.tech/fpt-university/java-web-oop-workshop:<team-name>
```
---

## 4) Log in to Docker Registry
```bash
docker login -u fpt-university -p <change-password> registry.minnn.tech 
```

---

## 5) Push the image to Docker Registry
```bash
registry.minnn.tech/fpt-university/java-web-oop-workshop:<team-name>
```

> After this step, your Docker Registry repo `dregistry.minnn.tech/fpt-university/java-web-oop-workshop` will have `<team-name>` tags.

---

## 6) Pull & run the image on another machine/VM
On the target machine (Docker only, no source code required):

```bash
docker login -u fpt-university -p <change-password> registry.minnn.tech 

# Run the app
docker run -d --name devops-app -p 8080:8080 -e SPRING_PROFILES_ACTIVE='' registry.minnn.tech/fpt-university/java-web-oop-workshop:<team-name>
```

Open your browser:
- `http://<server>:8080/` → redirects to login  
- Login: `admin / admin123` → go to `/students` (ADMIN sees **Users** menu)  
- Regular users → `/me` for profile; change password at `/me/change-password`