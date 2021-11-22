#### Docker Commands

## Note: use Java SDK version 8.0. Any version above that will break the build.

# Todo 

run `docker -v` - to check docker version

run `docker build -f Dockerfile -t guidelk-docker .` - to generate & build docker image

run `docker images` - check available docker images

finally, run `docker compose up` or `docker run -p 8085:8085 guidelk-docker` - run docker image in port 8085 and initialize springboot


# Errors encountered

> Open Intellij > Maven Tab > open lifecycle > run clean > run install

**or**

run `mvn clean install` and then follow the steps in **Todo** to initialize **docker**

# Video Implementation on how to set up

link - <https://drive.google.com/drive/folders/1dCt_eariP3iL58OHVIZtDhIul2x3thZv?usp=sharing>

