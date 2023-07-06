FROM gradle:7.6-jdk17

WORKDIR /zonasb

COPY ./ .

RUN gradle installDist

CMD build/install/zonasb/bin/zonasb