FROM openjdk:11-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.optimagrowth.configserver.ConfigurationServerApplication"]