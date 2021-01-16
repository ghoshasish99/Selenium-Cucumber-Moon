FROM    maven:3.6.0-jdk-8
LABEL maintainer="Ashish Ghosh"
ENV GRIDURL=""
ENV TAGS=""
ENV PAT=""
RUN     mkdir /functional-test
WORKDIR /functional-test
COPY    . .
RUN     mvn dependency:resolve
CMD mvn test -Dcucumber.options="--tags ${TAGS}" -DExecutionPlatform="GRID_CHROME" -Dpat=${PAT} -Dhub=${GRIDURL}